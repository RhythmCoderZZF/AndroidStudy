package com.example.android_study.third.map._3dmap

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.*
import com.amap.api.maps.model.animation.Animation
import com.amap.api.maps.model.animation.TranslateAnimation
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.DensityUtil
import kotlinx.android.synthetic.main.activity_show_map.*
import kotlin.math.sqrt

/**
 *  aMap.moveCamera：改变camera中心点到指定的经纬度，无需等待map初始化完毕
 *  aMap.cameraPosition.target：获取当前camera中心点经纬度
 */
class ThirdMapChooseLocationActivity : AppCompatActivity() {
    private var latlng = LatLng(29.8537, 121.5832)
    private lateinit var aMap: AMap
    private lateinit var screenMarker: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_map_choose_location)
        map.onCreate(savedInstanceState)
        aMap = map.map
        CmdUtil.showWindow()

        val myLocationStyle = MyLocationStyle().apply {
            myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)//定位一次，且将视角移动到地图中心点。
            showMyLocation(false)//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        }
        aMap.myLocationStyle = myLocationStyle

        aMap.uiSettings.isMyLocationButtonEnabled = true;// 设置默认定位按钮是否显示
        aMap.isMyLocationEnabled = true;// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

        aMap.setOnMyLocationChangeListener {
            val lat = it.latitude
            val lon = it.longitude
            latlng = LatLng(lat, lon)
            val bundle: Bundle = it.extras
            val errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE)
            val errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO)
            // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
            val locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE)
            CmdUtil.v("定位信息，经纬度($lat $lon) code: $errorCode errorInfo: $errorInfo locationType: $locationType")
            aMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latlng, 17f, 0f, 0f)))//CameraPosition参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)


        }
        aMap.setOnMapLoadedListener {
            CmdUtil.e("地图初始化完毕")
            aMap.clear()
            addMarkersToMap()
        }

        //设置可视范围变化时的回调的接口方法
        aMap.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            override fun onCameraChange(position: CameraPosition) {
            }

            override fun onCameraChangeFinish(position: CameraPosition) {
                //屏幕中心的Marker跳动
                startJumpAnimation()
            }
        })


    }

    /**
     * 在屏幕中心添加一个Marker
     */
    private fun addMarkersToMap() {
        val latLng = aMap.cameraPosition.target//当前屏幕中心点的经纬度
        val screenPosition = aMap.projection.toScreenLocation(latLng)//当前屏幕中心点的屏幕坐标
        screenMarker = aMap.addMarker(MarkerOptions()
//                .title("标记")
//                .snippet("这里是摘要")
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location)))
        //设置Marker在屏幕上,不跟随地图移动
        screenMarker.setPositionByPixels(screenPosition.x, screenPosition.y)
        CmdUtil.i("中心经纬度:${latLng} \n 屏幕中心点:${screenPosition}")
    }

    /**
     * 屏幕中心marker 跳动
     */
    fun startJumpAnimation() {
        if (::screenMarker.isInitialized) {
            //根据屏幕距离计算需要移动的目标点
            val latLng = screenMarker.position
            val point = aMap.projection.toScreenLocation(latLng)
            CmdUtil.i("中心点:$point \n经纬度：$latLng")
            point.y -= DensityUtil.dp2px(50f)
            val target = aMap.projection
                    .fromScreenLocation(point)
            //使用TranslateAnimation,填写一个需要移动的目标点
            val animation: Animation = TranslateAnimation(target)
            animation.setInterpolator { input -> // 模拟重加速度的interpolator
                if (input <= 0.5) {
                    (0.5f - 2 * (0.5 - input) * (0.5 - input)).toFloat()
                } else {
                    (0.5f - sqrt((input - 0.5f) * (1.5f - input).toDouble())).toFloat()
                }
            }

            animation.setDuration(600)//整个移动所需要的时间
            screenMarker.setAnimation(animation)//设置动画
            screenMarker.startAnimation()//开始动画
        } else {
            Log.e("amap", "screenMarker is null")
        }
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }

}