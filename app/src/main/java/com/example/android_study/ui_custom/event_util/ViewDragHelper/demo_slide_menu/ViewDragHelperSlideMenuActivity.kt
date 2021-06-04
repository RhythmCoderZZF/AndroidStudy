package com.example.android_study.ui_custom.event_util.ViewDragHelper.demo_slide_menu

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_u_i_cus_event_view_drag_helper_demo_slide_menu.*

class ViewDragHelperSlideMenuActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_cus_event_view_drag_helper_demo_slide_menu

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val main = layoutInflater.inflate(R.layout.layout_slide_content, slideMenuView, false)
        val menu = layoutInflater.inflate(R.layout.layout_slide_menu, slideMenuView, false)
        slideMenuView.setMainView(main)
        slideMenuView.setMenuView(menu)
        (menu as ViewGroup).children.forEach {
            it.setOnClickListener { i ->
                val item = i as TextView
                main.findViewById<TextView>(R.id.mainText)?.let { v ->
                    v.text = item.text
                }
            }
        }
    }
}