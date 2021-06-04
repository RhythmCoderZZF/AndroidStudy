package com.example.android_study.android.drawable_and_graph.bitmap.demo.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import android.widget.ImageView
import androidx.collection.LruCache
import com.bumptech.glide.disklrucache.DiskLruCache
import com.example.android_study.R
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by JDNew on 2017/7/3.
 */
class ImageLoaderCopy(context: Context) {
    private var mIsDiskLruCacheCreated = false//磁盘存储是否创建成功

    private val mMemoryCache: LruCache<String, Bitmap>
    private var mDiskLruCache: DiskLruCache? = null

    private val mContext: Context = context.applicationContext
    private val mImageResizer = ImageResizer()

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt() //单位KB
        val cacheSize = maxMemory / 8//设定缓存的容量为总容量的1/8
        mMemoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount
            }
        }
        //创建文件夹
        val diskCacheDir = getDiskCacheDir(mContext, "bitmap")
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs()
        }
        //创建完文件后获取该文件夹
        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                //利用open方法来创建，第一个参数是存储路径，第二个参数是版本号，
                // 第三个参数是单个节点对应的数据的个数，第四个参数是缓存的总大小
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE)
                mIsDiskLruCacheCreated = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val TAG = "ImagerLoader"
        const val MESSAGE_POST_RESULT = 1
        private const val DISK_CACHE_SIZE = (1024 * 1024 * 50).toLong()//磁盘缓存容量为50MB
        var TAG_KEY_URI: Int = R.id.rv_main

        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()//CPU核心数
        private val CORE_POOL_SIZE = CPU_COUNT + 1//核心线程数
        private val MAXIMUN_POOL_SIZE = CPU_COUNT * 2 + 1//最大容量
        private const val KEEP_ALIVE = 10L//线程闲置超时时长10秒

        private const val IO_BUFFER_SIZE = 8 * 1024

        private val sThreadFactory: ThreadFactory = object : ThreadFactory {
            private val mCount = AtomicInteger()
            override fun newThread(r: Runnable): Thread {
                return Thread(r, "ImageLoader#" + mCount.getAndIncrement())
            }
        }

        /**
         * 线程池
         */
        val THREAD_POOL_EXECUTOR: Executor = ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUN_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,
            LinkedBlockingDeque(),
            sThreadFactory
        )
    }

    private val mMainHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val result = msg.obj as LoaderResult
            val imageView = result.imageView
            val uri = imageView.getTag(TAG_KEY_URI) as String
            if (uri == result.uri) {
                imageView.setImageBitmap(result.bitmap)
            } else {
                Log.w(TAG, "set image bitmap , but url has changed , ignored!")
            }
        }
    }

    /**
     * 获取可用空间
     *
     * @param path
     * @return
     */
    private fun getUsableSpace(path: File): Long {
        return path.usableSpace
        val statFs = StatFs(path.path)
        return statFs.blockSizeLong * statFs.availableBlocksLong //每块的大小*空间的块数
    }

    /**
     * 添加图片到内存缓存
     *
     * @param key
     * @param bitmap
     */
    private fun addBitmapToMemorryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap)
        }
    }

    /**
     * 从内存缓存中取出图片
     *
     * @param key
     * @return
     */
    private fun getBitmapFromMemCache(key: String): Bitmap? {
        return mMemoryCache.get(key)
    }

    /**
     *
     * 如果外部存储可用，就在外部创建文件
     * 否则就在内部存储里创建文件
     *
     * @param context
     * @param uniqueName 文件名
     * @return 返回文件对象
     */
    private fun getDiskCacheDir(context: Context, uniqueName: String): File {
        val externalStorageAvaliable =
            Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        val cachePath: String
        cachePath = if (externalStorageAvaliable) {
            context.externalCacheDir!!.path
        } else {
            context.cacheDir.path
        }
        return File(cachePath + File.separator + uniqueName)
    }

    /**
     * 从网络请求地址里加载图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun loadBitmapFromHttp(url: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("can not visit network from UI Thread.")
        }
        if (mDiskLruCache == null) {
            return null
        }
        val key = hashKeyFromUrl(url)
        val editor: DiskLruCache.Editor = mDiskLruCache!!.edit(key)
        val outputStream: OutputStream = editor.getFile(0).outputStream()
        if (downloadUrlToStream(url, outputStream)) {
            editor.commit()
        } else {
            editor.abort()
        }
        mDiskLruCache!!.flush()
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight)
    }

    /**
     * 从磁盘缓存中加载图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun loadBitmapFromDiskCache(url: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "It's not recommend to load Bitmap from UI Thread")
        }
        if (mDiskLruCache == null) {
            return null
        }
        var bitmap: Bitmap? = null
        val key = hashKeyFromUrl(url)
        val value = mDiskLruCache!!.get(key)
        if (value != null) {
            var ios = value.getFile(0)?.inputStream()
            if (ios == null) {
                ios = value.edit().getFile(0)?.inputStream()
            }
            if (ios == null) {
                return null
            }
            val fileDescriptor = ios.fd
            bitmap = mImageResizer.decodeSampleBitmapFromFileDescriptor(
                fileDescriptor,
                reqWidth,
                reqHeight
            )
            bitmap?.let { addBitmapToMemorryCache(key, it) }
        }
        return bitmap
    }

    private fun downloadUrlToStream(urlString: String, outputStream: OutputStream): Boolean {
        var urlConnection: HttpURLConnection? = null
        var out: BufferedOutputStream? = null
        var `in`: BufferedInputStream? = null
        try {
            val url = URL(urlString)
            urlConnection = url.openConnection() as HttpURLConnection
            `in` = BufferedInputStream(urlConnection.inputStream, IO_BUFFER_SIZE)
            out = BufferedOutputStream(outputStream, IO_BUFFER_SIZE)
            var b: Int
            while (`in`.read().also { b = it } != -1) {
                out.write(b)
            }
            return true
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            urlConnection?.disconnect()
            try {
                `in`!!.close()
                out!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return false
    }

    /**
     * 将图片的url转换成key，这里采用url的md5的值作为key
     *
     *
     * @param url
     * @return
     */
    private fun hashKeyFromUrl(url: String): String {
        val cacheKey: String
        cacheKey = try {
            val messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(url.toByteArray())
            bytesToHexString(messageDigest.digest())
        } catch (e: NoSuchAlgorithmException) {
            url.hashCode().toString()
        }
        return cacheKey
    }

    private fun bytesToHexString(bytes: ByteArray): String {
        val stringBuilder = StringBuilder()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                stringBuilder.append('0')
            }
            stringBuilder.append(hex)
        }
        return stringBuilder.toString()
    }

    fun loadBitmap(uri: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        //先从内存缓存中加载
        var bitmap = loadBitmapFromMemCache(uri)
        if (bitmap != null) {
            Log.d(
                TAG,
                "loadBitmapFromMemCache , url:$uri"
            )
            return bitmap
        }
        try {
            //没有的话从磁盘缓存中加载
            bitmap = loadBitmapFromDiskCache(uri, reqWidth, reqHeight)
            if (bitmap != null) {
                Log.d(
                    TAG,
                    "loadBitmapFromDiskCache , url:$uri"
                )
                return bitmap
            }

            //最后从网络中加载
            bitmap = loadBitmapFromHttp(uri, reqWidth, reqHeight)
            Log.d(
                TAG,
                "loadBitmapFromHttp , url:$uri"
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            Log.d(TAG, "encounter error , DiskLruCache is not created")
            bitmap = downloadBitmapFromUrl(uri)
        }
        return bitmap
    }

    private fun downloadBitmapFromUrl(urlString: String): Bitmap? {
        var bitmap: Bitmap? = null
        var urlConnection: HttpURLConnection? = null
        var inputStream: BufferedInputStream? = null
        try {
            val url = URL(urlString)
            urlConnection = url.openConnection() as HttpURLConnection
            inputStream = BufferedInputStream(
                urlConnection.inputStream, IO_BUFFER_SIZE
            )
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            urlConnection?.disconnect()
            try {
                inputStream!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return bitmap
    }

    private fun loadBitmapFromMemCache(uri: String): Bitmap? {
        val key = hashKeyFromUrl(uri)
        Log.d(
            TAG,
            "loadBitmapFromMemCache , url:$uri"
        )
        return getBitmapFromMemCache(key)
    }

    fun bindBitmap(
        uri: String, imageView: ImageView, reqWidth: Int,
        reqHeight: Int
    ) {
        TAG_KEY_URI = imageView.id
        imageView.setTag(TAG_KEY_URI, uri)
        val loadBitmapTask = Runnable {
            val bitmap = loadBitmap(uri, reqWidth, reqHeight)
            if (bitmap != null) {
                val result = LoaderResult(imageView, uri, bitmap)
                mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget()
            }
        }
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask)
    }

    private class LoaderResult(var imageView: ImageView, var uri: String, var bitmap: Bitmap)

}