package com.example.android_study.android.drawable_and_graph.bitmap.demo

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_android_bitmap_demo.*

class AndroidLoadBitmapDemoActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_android_bitmap_demo
    override fun initViewAndData(savedInstanceState: Bundle?) {
        rv_main.apply {
            adapter = PicAdapter(this@AndroidLoadBitmapDemoActivity).apply {
                submitList(listOf(
                    "https://cdn.pixabay.com/photo/2021/05/11/05/57/men-6245003_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2020/09/04/16/29/landscape-5544380_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/22/17/06/hybrid-6274156_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/24/14/15/woman-6279417_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2018/05/14/13/44/natural-3400005_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/24/11/56/lake-6278825_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/16/01/04/orchids-6256963_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/03/01/09/29/woman-6059236_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/28/14/34/ships-6291071_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/26/10/02/tulips-6284729_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/14/16/06/building-6253867_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/28/06/16/black-spotted-longhorn-beetle-6289968_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/27/18/15/motorcycle-6288958_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/02/04/20/04/tea-5982485_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2021/05/25/03/39/greylag-geese-6281214_960_720.jpg",
                    "https://cdn.pixabay.com/photo/2020/08/08/17/44/cockle-5473563_960_720.jpg",
                ))
            }
        }
    }
}