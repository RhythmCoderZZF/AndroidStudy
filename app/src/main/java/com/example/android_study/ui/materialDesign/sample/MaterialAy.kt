package com.example.android_study.ui.materialDesign.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import butterknife.BindView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.ToolbarHelper
import com.example.android_study.other.status_bar.util.StatusBarUtil
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_metarial_ay.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Material Design 控件
 */
class MaterialAy : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_metarial_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        toolbar!!.setBackgroundColor(resources.getColor(android.R.color.transparent))
        ToolbarHelper.setBar(mContext, "Material Design")
        StatusBarUtil.setStatusBarColor(this, Color.TRANSPARENT)
        StatusBarUtil.setSystemUi(this, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }

    fun onFloatButtonClick(view: View?) {
        val home = Snackbar.make(view!!, "HOME", Snackbar.LENGTH_SHORT)
        home.setAction("dismiss") { v: View? -> home.dismiss() }.show()
        CmdUtil.e("""
    
    collapsingToolbarLayout->${collapsingToolbarLayout!!.paddingTop}
    """.trimIndent())
        CmdUtil.e("appBar->" + appBar!!.paddingTop)
        CmdUtil.e("coordinatorLayout->" + coordinatorLayout!!.paddingTop)
    }
}