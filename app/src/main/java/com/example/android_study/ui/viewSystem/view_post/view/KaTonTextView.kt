package com.example.android_study.ui.viewSystem.view_post.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView


/**
 * Author:create by RhythmCoder
 * Date:2021/6/9
 * Description:
 */
class KaTonTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    //重写设置字体方法
    override fun setTypeface(tf: Typeface?) {
        val tf = Typeface.createFromAsset(context.assets, "katon.TTF")
        super.setTypeface(tf)
    }
}