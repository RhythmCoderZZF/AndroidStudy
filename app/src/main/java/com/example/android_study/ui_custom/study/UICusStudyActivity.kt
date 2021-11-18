package com.example.android_study.ui_custom.study

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui_custom.study._1_standard.UICusStandardActivity
import com.example.android_study.ui_custom.demo.standard.UICusStandardDemoActivity
import com.example.android_study.ui_custom.study._2_measure_layout.UICusMeasureLayoutActivity
import com.example.android_study.ui_custom.study._3_gesture.UICusGestureActivity
import com.example.android_study.ui_custom.study._Bitmap._UICusBitmapActivity
import com.example.android_study.ui_custom.study._Camera._UICusCameraActivity
import com.example.android_study.ui_custom.study._Canvas._UICusStudyCanvasActivity
import com.example.android_study.ui_custom.study._Drawable._UICusDrawableActivity
import com.example.android_study.ui_custom.study._Matrix._UICusMatrixActivity
import com.example.android_study.ui_custom.study._Packge_View._UICusPackageViewActivity
import com.example.android_study.ui_custom.study._Paint._UICusStudyPaintActivity
import com.example.android_study.ui_custom.study._SurfaceView._UICusSurfaceViewActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UICusStudyActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. 基础部分", UICusStandardActivity::class.java,"canvas | paint "),
                Entry("2. Paint API", _UICusStudyPaintActivity::class.java),
                Entry("3. Canvas API", _UICusStudyCanvasActivity::class.java),
                Entry("4. Drawable", _UICusDrawableActivity::class.java),
                Entry("5. Bitmap", _UICusBitmapActivity::class.java),
                Entry("6. Measure|Layout", UICusMeasureLayoutActivity::class.java),
                Entry("7. Gesture", UICusGestureActivity::class.java),
                Entry("8. SurfaceView", _UICusSurfaceViewActivity::class.java),
                Entry("9. 封装控件", _UICusPackageViewActivity::class.java),
                Entry("10. Matrix", _UICusMatrixActivity::class.java),
                Entry("11. Camera", _UICusCameraActivity::class.java,"3D Canvas"),
                Entry("demo合集", UICusStandardDemoActivity::class.java)
        ))
    }
}