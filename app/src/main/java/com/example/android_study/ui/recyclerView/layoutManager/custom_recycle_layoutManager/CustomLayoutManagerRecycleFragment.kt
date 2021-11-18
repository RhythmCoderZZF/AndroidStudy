package com.example.android_study.ui.recyclerView.layoutManager.custom_recycle_layoutManager

import android.graphics.Rect
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
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
class CustomLayoutManagerRecycleFragment : BaseFragment() {
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
    }

    inner class MyLayoutManager : RecyclerView.LayoutManager() {
        private var mTotalHeight = 0//item总高度，当item不足以填满recyclerView时为recyclerView的高度
        private var mSumDy = 0//记录dy的累加量，有上下界限制[0 ~ itemHeights-recyclerView.height]

        private var mItemWidth = 0
        private var mItemHeight = 0
        private var mItemRects = SparseArray<Rect>()

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
            var travel = dy
            if (mSumDy + dy <= 0) {//滑到顶部处理
                travel = -mSumDy//矫正travel
            } else if (mSumDy + dy >= mTotalHeight - getVerticalSpace()) {//滑倒底部处理
                travel = mTotalHeight - getVerticalSpace() - mSumDy
            }


            //对可见的child进行遍历，预判child经过travel的平移之后是否屏幕不可见，依此来进行[回收]
            for (i in childCount - 1 downTo 0) {
                val view = getChildAt(i)!!
                if (travel > 0) {//上滑，判断child是否会滑出屏幕上边界，滑出则需要回收
                    if (getDecoratedBottom(view) - travel < 0) {
                        removeAndRecycleView(view, recycler)//回收
                        continue
                    }
                }
            }

            //对不可见的child进行遍历，预判child经过travel的平移之后是否屏幕可见，依此来进行[复用]
            if (travel >= 0) {
                val lastView = getChildAt(childCount - 1)!!
                val minPos = getPosition(lastView) + 1
                for (i in minPos until itemCount) {
                    val rect = mItemRects.get(i)
                    if (Rect.intersects(rect, getVisibleArea(travel))) {
                        val child = recycler.getViewForPosition(i)
                        addView(child)
                        measureChildWithMargins(child, 0, 0)
                        layoutDecorated(
                            child,
                            rect.left,
                            rect.top - mSumDy,
                            rect.right,
                            rect.bottom - mSumDy
                        )
                    } else {
                        break
                    }
                }
            }
            mSumDy += travel
            //对child进行offsetTopAndBottom
            offsetChildrenVertical(-travel)
            return travel
        }

        private fun getVisibleArea(travel: Int) = Rect(
            paddingLeft,
            paddingTop + mSumDy + travel,
            width+paddingRight,
            getVerticalSpace() + mSumDy + travel
        )
    }
}