package com.example.android_study._android._fragment

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.android_study.R
import com.example.android_study.base.BaseFragment
import com.example.android_study._android._fragment._lifecycle.FragmentIns
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_fragment_demo_ay.*

class FragmentDemoAy : BaseActivity() {
    private var mLastFgIndex: Int = 0
    private var mFragments = MutableList<BaseFragment>(5) {
        FragmentIns("我是第 $it 个fragment")
    }


    override fun getLayoutId(): Int {
        showLifecycle = true
        CmdUtil.showWindow()
        return R.layout.activity_fragment_demo_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        initBottomNavigationView()
        switchFragment(0)
    }

    private fun initBottomNavigationView() {
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_main_pager -> switchFragment(0)
                R.id.tab_knowledge_hierarchy -> switchFragment(1)
                R.id.tab_wx_article -> switchFragment(2)
                R.id.tab_navigation -> switchFragment(3)
                R.id.tab_project -> switchFragment(4)
                else -> {
                }
            }
            true
        }
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private fun switchFragment(position: Int) {
        if (position >= mFragments.size) {
            return
        }
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val targetFg = mFragments[position]
        val lastFg = mFragments[mLastFgIndex]
        mLastFgIndex = position
        ft.hide(lastFg)
        if (!targetFg.isAdded) {
            supportFragmentManager.beginTransaction().remove(targetFg).commitAllowingStateLoss()
            ft.add(R.id.fl, targetFg)
        }
        ft.show(targetFg)
        ft.commitAllowingStateLoss()
    }

}