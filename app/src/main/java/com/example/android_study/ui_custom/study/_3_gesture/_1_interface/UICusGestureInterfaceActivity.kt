package com.example.android_study.ui_custom.study._3_gesture._1_interface

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Choreographer
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.android.synthetic.main.activity_u_i_choreographer.*
import kotlinx.android.synthetic.main.activity_u_i_cus_gesture_interface.*

class UICusGestureInterfaceActivity : BaseActivity() {
    var showScrollParam = false
    var showFlingParam = false
    override fun getLayoutId() = R.layout.activity_u_i_cus_gesture_interface

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun run() {
        val mGesture = GestureDetector(this, mGestureListener)
        v1.isLongClickable = true
        v1.setOnTouchListener { v, e ->
            mGesture.onTouchEvent(e)
        }
        cbFling.setOnCheckedChangeListener { v, check ->
            showFlingParam = check
        }

        cbScroll.setOnCheckedChangeListener { v, check ->
            showScrollParam = check
        }
    }


    private val mGestureListener = object : GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        override fun onDown(e: MotionEvent?): Boolean {
            CmdUtil.v("onDown")
            return true
        }

        override fun onShowPress(e: MotionEvent?) {
            CmdUtil.v("onShowPress")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            CmdUtil.v("onSingleTapUp")
            return true
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            CmdUtil.v("onScroll")
            if (showScrollParam) {
                CmdUtil.e("down:${e1.action}|move:${e2.action}|dX:${distanceX}|dY${distanceY}")
            }
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            CmdUtil.v("onLongPress")
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            CmdUtil.v("onFling")
            if (showFlingParam) {
                CmdUtil.e("down:${e1.action}|move:${e2.action}|vX:${velocityX}|vY${velocityY}")
            }
            return true
        }

        /**
         * double tap
         */
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            CmdUtil.i("onSingleTapConfirmed")
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            CmdUtil.i("onDoubleTap")
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            CmdUtil.i("onDoubleTapEvent")
            return true
        }
    }


}