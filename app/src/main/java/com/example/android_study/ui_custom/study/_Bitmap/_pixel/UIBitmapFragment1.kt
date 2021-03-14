package com.example.android_study.ui_custom.study._Bitmap._pixel

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_cus_bitmap1.*

class UIBitmapFragment1 : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_bitmap1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmap = (iv.drawable as BitmapDrawable).bitmap

        val bitmap1 = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        repeat(bitmap1.height) { h ->
            repeat(bitmap1.width) { w ->
                val pixelColor = bitmap1.getPixel(w, h)
                val a = Color.alpha(pixelColor)
                val r = Color.red(pixelColor)
                val g = Color.green(pixelColor)
                val b = Color.blue(pixelColor)
                bitmap1.setPixel(w, h, Color.argb(a, r + 30, g + 80, b - 30))
            }
        }
        iv1.setImageBitmap(bitmap1)

    }
}