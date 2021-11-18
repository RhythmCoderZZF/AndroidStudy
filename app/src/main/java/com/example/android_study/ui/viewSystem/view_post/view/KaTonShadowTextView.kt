package com.example.android_study.ui.viewSystem.view_post.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import com.example.android_study.R


/**
 * Author:create by RhythmCoder
 * Date:2021/6/9
 * Description:
 */
class KaTonShadowTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private var borderText: KaTonTextView? = null ///用于描边的TextView


    init {
        borderText = KaTonTextView(context, attrs)
        init()
    }

    fun init() {
        val tp1: TextPaint = borderText!!.paint
        tp1.strokeWidth = 2f //设置描边宽度
        tp1.isAntiAlias=true
        tp1.style = Paint.Style.STROKE //对文字只描边
        borderText!!.setTextColor(resources.getColor(R.color.white)) //设置描边颜色
        borderText!!.gravity = gravity
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        super.setLayoutParams(params)
        borderText!!.layoutParams = params
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val tt: CharSequence = borderText!!.text

        //两个TextView上的文字必须一致
        if (tt != this.text) {
            borderText!!.text = text
            this.postInvalidate()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        borderText!!.measure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        borderText!!.layout(left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        borderText!!.draw(canvas)
        super.onDraw(canvas)
    }


    //重写设置字体方法
    override fun setTypeface(tf: Typeface?) {
        val tf = Typeface.createFromAsset(context.assets, "katon.TTF")
        super.setTypeface(tf)
    }
}