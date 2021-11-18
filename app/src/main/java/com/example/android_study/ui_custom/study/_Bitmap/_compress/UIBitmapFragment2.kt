package com.example.android_study.ui_custom.study._Bitmap._compress

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_cus_bitmap2.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class UIBitmapFragment2 : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_bitmap2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val srcBitmap = (iv.drawable as BitmapDrawable).bitmap


        CmdUtil.v("原图尺寸大小:${srcBitmap.width}|${srcBitmap.height}|${srcBitmap.allocationByteCount}")


        val bos = ByteArrayOutputStream()
        var desBitmap: Bitmap?
        lifecycleScope.launch(IO) {
            if (srcBitmap.compress(Bitmap.CompressFormat.JPEG, 1, bos)) {
                val bytes = bos.toByteArray()
                desBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                CmdUtil.v("压缩1%后:${desBitmap?.width}|${desBitmap?.height}|${desBitmap?.allocationByteCount}")
                withContext(Main) {
                    iv.setImageBitmap(desBitmap)
                }
            }
        }
        val bos1 = ByteArrayOutputStream()
        var desBitmap1: Bitmap?
        lifecycleScope.launch(IO) {
            if (srcBitmap.compress(Bitmap.CompressFormat.JPEG, 30, bos1)) {
                val bytes = bos1.toByteArray()
                desBitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                CmdUtil.v("压缩30%后:${desBitmap1?.width}|${desBitmap1?.height}|${desBitmap1?.allocationByteCount}")
                withContext(Main) {
                    iv1.setImageBitmap(desBitmap1)
                }
            }
        }

        val matrix = iv2.imageMatrix


//        var once = true
        val viewTreeObserve = iv2.viewTreeObserver
//        var observe: ViewTreeObserver.OnPreDrawListener? = null
//        observe = ViewTreeObserver.OnPreDrawListener {
//            if (once) {
//                iv2.imageMatrix.setScale(2f, 2f)
//                iv2.invalidate()
//                val desBitmap2 = (iv2.drawable as BitmapDrawable).bitmap
//                CmdUtil.v("放大后:${desBitmap2.width}|${desBitmap2.height}|${desBitmap2.allocationByteCount}")
//            }
//            once = false
//            true
//        }
//        viewTreeObserve.addOnPreDrawListener(observe)

        iv2.viewTreeObserver.addOnGlobalLayoutListener {
            iv2.imageMatrix.setScale(2f, 2f)
            iv2.invalidate()
            val desBitmap2 = (iv2.drawable as BitmapDrawable).bitmap
            CmdUtil.v("放大后:${desBitmap2.width}|${desBitmap2.height}|${desBitmap2.allocationByteCount}")
        }

        iv2.post {
            iv2.imageMatrix.setScale(2f, 2f)
            iv2.invalidate()
            val desBitmap2 = (iv2.drawable as BitmapDrawable).bitmap
            CmdUtil.v("放大后:${desBitmap2.width}|${desBitmap2.height}|${desBitmap2.allocationByteCount}")
        }
    }
}