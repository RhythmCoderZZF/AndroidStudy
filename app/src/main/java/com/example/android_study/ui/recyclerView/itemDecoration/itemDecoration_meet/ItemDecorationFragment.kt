package com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_meet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
class ItemDecorationFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        CmdUtil.showWindow()
        return inflater.inflate(R.layout.activity_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.post {
            rv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = StringAdapter().apply {
                    submitList(LIST_DATA)
                }
                addItemDecoration(MyLinearItemDecoration())
            }
        }

    }

    inner class MyLinearItemDecoration : RecyclerView.ItemDecoration() {
        private val mPaint = Paint().apply {
            color = Color.GREEN
            isAntiAlias = true
            strokeWidth = 10f
        }
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(20, 0, 20, 5)
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            CmdUtil.v("onDraw")
            mPaint.color=Color.GREEN
            val layoutManager = parent.layoutManager!!
            var bottom = 0
            repeat(parent.childCount) {
                val child = parent.getChildAt(it)
                val width=parent.width
                val bottomS = layoutManager.getBottomDecorationHeight(child)
                bottom += bottomS + child.height
                c.drawLine(0f,bottom - (child.height / 2f),width.toFloat(),bottom - (child.height / 2f),mPaint)
            }

        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            CmdUtil.v("onDrawOver")
            mPaint.color=Color.RED
            val layoutManager = parent.layoutManager!!
            var bottom = 0
            repeat(parent.childCount) {
                val child = parent.getChildAt(it)
                val width=parent.width
                val bottomS = layoutManager.getBottomDecorationHeight(child)
                bottom += bottomS + child.height
                c.drawLine(0f,bottom - (child.height / 2f)+15,width.toFloat(),bottom - (child.height / 2f)+15,mPaint)
            }
        }
    }
}