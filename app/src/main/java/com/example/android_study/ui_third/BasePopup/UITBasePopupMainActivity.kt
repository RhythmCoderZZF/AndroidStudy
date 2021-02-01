package com.example.android_study.ui_third.BasePopup

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.ui.recyclerView.gesture.adapter.GestureRvAdapter
import kotlinx.android.synthetic.main.activity_u_i_t_base_popup_main.*
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig
import razerdp.blur.PopupBlurOption
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.TranslationConfig


/**
 * github : https://github.com/razerdp/BasePopup
 * 使用手册 : https://www.yuque.com/razerdp/basepopup/rp56q4
 *
 * 一款针对系统PopupWindow优化的Popup库，功能强大，支持背景模糊，使用简单，你会爱上他的~
 */
class UITBasePopupMainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var adapter: GestureRvAdapter
    private lateinit var rv: RecyclerView
    private lateinit var popup: DemoPopup
    override fun getLayoutId() = R.layout.activity_u_i_t_base_popup_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        popup = DemoPopup(this)
        adapter = GestureRvAdapter( listOf("1", "2", "3"))

        rv = popup.findViewById(R.id.rv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)




        b_1.setOnClickListener(this)
        b_2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.b_1 -> {
                popup.setAlignBackground(true)
                popup.popupGravity = Gravity.BOTTOM
                popup.showPopupWindow(v)
            }
            R.id.b_2 -> {
                val q = QuickPopupBuilder.with(this)
                        .contentView(R.layout.activity_item_touch_hel_recycler_view)
                        .config(QuickPopupConfig()
                                .gravity(Gravity.BOTTOM)
//                                .alignBackground(true)//蒙板
                                .withBlurOption(PopupBlurOption().setBlurView(findViewById(android.R.id.content)))//模糊
                                .withShowAnimation(AnimationHelper.asAnimation()
                                        .withTranslation(TranslationConfig.FROM_TOP)
                                        .toShow())
                                .withDismissAnimation(AnimationHelper.asAnimation()
                                        .withTranslation(TranslationConfig.TO_TOP)
                                        .toDismiss())
                                .withClick(R.id.tv) { Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show() }
                        ).build()
                q.showPopupWindow(v)
                val rv = q.findViewById<RecyclerView>(R.id.rv)

               val  adapter = GestureRvAdapter( listOf("1", "2", "3"))
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(this)
            }
        }
    }

}