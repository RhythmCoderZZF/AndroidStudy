package com.example.android_study.samples.largePhoto.third_framework

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.PointF
import android.net.Uri
import android.util.AttributeSet
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.contains
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.android_study.R
import com.example.android_study._base.util.dp2px
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

/**
 * Author:create by RhythmCoder
 * Date:2021/9/2
 * Description:
 */
class MyMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var mSSIV: SubsamplingScaleImageView = SubsamplingScaleImageView(context).apply {
        layoutParams = LayoutParams(-1, -1)
    }

    private val mPointView = mutableListOf<ImageView>()

    init {
        setWillNotDraw(false)
        addView(mSSIV)
//        mSSIV.apply {
//            setOnStateChangedListener(object : SubsamplingScaleImageView.OnStateChangedListener {
//                override fun onScaleChanged(newScale: Float, origin: Int) {
//                    CmdUtil.i("onScaleChanged:$newScale|$origin")
//                }
//
//                override fun onCenterChanged(newCenter: PointF?, origin: Int) {
//                    CmdUtil.i("onCenterChanged:$newCenter|$origin")
//                }
//            })
//        }
    }

    /* API————————————————————————————————————————————————————————————————————————————————————*/
    /**
     * 添加标记点位
     */
    fun addPoints(points: List<PointF>) {
        if (mSSIV.isReady && points.isNotEmpty()) {
            points.forEach { point ->
                val pointView = ImageView(context).apply {
                    setImageDrawable(resources.getDrawable(R.mipmap.ic_location))
                    layoutParams = LayoutParams(40f.dp2px(), 60f.dp2px())
                }
                pointView.setTag(R.id.point, point)
                pointView.setOnClickListener {
                    mSSIV.animateScaleAndCenter(mSSIV.scale, it.getTag(R.id.point) as PointF)
                        ?.start()
                }
                addView(pointView)
                mPointView.add(pointView)
            }
        }
    }


    /**
     * 从网络加载地图
     */
    fun setMapFromNet(res: String) {
        clearPoints()
        val futureTask = Glide.with(context).downloadOnly().load(res).submit()
        findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
            val file = withContext(IO) {
                futureTask.get(10, TimeUnit.SECONDS)
            }
            mSSIV.setImage(ImageSource.uri(Uri.fromFile(file)))
        }
    }

    /**
     * 本地加载地图
     */
    fun setMapFromResource(id: Int) {
        clearPoints()
        mSSIV.setImage(ImageSource.resource(id))
    }

    /**
     * 将图像中的点位转换成View坐标系的点位
     */
    private fun sourcePointToViewPoint(point: PointF, layoutParams: LayoutParams) {
        val viewPointF = mSSIV.sourceToViewCoord(point.x, point.y)
        viewPointF?.let {
            layoutParams.apply {
                leftMargin = it.x.toInt() - 20f.dp2px()
                topMargin = it.y.toInt() - 30f.dp2px()
            }
            requestLayout()
        }
    }

    private fun clearPoints() {
        mPointView.forEach {
            if (contains(it)) {
                removeView(it)
            }
        }
        mPointView.clear()
    }

    override fun onDraw(canvas: Canvas) {
        if (!mSSIV.isReady) {
            return
        }
        mPointView.forEachIndexed { index, imageView ->
            val p = imageView.getTag(R.id.point) as PointF
            sourcePointToViewPoint(p, imageView.layoutParams as LayoutParams)
        }
    }
}