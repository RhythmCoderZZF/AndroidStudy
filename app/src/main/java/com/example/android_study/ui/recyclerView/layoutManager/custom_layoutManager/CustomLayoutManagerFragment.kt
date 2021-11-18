package com.example.android_study.ui.recyclerView.layoutManager.custom_layoutManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.StringAdapter
import kotlinx.android.synthetic.main.activity_rv.*


/**
 * Author:create by RhythmCoder
 * Date:2021/5/4
 * Description:
 */
class CustomLayoutManagerFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CmdUtil.showWindow()
        return inflater.inflate(R.layout.activity_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.post {
            rv.apply {
                layoutManager = MyLayoutManager()
                adapter = StringAdapter()
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
        private var mTotalHeight = 0//item的总高度，当item不足以填满recyclerView时为recyclerView的高度
        private var mSumDy = 0//记录dy的累加量，有上下界限制[0 ~ itemHeights-recyclerView.height]

        //为child设置layoutParams
        override fun generateDefaultLayoutParams() = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //该方法用于对children进行measure、layout
        override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
            super.onLayoutChildren(recycler, state)
            CmdUtil.i("onLayoutChildren")

            var offsetY = 0
            repeat(itemCount) {
                val itemView = recycler.getViewForPosition(it)
                measureChildWithMargins(itemView, 0, 0)
                addView(itemView)
                val childWidth = getDecoratedMeasuredWidth(itemView)
                val childHeight = getDecoratedMeasuredHeight(itemView)
                layoutDecorated(itemView, 0, offsetY, childWidth, childHeight + offsetY)
                offsetY += childHeight
            }
            mTotalHeight = Math.max(offsetY, getVerticalSpace())
        }

        //计算recyclerView内容区的高度
        private fun getVerticalSpace() = height - paddingTop - paddingBottom

        //是否允许垂直滚动，只有返回true，scrollVerticallyBy函数才会在RecyclerView发生scroll时被调用。
        override fun canScrollVertically() = true

        //该方法由recyclerView move事件、fling事件触发，参数dy为差值
        override fun scrollVerticallyBy(
            dy: Int,
            recycler: RecyclerView.Recycler,
            state: RecyclerView.State?
        ): Int {
            CmdUtil.v("y:$dy")
            var travel = dy
            if (mSumDy + dy <= 0) {//滑到顶部处理
                travel = -mSumDy//矫正travel
            } else if (mSumDy + dy >= mTotalHeight - getVerticalSpace()) {//滑倒底部处理
                travel = mTotalHeight - getVerticalSpace() - mSumDy//矫正travel
            }
            mSumDy += travel

            //对child进行offsetTopAndBottom
            offsetChildrenVertical(-travel)
            return travel
        }
    }
}