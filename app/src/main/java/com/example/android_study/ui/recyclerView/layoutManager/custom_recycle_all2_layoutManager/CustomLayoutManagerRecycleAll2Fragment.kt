package com.example.android_study.ui.recyclerView.layoutManager.custom_recycle_all2_layoutManager

import android.graphics.Rect
import android.os.Bundle
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.StringAdapter
import com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_meet.ItemDecorationFragment
import kotlinx.android.synthetic.main.activity_rv.*


/**
 * Author:create by RhythmCoder
 * Date:2021/5/4
 * Description:
 */
class CustomLayoutManagerRecycleAll2Fragment : BaseFragment() {
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
                addItemDecoration(ItemDecorationFragment().MyLinearItemDecoration())
                adapter = StringAdapter()
            }
        }
    }

    inner class MyLayoutManager : RecyclerView.LayoutManager() {
        private var mTotalHeight = 0//item总高度，当item不足以填满recyclerView时为recyclerView的高度
        private var mSumDy = 0//记录dy的累加量，有上下界限制[0 ~ itemHeights-recyclerView.height]

        private var mItemWidth = 0
        private var mItemHeight = 0
        private var mItemRects = SparseArray<Rect>()

        private var mHasAttachedItems = SparseBooleanArray()

        //为child设置layoutParams
        override fun generateDefaultLayoutParams() = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //该方法用于对children进行measure、layout
        override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
            super.onLayoutChildren(recycler, state)
            CmdUtil.v("onLayoutChildren")

            //没有child就不显示
            if (itemCount == 0) {
                return
            }
            //剥离所有child
            detachAndScrapAttachedViews(recycler)

            mHasAttachedItems.clear()
            mItemRects.clear()

            //根据第一个child 获取item宽高、一屏可见的child个数
            val view = recycler.getViewForPosition(0)
            measureChildWithMargins(view, 0, 0)
            mItemWidth = getDecoratedMeasuredWidth(view)
            mItemHeight = getDecoratedMeasuredHeight(view)

            val visibleCount = if (getVerticalSpace() % mItemHeight == 0) {
                getVerticalSpace() / mItemHeight
            } else {
                getVerticalSpace() / mItemHeight + 1
            }

            //将所有child的宽高保存起来
            var offsetY = 0
            repeat(itemCount) {
                val rect = Rect(0, offsetY, mItemWidth, offsetY + mItemHeight)
                mItemRects.put(it, rect)
                offsetY += mItemHeight
            }

            //只为可见的child进行布局
            repeat(visibleCount) {
                val rect = mItemRects.get(it)
                val view = recycler.getViewForPosition(it)
                measureChildWithMargins(view, 0, 0)
                addView(view)
                layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom)
            }
            mTotalHeight = Math.max(offsetY, getVerticalSpace())
        }

        //计算recyclerView内容区的高度
        private fun getVerticalSpace() = height - paddingTop - paddingBottom

        //是否允许垂直滚动，只有返回true，scrollVerticallyBy函数才会在RecyclerView发生scroll时被调用。
        override fun canScrollVertically() = true

        //该方法由recyclerView move事件、fling事件触发，参数dy为差值
        override fun scrollVerticallyBy(
            dy: Int,//dy>0时 列表是上滑
            recycler: RecyclerView.Recycler,
            state: RecyclerView.State?
        ): Int {
            CmdUtil.v("y:$dy")
            if (childCount <= 0) return 0
            var travel = dy
            if (mSumDy + dy <= 0) {//滑到顶部处理
                travel = -mSumDy//矫正travel
            } else if (mSumDy + dy >= mTotalHeight - getVerticalSpace()) {//滑倒底部处理
                travel = mTotalHeight - getVerticalSpace() - mSumDy
            }

            mSumDy += travel
            val visibleRect = getVisibleArea()

            //对可见的child进行遍历，预判child经过travel的平移之后是否屏幕不可见，依此来进行[回收]
            for (i in childCount - 1 downTo 0) {
                val child = getChildAt(i)!!
                val position = getPosition(child)
                val rect = mItemRects.get(position)
                if (!Rect.intersects(rect, visibleRect)) {
                    removeAndRecycleView(child, recycler)
                    mHasAttachedItems.put(position, false)
                } else {
                    layoutDecorated(
                        child,
                        rect.left,
                        rect.top - mSumDy,
                        rect.right,
                        rect.bottom - mSumDy
                    )
                    child.rotationY += 1
                    mHasAttachedItems.put(position, true)
                }
            }
            val lastView = getChildAt(childCount - 1)!!
            val firstView = getChildAt(0)!!

            //对不可见的child进行遍历，预判child经过travel的平移之后是否屏幕可见，依此来进行[复用]
            if (travel >= 0) {
                val minPos = getPosition(firstView)
                for (i in minPos until itemCount) {
                    insertView(i, visibleRect, recycler, false)
                }
            } else {
                val maxPos = getPosition(lastView)
                for (i in maxPos downTo 0) {
                    insertView(i, visibleRect, recycler, true)
                }
            }
            return travel
        }

        /**
         * 获取下一帧可见区域（）
         */
        private fun getVisibleArea() = Rect(
            paddingLeft,
            paddingTop + mSumDy,
            width + paddingRight,
            getVerticalSpace() + mSumDy
        )

        private fun insertView(
            pos: Int,
            visibleRect: Rect,
            recycler: RecyclerView.Recycler,
            firstPos: Boolean
        ) {
            val rect = mItemRects.get(pos)
            if (Rect.intersects(rect, visibleRect) && !mHasAttachedItems.get(pos)) {
                val child = recycler.getViewForPosition(pos)
                if (firstPos) {
                    addView(child, 0)
                } else {
                    addView(child)
                }
                measureChildWithMargins(child, 0, 0)
                layoutDecorated(
                    child,
                    rect.left,
                    rect.top - mSumDy,
                    rect.right,
                    rect.bottom - mSumDy
                )
                child.rotationY = (child.rotationY + 1)
            }
        }
    }
}