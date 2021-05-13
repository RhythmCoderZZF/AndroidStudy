package com.example.android_study.ui_custom.event.conflict

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.dp2px
import kotlin.math.abs

class ConflictBActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_dispatch_conflict_b

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }

}

class Father @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var lastX = 0f
    private var lastY = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        lastX = ev.rawX
        lastY = ev.rawY
        return ev.action != MotionEvent.ACTION_DOWN
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                translationX = ev.rawX - lastX
                translationY = ev.rawY - lastY
                lastX = ev.rawX
                lastY = ev.rawY
            }
        }
        return super.onTouchEvent(ev)
    }

}

class Child @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var startX = 0f
    private var startY = 0f

    private var lastX = 0f
    private var lastY = 0f
    private val slop = 10f.dp2px()


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                startX = ev.rawX
                startY = ev.rawY
                lastX = startX
                lastY = startY
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = abs(ev.rawX - startX)
                val offsetY = abs(ev.rawY - startY)
                if (offsetX > slop || offsetY > slop) {
                    if (offsetX < offsetY) {
                        parent.requestDisallowInterceptTouchEvent(false)
                        CmdUtil.i("内部拦截")
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                translationX = ev.rawX - lastX
                translationY = ev.rawY - lastY
            }
        }
        lastX = ev.rawX
        lastY = ev.rawY
        return super.onTouchEvent(ev)
    }
}