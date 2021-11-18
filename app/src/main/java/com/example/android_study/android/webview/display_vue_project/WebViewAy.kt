package com.example.android_study.android.webview.display_vue_project

import android.os.Bundle
import android.webkit.WebViewClient
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view_ay.*

class WebViewAy : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        webview.settings.apply {
            //一定要设置该属性
            javaScriptEnabled = true
            //一定要设置该属性
            domStorageEnabled=true
            setAppCacheEnabled(true)
            setAppCachePath(this@WebViewAy.cacheDir.absolutePath)
        }

        webview.webViewClient = WebViewClient()
        webview.loadUrl("file:///android_asset/dist/index.html")

//        webview.webChromeClient = object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView, newProgress: Int) {
//                super.onProgressChanged(view, newProgress)
//                if (newProgress == 0) {
//                    pb.visibility = View.VISIBLE
//                }
//                pb.progress = newProgress
//                pb.postInvalidate()
//                if (newProgress == 100) {
//                    pb.visibility = View.GONE
//                }
//            }
//        }
    }
}