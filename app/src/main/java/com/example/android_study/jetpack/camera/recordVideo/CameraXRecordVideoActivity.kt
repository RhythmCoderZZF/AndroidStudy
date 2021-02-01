package com.example.android_study.jetpack.camera.recordVideo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.extensions.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.screenHeight
import com.example.android_study._base.util.screenWidth
import com.example.android_study.jetpack.camera.recordVideo.view.RecordView
import java.io.File
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * author：chs
 * date：2020/5/21
 * des：
 */
class CameraXRecordVideoActivity : BaseActivity() {
    private val deniedPermission = ArrayList<String>()
    private val TAG = this.javaClass.simpleName
    private var outputFilePath: String? = null
    private lateinit var mPreviewView: PreviewView
    private lateinit var mRecordView: RecordView

    //    private var mBtnCameraSwitch: ImageButton
    private lateinit var mPreview: Preview
    private lateinit var mExecutorService: ExecutorService

    /**
     * 照相
     */
    private var mImageCapture: ImageCapture? = null

    /**
     * 录制视频
     */
    private lateinit var mVideoCapture: VideoCapture
    private lateinit var mImageAnalysis: ImageAnalysis
    private lateinit var mCamera: Camera

    /**
     * 可以将一个camera跟任意的LifecycleOwner绑定的一个单例类
     */
    private var mCameraProvider: ProcessCameraProvider? = null

    /**
     * 摄像头朝向 默认向后
     */
    private var mLensFacing = CameraSelector.LENS_FACING_BACK

    /**
     * 是否是照相
     */
    private var takingPicture = false


    override fun getLayoutId() = R.layout.activity_camerax_record_video

    override fun initViewAndData(savedInstanceState: Bundle?) {
        mExecutorService = Executors.newSingleThreadExecutor()
        if (!hsaPermission(this)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        } else {
            setUpCamera()
        }
        mPreviewView = findViewById(R.id.view_finder)
        mRecordView = findViewById(R.id.record_view)
//        mBtnCameraSwitch = findViewById(R.id.camera_switch_button)
        setRecordListener()
//        mBtnCameraSwitch.setOnClickListener(View.OnClickListener { v: View? ->
//            mLensFacing = if (CameraSelector.LENS_FACING_FRONT == mLensFacing) {
//                CameraSelector.LENS_FACING_BACK
//            } else {
//                CameraSelector.LENS_FACING_FRONT
//            }
//            bindCameraUseCases()
//        })
    }


    private fun setRecordListener() {
        mRecordView.setOnRecordListener(object : RecordView.OnRecordListener {
            override fun onTackPicture() {
                //拍照
                takingPicture = true
                //创建图片保存的文件地址
                val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath, System.currentTimeMillis().toString() + ".jpeg")
                val metadata = ImageCapture.Metadata()
                metadata.isReversedHorizontal = mLensFacing == CameraSelector.LENS_FACING_FRONT
                val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file)
                        .setMetadata(metadata)
                        .build()
                mImageCapture!!.takePicture(outputFileOptions, mExecutorService!!, object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        var savedUri = outputFileResults.savedUri
                        if (savedUri == null) {
                            savedUri = Uri.fromFile(file)
                        }
                        outputFilePath = file.absolutePath
                        onFileSaved(savedUri)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: " + exception.message, exception)
                    }
                })
            }

            @SuppressLint("RestrictedApi")
            override fun onRecordVideo() {
                //视频
                takingPicture = false
                //创建视频保存的文件地址
                val file = File(getExternalFilesDir(Environment.DIRECTORY_MOVIES)!!.absolutePath, System.currentTimeMillis().toString() + ".mp4")
                val metadata = VideoCapture.Metadata()
                val outputFileOptions = VideoCapture.OutputFileOptions.Builder(file).build()
                mVideoCapture.startRecording(outputFileOptions, Executors.newSingleThreadExecutor(), object : VideoCapture.OnVideoSavedCallback {
                    override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                        outputFilePath = file.absolutePath
                        onFileSaved(Uri.fromFile(file))
                    }

                    override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                        Log.i(TAG, message)
                    }
                })
            }

            @SuppressLint("RestrictedApi")
            override fun onFinish() {
                //录制完成
                mVideoCapture.stopRecording()
            }
        })
    }

    private fun onFileSaved(savedUri: Uri?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sendBroadcast(Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri))
        }
        val mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap
                .getFileExtensionFromUrl(savedUri!!.path))
        MediaScannerConnection.scanFile(applicationContext, arrayOf(File(savedUri.path).absolutePath), arrayOf(mimeTypeFromExtension)) { path, uri -> Log.d(TAG, "Image capture scanned into media store: \$uri$uri") }
//        PreviewActivity.start(this, outputFilePath, !takingPicture)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            deniedPermission.clear()
            for (i in permissions.indices) {
                val permission = permissions[i]
                val grant = grantResults[i]
                if (grant == PackageManager.PERMISSION_DENIED) {
                    deniedPermission.add(permission)
                }
            }
            if (deniedPermission.isEmpty()) {
                setUpCamera()
            } else {
                AlertDialog.Builder(this)
                        .setMessage("有权限没有授权，无法使用")
                        .setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
                        .setPositiveButton("好的") { dialog, which ->
                            val denied = arrayOfNulls<String>(deniedPermission.size)
                            ActivityCompat.requestPermissions(this, deniedPermission.toArray(denied), PERMISSIONS_REQUEST_CODE)
                        }.create().show()
            }
        }
    }

    private fun setUpCamera() {
        //Future表示一个异步的任务，ListenableFuture可以监听这个任务，当任务完成的时候执行回调
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            try {
                mCameraProvider = cameraProviderFuture.get()
                //选择摄像头的朝向
                mLensFacing = lensFacing
                if (mLensFacing == -1) {
                    Toast.makeText(applicationContext, "无可用的设备cameraId!,请检查设备的相机是否被占用", Toast.LENGTH_SHORT).show()
                    return@Runnable
                }
                // 构建并绑定照相机用例
                bindCameraUseCases()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @SuppressLint("RestrictedApi")
    private fun bindCameraUseCases() {
        //获取屏幕的分辨率
        val displayMetrics = DisplayMetrics()
        mPreviewView.display.getRealMetrics(displayMetrics)
        //获取宽高比
        val screenAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels)
        val rotation = mPreviewView.display.rotation
        if (mCameraProvider == null) {
            Toast.makeText(applicationContext, "相机初始化失败", Toast.LENGTH_SHORT).show()
            return
        }
        val cameraSelector = CameraSelector.Builder().requireLensFacing(mLensFacing).build()
        val pBuilder = Preview.Builder()
        setPreviewExtender(pBuilder, cameraSelector)
        mPreview = pBuilder //设置宽高比
                .setTargetAspectRatio(screenAspectRatio) //设置当前屏幕的旋转
                .setTargetRotation(rotation)
                .build()
        val builder = ImageCapture.Builder()
        setImageCaptureExtender(builder, cameraSelector)
        mImageCapture = builder //优化捕获速度，可能降低图片质量
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY) //设置宽高比
                .setTargetAspectRatio(screenAspectRatio) //设置初始的旋转角度
                .setTargetRotation(rotation)
                .build()
        mVideoCapture = VideoCapture.Builder()
                //设置当前旋转
                .setTargetRotation(rotation)
                //设置宽高比
                .setTargetAspectRatio(screenAspectRatio)
                //                分辨率
                //                .setTargetResolution(resolution)
                //视频帧率  越高视频体积越大
                .setVideoFrameRate(25)
                //bit率  越大视频体积越大
                .setBitRate(3 * 1024 * 1024)
                .build()
        mImageAnalysis = ImageAnalysis.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build()
        mImageAnalysis.setAnalyzer(mExecutorService, { })


        //重新绑定之前必须先取消绑定
        mCameraProvider?.unbindAll()
        mCamera = mCameraProvider!!.bindToLifecycle(this@CameraXRecordVideoActivity,
                cameraSelector, mPreview, mImageCapture, mVideoCapture)
        mPreview.setSurfaceProvider(mPreviewView.surfaceProvider)
    }

    /**
     * 预览自拍视频时 旋转TextureView 解决左右镜像的问题
     *
     * @param textureView
     */
    private fun transformsTextureView(textureView: TextureView) {
        val matrix = Matrix()
        val screenHeight: Int = screenHeight
        val screenWidth: Int = screenWidth
        if (mLensFacing == CameraSelector.LENS_FACING_FRONT) {
            matrix.postScale(-1f, 1f, 1f * screenWidth / 2, 1f * screenHeight / 2)
        } else {
            matrix.postScale(1f, 1f, 1f * screenWidth / 2, 1f * screenHeight / 2)
        }
        textureView.setTransform(matrix)
    }

    /**
     * 给预览设置外部扩展
     * @param builder
     * @param cameraSelector
     */
    private fun setPreviewExtender(builder: Preview.Builder, cameraSelector: CameraSelector) {
        val extender = AutoPreviewExtender.create(builder)
        if (extender.isExtensionAvailable(cameraSelector)) {
            extender.enableExtension(cameraSelector)
        }
        val bokehPreviewExtender = BokehPreviewExtender.create(builder)
        if (bokehPreviewExtender.isExtensionAvailable(cameraSelector)) {
            bokehPreviewExtender.enableExtension(cameraSelector)
        }
        val hdrPreviewExtender = HdrPreviewExtender.create(builder)
        if (hdrPreviewExtender.isExtensionAvailable(cameraSelector)) {
            hdrPreviewExtender.enableExtension(cameraSelector)
        }
        val beautyPreviewExtender = BeautyPreviewExtender.create(builder)
        if (beautyPreviewExtender.isExtensionAvailable(cameraSelector)) {
            beautyPreviewExtender.enableExtension(cameraSelector)
        }
        val nightPreviewExtender = NightPreviewExtender.create(builder)
        if (nightPreviewExtender.isExtensionAvailable(cameraSelector)) {
            nightPreviewExtender.enableExtension(cameraSelector)
        }
    }

    /**
     * 给拍照设置外部预览
     * @param builder
     * @param cameraSelector
     */
    private fun setImageCaptureExtender(builder: ImageCapture.Builder, cameraSelector: CameraSelector) {
        val autoImageCaptureExtender = AutoImageCaptureExtender.create(builder)
        if (autoImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            autoImageCaptureExtender.enableExtension(cameraSelector)
        }
        val bokehImageCaptureExtender = BokehImageCaptureExtender.create(builder)
        if (bokehImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            bokehImageCaptureExtender.enableExtension(cameraSelector)
        }
        val hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder)
        if (hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            hdrImageCaptureExtender.enableExtension(cameraSelector)
        }
        val beautyImageCaptureExtender = BeautyImageCaptureExtender.create(builder)
        if (beautyImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            beautyImageCaptureExtender.enableExtension(cameraSelector)
        }
        val nightImageCaptureExtender = NightImageCaptureExtender.create(builder)
        if (nightImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            nightImageCaptureExtender.enableExtension(cameraSelector)
        }
    }

    private fun aspectRatio(widthPixels: Int, heightPixels: Int): Int {
        val previewRatio = Math.max(widthPixels, heightPixels).toDouble() / Math.min(widthPixels, heightPixels).toDouble()
        return if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            AspectRatio.RATIO_4_3
        } else AspectRatio.RATIO_16_9
    }

    private val lensFacing: Int
        private get() {
            if (hasBackCamera()) {
                return CameraSelector.LENS_FACING_BACK
            }
            return if (hasFrontCamera()) {
                CameraSelector.LENS_FACING_FRONT
            } else -1
        }

    /**
     * 是否有后摄像头
     */
    private fun hasBackCamera(): Boolean {
        if (mCameraProvider == null) {
            return false
        }
        try {
            return mCameraProvider!!.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
        } catch (e: CameraInfoUnavailableException) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 是否有前摄像头
     */
    private fun hasFrontCamera(): Boolean {
        if (mCameraProvider == null) {
            return false
        }
        try {
            return mCameraProvider!!.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
        } catch (e: CameraInfoUnavailableException) {
            e.printStackTrace()
        }
        return false
    }

    override fun onDestroy() {
        mExecutorService.shutdown()
        super.onDestroy()
    }

    companion object {
        private val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
        private const val PERMISSIONS_REQUEST_CODE = 10
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
        fun hsaPermission(context: Context?): Boolean {
            for (permission in PERMISSIONS) {
                val res = ContextCompat.checkSelfPermission(context!!, permission) == PackageManager.PERMISSION_DENIED
                if (res) {
                    return false
                }
            }
            return true
        }
    }
}