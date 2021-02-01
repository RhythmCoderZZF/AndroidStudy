package com.example.android_study.third.map._3dmap

import android.os.Bundle
import android.util.Log
import com.amap.api.maps.model.MyLocationStyle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_show_map.*


class ThridMapShowActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_show_map

    override fun initViewAndData(savedInstanceState: Bundle?) {
        map.onCreate(savedInstanceState)// 此方法必须重写

        val aMap = map.map
        val myLocationStyle = MyLocationStyle()
        aMap.myLocationStyle = myLocationStyle

        aMap.uiSettings.isMyLocationButtonEnabled = true;// 设置默认定位按钮是否显示
        aMap.isMyLocationEnabled = true;// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW)

        aMap.setOnMyLocationChangeListener {
            val lat = it?.latitude
            val lon = it?.longitude
            val bundle: Bundle = it.extras
            val errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE)
            val errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO)
            // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
            val locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE)
            Log.e("amap", "定位信息，经纬度($lat $lon) code: $errorCode errorInfo: $errorInfo locationType: $locationType")
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