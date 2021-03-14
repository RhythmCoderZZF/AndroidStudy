package com.example.android_study.ui_custom.study._4_anim.animator

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF

class _UICusMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1.ValueAnimator", UIValueAnimatorFragment()),
                EntryF("2.插值器估值器", UIInterpolatorEvaluatorFragment()),
                EntryF("3.AnimatorSet", UIAnimatorSetFragment()),
                EntryF("4.Xml加载", UIXmlFragment()),
                EntryF("5.PropertyValuesHolder", UIProperValuesHolderAndKeyframeFragment()),
                EntryF("6.更方便的ViewPropertyAnimator", UIViewPropertyAnimatorFragment())
        ))
    }
}