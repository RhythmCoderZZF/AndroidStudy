package com.example.android_study.android.drawable_and_graph.bitmap.reuse

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_bitmap_reuse.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AndroidBitmapReuseActivity : BaseActivity(), Runnable {
    private var mLastBitmap: Bitmap? = null
    private var isFirst = true

    override fun getLayoutId() = R.layout.activity_android_bitmap_reuse
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val option = BitmapFactory.Options().apply {
            inMutable = true
        }
        btnNoReuse.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_cloudy)
            ivNoReuse.setImageBitmap(bitmap)
        }
        btnReuse.setOnClickListener {
            if (isFirst) {
                mLastBitmap =
                    BitmapFactory.decodeResource(resources, R.drawable.bigpic, option)
                iv.setImageBitmap(mLastBitmap)
            } else {
                val bitmap =
                    BitmapFactory.decodeResource(resources, R.drawable.bg_cloudy, option.apply {
                        inBitmap = mLastBitmap
                    })
                ivReuse.setImageBitmap(bitmap)
            }
            isFirst = false
        }
    }
}