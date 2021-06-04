package com.example.android_study.android._webview.interaction.android_call_js

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_android_webview_interaction.*


/**
 * Author:create by RhythmCoder
 * Date:2021/6/4
 * Description:
 */
class AndroidCallJsFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_android_webview_interaction,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //1.配置
        webview.settings.apply {
            //一定要设置该属性
            javaScriptEnabled = true
            //一定要设置该属性
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically=true
            setAppCacheEnabled(true)
            setAppCachePath(requireContext().cacheDir.absolutePath)
        }

        webview.loadUrl("file:///android_asset/Interaction_android_call.html")


        webview.webViewClient=object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        btn_webview_rotate.setOnClickListener {
            //3.Android调用Js方法
            webview.loadUrl("javascript:callJs('90deg')")
        }

        btn_webview_rotate1.setOnClickListener {
            webview.evaluateJavascript("javascript:callJs1('90deg')"){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }
}