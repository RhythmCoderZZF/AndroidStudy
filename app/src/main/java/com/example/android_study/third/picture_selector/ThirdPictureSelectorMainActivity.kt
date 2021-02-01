package com.example.android_study.third.picture_selector

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.LogUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnImageCompleteCallback
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.activity_third_picture_selector_main.*

class ThirdPictureSelectorMainActivity : BaseActivity() {
    private val TAG = this.javaClass.simpleName

    override fun getLayoutId() = R.layout.activity_third_picture_selector_main

    override fun initViewAndData(savedInstanceState: Bundle?) {

        button8.setOnClickListener {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .imageEngine(MyGlideEngine())
                    .isEnableCrop(true)
                    .maxSelectNum(1)
                    .freeStyleCropEnabled(false)//裁剪框是否可拖拽
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)//是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .circleDimmedLayer(true)
                    .forResult(object : OnResultCallbackListener<LocalMedia?> {
                        override fun onResult(result: List<LocalMedia?>?) {
                            showToast(result.toString())
                            // 例如 LocalMedia 里面返回五种path
                            // 1.media.getPath(); 原图path，但在Android Q版本上返回的是content:// Uri类型
                            // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                            // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                            // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                            // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路
                            // 径；注意：.isAndroidQTransform(false);此字段将返回空
                            // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                            // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，
                            //  如旋转角度、经纬度等信息
                            val localMedia = result?.get(0)
                            if (localMedia?.isCut == true) {
                                Log.i(TAG, "裁剪:" + localMedia.cutPath);
                            }

                            result?.forEach { media ->
//                                Log.i(TAG, "裁剪:" + media?.getCutPath());
//                                Log.i(TAG, "是否压缩:" + media?.isCompressed());
//                                Log.i(TAG, "压缩:" + media?.getCompressPath());
//                                Log.i(TAG, "原图:" + media?.getPath());
//                                Log.i(TAG, "是否裁剪:" + media?.isCut());
//                                Log.i(TAG, "裁剪:" + media?.getCutPath());
//                                Log.i(TAG, "是否开启原图:" + media?.isOriginal());
//                                Log.i(TAG, "原图路径:" + media?.getOriginalPath());
//                                Log.i(TAG, "Android Q 特有Path:" + media?.getAndroidQToPath());
                            }
                        }


                        override fun onCancel() {
                            showToast("cancel")
                        }
                    })
        }
    }

    class MyGlideEngine : ImageEngine {
        override fun loadImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }

        override fun loadImage(context: Context, url: String, imageView: ImageView, longImageView: SubsamplingScaleImageView?, callback: OnImageCompleteCallback?) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }

        override fun loadImage(context: Context, url: String, imageView: ImageView, longImageView: SubsamplingScaleImageView?) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }

        override fun loadFolderImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }

        override fun loadAsGifImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }

        override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(imageView)
        }
    }
}