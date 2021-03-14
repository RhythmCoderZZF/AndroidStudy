package com.example.android_study.android.drawable_and_graph.drawable

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.CommonUtils
import com.example.android_study._base.util.screenHeight
import com.example.android_study._base.util.screenWidth

class AndroidDrawableActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_android_drawable

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}

class MyView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var i=0
    val drawable = MyDrawable()
    init {
        addOnLayoutChangeListener{_,_,_,_,_,_,_,_,_->
            drawable.bounds = Rect(100, 100, screenWidth-100, screenHeight-100)//bounds是drawable的大小
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawable.draw(canvas!!)
        CmdUtil.v("${i++}")
        invalidate()
    }
}

/**
 * Drawable具有View的功能，只会绘制，要绘制有大小的drawable要设置其bounds
 */
class MyDrawable : Drawable() {
    private val mPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    }

    override fun draw(canvas: Canvas) {
        for (i in 0..bounds.right step 50) {
            canvas.drawLine(i.toFloat(), bounds.top.toFloat(), i.toFloat(), bounds.bottom.toFloat(), mPaint)
        }
        for (i in 0..bounds.bottom step 50) {
            canvas.drawLine(bounds.left.toFloat(), i.toFloat(), bounds.right.toFloat(), i.toFloat(), mPaint.apply { color = CommonUtils.randomColor() })//注意CommonUtils.randomColor()会一直new Random对象
        }
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity() = when (mPaint.alpha) {
        0xff -> PixelFormat.OPAQUE
        0 -> PixelFormat.TRANSPARENT
        else -> PixelFormat.TRANSLUCENT
    }
}