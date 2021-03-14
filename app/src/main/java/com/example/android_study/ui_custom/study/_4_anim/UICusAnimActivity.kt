package com.example.android_study.ui_custom.study._4_anim

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui_custom.study._4_anim.anim.UICusAnimMainActivity
import com.example.android_study.ui_custom.study._4_anim.animator._UICusMainActivity
import com.example.android_study.ui_custom.study._4_anim.path_measure._UIPathMeasureMainActivity
import com.example.android_study.ui_custom.study._4_anim.svg._UISVGMainActivity
import com.example.android_study.ui_custom.study._4_anim.viewGroup_anim._UIViewGroupAnimMainActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UICusAnimActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val contentView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
        contentView.addView(TextView(this).apply {
            layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
                setMargins(20,20,20,20)
            }
            text = """   
   1. 动画分为2大架构：视图动画（View Animation） | 属性动画（Property Animation）。
                
   2. 视图动画在android.view.animation包 属性动画在android.animation包
   
   3. 试图动画只能作用在View上，且不改变view的属性，属性动画能操作任何值，脱离view存在   
                
   4. 视图动画分为——补间动画（TweenAnimation）<scale、alpha、translate、rotateAnimation> | 帧动画（FrameAnimation）<AnimationDrawable>

   5. 属性动画分为——ValueAnimator | ObjectAnimator"
""".trimIndent()
        })

        setRecyclerView(rv, listOf(
                Entry("1.视图动画", UICusAnimMainActivity::class.java, "4种补间动画(TweenAnimation)、帧动画的(FrameAnimation) xml和code玩法,"),
                Entry("2.属性动画", _UICusMainActivity::class.java, "4种补间动画、帧动画的xml和code玩法"),
                Entry("3.ViewGroup动画", _UIViewGroupAnimMainActivity::class.java),
                Entry("4.PathMeasure", _UIPathMeasureMainActivity::class.java,"用于对Path的追踪截取，其中最有用的getSegment函数用于对Path的截取用于矢量动画"),
                Entry("5.SVG", _UISVGMainActivity::class.java,"")
        ))
    }
}