package com.example.android_study._base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.android_study.R
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.adapter.RvAdapter
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig
import razerdp.widget.QuickPopup

/**
 * 1. 基类
 *
 *
 * 2. cmd命令行
 * onCreate 中自动绑定Cmd Service（全局公用一个连接）
 * onDestroy中自动解除绑定
 * 显示隐藏cmd 调用 cmd.showWindow\dismissWindow
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope(), Runnable {
    private var unBinder: Unbinder? = null

    @JvmField
    protected var mContext = this

    @JvmField
    protected var showLifecycle //向cmd打印生命周期
            = false
    private var toast: Toast? = null

    private var loadingDialog: QuickPopup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        unBinder = ButterKnife.bind(this)
        initViewAndData(savedInstanceState)
        initToolbar()

        //绑定连接cmd
        CmdUtil.connectCmd(this)
        if (showLifecycle) CmdUtil.v("onCreate")
    }

    private fun initToolbar() {
        val tv = findViewById<TextView>(R.id.tv_title)
        val iv = findViewById<ImageView>(R.id.iv_toolbar_right)
        val title = intent.getStringExtra("title")
        iv?.setOnClickListener { view: View? -> CmdUtil.showWindow() }
        if (tv != null && !TextUtils.isEmpty(title)) {
            tv.text = title
        }
    }

    override fun run() {
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initViewAndData(savedInstanceState: Bundle?)
    fun showToast(message: String?) {
        if (toast != null) {
            toast!!.cancel()
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    override fun onRestart() {
        super.onRestart()
        if (showLifecycle) CmdUtil.v("onRestart")
    }

    override fun onStart() {
        super.onStart()
        if (showLifecycle) CmdUtil.v("onStart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (showLifecycle) CmdUtil.v("onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.v("topActivity", this.javaClass.simpleName)
        if (showLifecycle) CmdUtil.v("onResume")
    }

    override fun onPause() {
        super.onPause()
        if (showLifecycle) CmdUtil.v("onPause")
    }

    override fun onStop() {
        super.onStop()
        if (showLifecycle) CmdUtil.v("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (unBinder != null && unBinder !== Unbinder.EMPTY) {
            unBinder!!.unbind()
            unBinder = null
        }
        if (showLifecycle) CmdUtil.v("onDestroy")
        //解除cmd绑定
        CmdUtil.disConnectCmd(this)
        cancel()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (showLifecycle) CmdUtil.v("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (showLifecycle) CmdUtil.v("onRestoreInstanceState")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        CmdUtil.onActivityResult(requestCode)
        if (showLifecycle) CmdUtil.v("onActivityResult")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (showLifecycle) CmdUtil.v("onTrimMemory")
    }

    protected fun setRecyclerView(rv: RecyclerView, list: List<Entry>) {
        rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.adapter = RvAdapter(list)
    }

    protected fun setViewPagerFragment(root: Window, list: List<EntryF>) {
        val viewPager = root.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        viewPager.apply {
            adapter = object : FragmentStateAdapter(this@BaseActivity) {
                override fun getItemCount() = list.size

                override fun createFragment(position: Int) = list[position].clazz
            }
            offscreenPageLimit = 1
        }

        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = list[i].title
        }.attach()
    }


    fun showLoading(show: Boolean, text: String = "正在加载...") {
        loadingDialog = loadingDialog ?: QuickPopupBuilder.with(this)
            .contentView(R.layout.layout_loading)
            .config(
                QuickPopupConfig()
                    .backgroundColor(Color.TRANSPARENT)
                    .backpressEnable(true)
            )
            .build()
        if (show) {
            if (loadingDialog!!.isShowing)
                return
            loadingDialog!!.showPopupWindow()
            val textView = loadingDialog!!.findViewById<TextView>(R.id.progressText)
            if (text == "") {
                textView.visibility = View.GONE
            }
            textView.text = text
        } else {
            if (loadingDialog!!.isShowing) {
                loadingDialog!!.dismiss()
            }
        }
    }

}