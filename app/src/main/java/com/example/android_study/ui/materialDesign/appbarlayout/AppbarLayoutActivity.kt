package com.example.android_study.ui.materialDesign.appbarlayout

import android.os.Bundle
import android.view.View
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_appbar_layout.*

/**
 * 参考：https://www.jianshu.com/p/640f4ef05fb2
 * AppbarLayout需要在CoordinatorLayout中使用，且专门配合NestScrollView/RecyclerView
 *
 *
 * 1. 为NestScrollView/RecyclerView（Scrollview）设置AppBarLayout$ScrollingViewBehavior（变为观察者），Behavior拦截ScrollView的touch event发送给AppbarLayout去做处理
 * 2. 为AppBarLayout的子view设置几种事件处理flag
 * —— scroll:
 * 设成这个值的效果就好比本 View 和 Scrolling view 是“一体”的
 * —— enterAways:
 * 当 Scrolling view 向下滚动时，本 View 会一起跟着向下滚动。实际上就好比我们同时对 Scrolling view 和本 View 进行向下滚动。
 * —— snap：
 * 在一次滚动结束时，本 View 很可能只处于“部分显示”的状态，加上这个标记能够达到“要么完全隐藏，要么完全显示”的效果。
 *
 *
 * 下面两个需要配合CollapsingToolBarLayout使用
 * —— exitUntilCollapsed：
 * 当本 View 离开屏幕时，会被“折叠”直到达到其最小高度。我们可以这样理解这个效果：
 * 当我们开始向上滚动 Scrolling view 时，本 View 会先接管滚动事件，这样本 View 会先进行滚动，直到滚动到了最小高度（折叠了），Scrolling view 才开始实际滚动。
 * 而当本 View 已完全折叠后，再向下滚动 Scrolling view，直到 Scrolling view 顶部的内容完全显示后，本 View 才会开始向下滚动以显现出来。
 * —— enterAlwaysCollapsed：
 * 从名字上就可以看出，这是在 enterAlways 的基础上，加上了“折叠”的效果。
 * 当我们开始向下滚动 Scrolling View 时，本 View 会一起跟着滚动直到达到其“折叠高度”（即最小高度）。
 * 然后当 Scrolling View 滚动至顶部内容完全显示后，再向下滚动 Scrolling View，本 View 会继续滚动到完全显示出来。
 */
class AppbarLayoutActivity : BaseActivity(), View.OnClickListener {
    private val fragments = listOf(
            FlagScrollFragment(),
            FlagEnterAwaysFragment()
    )

    override fun getLayoutId() = R.layout.activity_appbar_layout

    override fun initViewAndData(savedInstanceState: Bundle?) {
        scroll.setOnClickListener(this)
        enterAways.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.scroll -> {
                goToFragment(0)
            }
            R.id.enterAways -> {
                goToFragment(1)
            }
        }
    }

    private fun goToFragment(i: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fragment, fragments[i])
        beginTransaction.commit()
    }
}