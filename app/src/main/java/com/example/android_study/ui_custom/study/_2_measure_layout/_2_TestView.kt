package com.example.android_study.ui_custom.study._2_measure_layout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.marginBottom
import com.example.android_study._base.util.dp2px

/**
 * Author:create by RhythmCoder
 * Date:2021/2/4
 * Description:
 */
class _2_TestView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val recommendWidth = MeasureSpec.getSize(widthMeasureSpec)
        val recommendHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0
        repeat(childCount) {
            val child = getChildAt(it)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val childLayoutParams = child.layoutParams as MarginLayoutParams
            val childW = child.measuredWidth + childLayoutParams.marginStart + childLayoutParams.marginEnd
            val childH = child.measuredHeight + childLayoutParams.topMargin + childLayoutParams.bottomMargin

            width = Math.max(childW, width)
            height += childH
        }
        setMeasuredDimension(if (modeWidth == MeasureSpec.EXACTLY) recommendWidth else width,
                if (modeHeight == MeasureSpec.EXACTLY) recommendHeight else height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        repeat(childCount) {
            val child = getChildAt(it)
            val childLayoutParams = child.layoutParams as MarginLayoutParams
            val childH = child.measuredHeight + childLayoutParams.topMargin + childLayoutParams.bottomMargin

            child.layout(childLayoutParams.marginStart,
                    childLayoutParams.topMargin + top,
                    child.measuredWidth + childLayoutParams.marginStart,
                    childLayoutParams.topMargin + top + child.measuredHeight)
            top += childH
        }
    }

    /**
     * XML Child LayoutParams要生效必须重写该方法
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}