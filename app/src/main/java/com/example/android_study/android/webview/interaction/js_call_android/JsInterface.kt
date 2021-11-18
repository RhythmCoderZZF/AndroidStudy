package com.example.android_study.android.webview.interaction.js_call_android

import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface

/**
 * Author:create by RhythmCoder
 * Date:2021/6/4
 * Description:
 */
class JsInterface (val context:Context){

    @JavascriptInterface
    fun startActivity(str: String) {
        context.startActivity(Intent(context, JsInteractionActivity::class.java).apply {
            putExtra("jsCall",str)
        })
    }
}