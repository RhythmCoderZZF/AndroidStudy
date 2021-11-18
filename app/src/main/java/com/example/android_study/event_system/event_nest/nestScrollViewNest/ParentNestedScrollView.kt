package com.example.android_study.event_system.event_nest.nestScrollViewNest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.NestedScrollView

/**
 * Author:create by RhythmCoder
 * Date:2021/5/24
 * Description:
 */
class ParentNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (dy > 0) scrollUp(dy, consumed)
        super.onNestedPreScroll(target, dx, dy, consumed, type)
    }

    private fun scrollUp(dy: Int, consumed: IntArray) {
        val oldScrollY = scrollY
        scrollBy(0, dy)
        consumed[1] = scrollY - oldScrollY
    }


}