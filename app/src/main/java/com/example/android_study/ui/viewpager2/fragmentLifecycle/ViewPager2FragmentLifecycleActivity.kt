package com.example.android_study.ui.viewpager2.fragmentLifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_ui_view_pager2_fragment_lifecycle.*

class ViewPager2FragmentLifecycleActivity : AppCompatActivity(),Runnable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_view_pager2_fragment_lifecycle)
        CmdUtil.showWindow()
        window.decorView.postDelayed(this,1000)

    }

    override fun run() {
        viewPager.apply {
            adapter= object : FragmentStateAdapter(this@ViewPager2FragmentLifecycleActivity) {
                override fun getItemCount() = 20

                override fun createFragment(position: Int) = ViewPagerContentFragment.newInstance("$position")
            }
            offscreenPageLimit=2
        }

        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = "[$i]"
        }.attach()
    }
}