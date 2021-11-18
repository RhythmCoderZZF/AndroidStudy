package com.example.android_study.ui_custom.demo.standard

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.demo.standard.paint_bitmap_shadow.UISDShadowBitmapFragment
import com.example.android_study.ui_custom.demo.standard.paint_bsr_canvas.UISDBSRCanvasFragment
import com.example.android_study.ui_custom.demo.standard.falling_ball.UISDFallingBallFragment
import com.example.android_study.ui_custom.demo.standard.loading_image.UISDLoadingImageFragment
import com.example.android_study.ui_custom.demo.standard.paint_shader.UISDShaderFragment
import com.example.android_study.ui_custom.demo.standard.paint_shader_drawable.UISDShaderShapeDrawableFragment
import com.example.android_study.ui_custom.demo.standard.path_measure.UISDPathMeasureFragment
import com.example.android_study.ui_custom.demo.standard.region_anim.UISDRegionAnimFragment
import com.example.android_study.ui_custom.demo.standard.spider_web.UISDSpiderWebFragment

class UICusStandardDemoActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        setViewPagerFragment(window, listOf(
                EntryF("蜘蛛网（Path）", UISDSpiderWebFragment()),
                EntryF("裁剪动画（Path）", UISDRegionAnimFragment()),
                EntryF("加载中（ValueAnimator）", UISDLoadingImageFragment()),
                EntryF("抛物小球（Value|ObjectAnimator）", UISDFallingBallFragment()),
                EntryF("支付成功（PathMeasure）", UISDPathMeasureFragment()),
                EntryF("手势画线（贝塞尔曲线）", UISDBSRCanvasFragment()),
                EntryF("图片纯色阴影（BlurMaskFilter）", UISDShadowBitmapFragment()),
                EntryF("望远镜（Shader）", UISDShaderFragment()),
                EntryF("放大镜2（Shader|ShapeDrawable）", UISDShaderShapeDrawableFragment()),
        ))
    }
}