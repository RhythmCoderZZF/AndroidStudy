package com.example.android_study.ui.recyclerView.itemDecoration.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.itemDecoration.StringAdapter
import com.example.android_study.ui.recyclerView.itemDecoration.StringAdapter.Companion.mList
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
                    submitList(mList)
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
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.set(200, 0, 20, 5)
            CmdUtil.v("getItemOffsets")
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

            val layoutManager = parent.layoutManager!!
            var left = 0
            var bottom = 0
            CmdUtil.v("${ parent.childCount}")
            repeat(parent.childCount) {
                val child = parent.getChildAt(it)
                left = layoutManager.getLeftDecorationWidth(child)
                val left1 = layoutManager.getDecoratedLeft(child)
                val bottomS = layoutManager.getBottomDecorationHeight(child)
                bottom += bottomS + child.height
                val bottom1 = layoutManager.getDecoratedBottom(parent.getChildAt(it))
                c.drawCircle(left/2f,bottom-(child.height/2f),10f,mPaint)
            }

        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            CmdUtil.v("onDrawOver")
        }
    }
}