package com.example.android_study._android._webview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.android_study.R
import com.example.android_study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view_ay.*

class WebViewAy : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()
        webview.loadUrl("https://www.wanandroid.com")

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 0) {
                    pb.visibility = View.VISIBLE
                }
                pb.progress = newProgress
                pb.postInvalidate()
                if (newProgress == 100) {
                    pb.visibility = View.GONE
                }
            }
        }
    }
}