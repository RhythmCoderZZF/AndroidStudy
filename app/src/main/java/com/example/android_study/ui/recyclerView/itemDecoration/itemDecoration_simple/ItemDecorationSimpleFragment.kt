package com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_simple

import android.graphics.*
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
class ItemDecorationSimpleFragment : BaseFragment() {
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
                layoutManager = LinearLayoutManager(requireContext())
                adapter = StringAdapter().apply {
                    submitList(LIST_DATA)
                }
                addItemDecoration(MyLinearItemDecoration())
            }
        }

    }

    inner class MyLinearItemDecoration : RecyclerView.ItemDecoration() {
        private val mPaint = Paint()

        private lateinit var mBitmap: Bitmap

        init {
            mBitmap = decodeBitmapFromBitmap(R.mipmap.ic_launcher_round, 80, 80)
        }

        private fun decodeBitmapFromBitmap(id: Int, reqWidth: Int, reqHeight: Int): Bitmap {
            return BitmapFactory.Options().run {
                inJustDecodeBounds = true//只计算宽高，不生成bitmap
                BitmapFactory.decodeResource(resources, id, this)//只计算宽高，不生成bitmap
                inSampleSize =
                    calculateInSampleSize(this, reqWidth, reqHeight)//根据bitmap宽高，iv的宽高计算缩放倍数
                inJustDecodeBounds = false//生成bitmap
                BitmapFactory.decodeResource(resources, id, this)
            }
        }

        private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            val (w, h) = options.run { outWidth to outHeight }//Pair(w;h)
            var inSampleSize = 1
            if (w > reqWidth || h > reqHeight) {
                val halfW = w / 2
                val halfH = h / 2
                while (halfW / inSampleSize > reqWidth || halfH / inSampleSize > reqHeight) {
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(200, 5, 20, 5)
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            CmdUtil.v("onDrawOver")
            //绘制图标
            for (index in 0 until parent.childCount step 3) {
                val view = parent.getChildAt(index)
                val top=view.top
                val left =view.left - mBitmap.width / 2
                c.drawBitmap(mBitmap,left.toFloat(),top.toFloat(),mPaint)

            }
            val gradient = LinearGradient(
                parent.width / 2f,
                0f,
                parent.width / 2f,
                200f,
                Color.argb(125, 0, 0, 0),
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
            //绘制阴影
            mPaint.shader = gradient
            c.drawRect(0f,0f,parent.width.toFloat(),parent.height.toFloat(),mPaint)
        }
    }
}