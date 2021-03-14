package com.example.android_study.ui_custom.study._2_measure_layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.amap.api.mapcore.util.lb

/**
 * Author:create by RhythmCoder
 * Date:2021/2/5
 * Description:
 */
class _2_MyFlowLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val recommendWidth = MeasureSpec.getSize(widthMeasureSpec)

        var lineW = 0//每行宽
        var lineH = 0//每行高

        var width = 0//控件总宽
        var height = 0
        //【一】遍历
        repeat(childCount) {
            val child = getChildAt(it)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)//【二】测量子View
            val childLP = child.layoutParams as MarginLayoutParams
            val childW = child.measuredWidth + childLP.leftMargin + childLP.rightMargin
            val childH = child.measuredHeight + childLP.topMargin + childLP.bottomMargin

            //【三】依据子View尺寸处理计算自身尺寸
            if (lineW + childW > recommendWidth - paddingLeft - paddingRight) {//一行放不下
                lineW = childW//行的宽高变成一个item的宽高
                lineH = childH
                width = Math.max(width, lineW)
                height += lineH//高度累加
            } else {
                lineW += childW//宽度累加
                if (lineH < childH) {
                    height = height - lineH + childH
                } else {
                    height = Math.max(height, childH)
                }
                lineH = Math.max(lineH, childH)
                width = Math.max(width, lineW)
            }
        }
        //【四】加上内边距
        width += (paddingLeft + paddingRight)
        height += (paddingTop + paddingBottom)
        //【五】和最小尺寸、背景取最大值
        width = Math.max(width, suggestedMinimumWidth)
        height = Math.max(height, suggestedMinimumHeight)
        //【六】修正并设置结果
        setMeasuredDimension(View.resolveSize(width, widthMeasureSpec), View.resolveSize(height, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var lineW = 0
        var lineH = 0

        var left = paddingLeft
        var top = paddingTop

        repeat(childCount) {
            val child = getChildAt(it)
            val childLP = child.layoutParams as MarginLayoutParams

            val childW = child.measuredWidth + childLP.leftMargin + childLP.rightMargin
            val childH = child.measuredHeight + childLP.topMargin + childLP.bottomMargin

            if (lineW + childW > width - paddingLeft - paddingRight) {
                top += lineH
                left = paddingLeft
                lineW = childW
                lineH = childH
            } else {
                lineW += childW
                lineH = Math.max(lineH, childH)
            }

            val lc = left + childLP.leftMargin
            val lt = top + childLP.topMargin
            val lr = lc + child.measuredWidth
            val lb = lt + child.measuredHeight

            child.layout(lc, lt, lr, lb)
            left = lineW + paddingLeft
        }

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(MATCH_PARENT, MATCH_PARENT)
    }
}