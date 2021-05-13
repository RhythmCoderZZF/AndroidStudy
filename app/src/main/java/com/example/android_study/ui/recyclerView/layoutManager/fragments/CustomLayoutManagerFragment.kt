package com.example.android_study.ui.recyclerView.layoutManager.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.itemDecoration.StringAdapter
import com.example.android_study.ui.recyclerView.itemDecoration.StringAdapter.Companion.mList
import com.example.android_study.ui.recyclerView.itemDecoration.fragments.ItemDecorationFragment
import kotlinx.android.synthetic.main.activity_rv.*


/**
 * Author:create by RhythmCoder
 * Date:2021/5/4
 * Description:
 */
class CustomLayoutManagerFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        CmdUtil.showWindow()
        return inflater.inflate(R.layout.activity_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.post {
            rv.apply {
                layoutManager = MyLayoutManager()
                adapter = StringAdapter().apply {
                    submitList(mList)
                }
            }
        }
        rv.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_UP -> {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                    v.performClick()
                }
            }
            return@setOnTouchListener false
        }
    }

    inner class MyLayoutManager : RecyclerView.LayoutManager() {
        override fun generateDefaultLayoutParams() = null

        override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
            super.onLayoutChildren(recycler, state)
            CmdUtil.i("onLayoutChildren")
            var top = 0
            repeat(itemCount) {
                val itemView = recycler.getViewForPosition(it)
                addView(itemView)
                measureChildWithMargins(itemView, 0, 0)
                val childWidth = getDecoratedMeasuredWidth(itemView)
                val childHeight = getDecoratedMeasuredHeight(itemView)
                layoutDecorated(itemView, 0, top, childWidth, childHeight + top)
                top += childHeight
                CmdUtil.v("$childWidth | $childHeight")
            }
        }

        override fun canScrollVertically() = true
        override fun canScrollHorizontally() = true
        override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
            CmdUtil.v("y:$dy")
            offsetChildrenVertical(-dy)
            return dy
        }

        override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
            CmdUtil.v("x:$dx")
            offsetChildrenHorizontal(-dx)
            return dx
        }
    }
}