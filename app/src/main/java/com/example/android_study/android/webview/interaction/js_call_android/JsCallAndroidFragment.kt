package com.example.android_study.android.webview.interaction.js_call_android

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_android_webview_interaction_js_call.*


/**
 * Author:create by RhythmCoder
 * Date:2021/6/4
 * Description:
 */
class JsCallAndroidFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(
            R.layout.fragment_android_webview_interaction_js_call,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //1.配置
        webview.settings.apply {
            //一定要设置该属性
            javaScriptEnabled = true
            //一定要设置该属性
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setAppCacheEnabled(true)
            setAppCachePath(requireContext().cacheDir.absolutePath)
        }

        webview.loadUrl("file:///android_asset/Interaction_js_call.html")

        //2.注册Js调用Android接口
        webview.addJavascriptInterface(JsInterface(requireContext()), "androidInterface")

        webview.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                CmdUtil.v("$message")
                val uri = Uri.parse(message)
                if ("js" == uri?.scheme) {
                    if (uri.authority.equals("webview")) {
                        val str = uri.getQueryParameter(uri.queryParameterNames?.elementAt(0))
                        JsInterface(requireContext()).startActivity(str?:"")
                        result?.confirm()
                        return true
                    }
                }
                return super.onJsAlert(view, url, message, result)
            }
        }

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                CmdUtil.v("${request?.url}")
                val uri = request?.url
                if ("js" == uri?.scheme) {
                    if (uri.authority.equals("webview")) {
                        val str = uri.getQueryParameter(uri.queryParameterNames?.elementAt(0))
                        JsInterface(requireContext()).startActivity(str?:"")
                        return true
                    }
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}