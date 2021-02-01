package com.example.android_study.third.map._3dmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.android_study.R
import com.example.android_study._base.util.LogUtil
import kotlinx.android.synthetic.main.activity_third_map_location.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * 定位开发指南
 * https://lbs.amap.com/api/android-location-sdk/guide/android-location/getlocation
 */
class ThirdMapLocationActivity : AppCompatActivity() {
    //声明AMapLocationClient类对象
    private lateinit var mLocationClient: AMapLocationClient


    private val mAMapLocationListener = AMapLocationListener {
        if (it != null) {
            if (it.errorCode == 0) {
                //解析定位结果
                it.locationType //获取当前定位结果来源，如网络定位结果，详见定位类型表

                it.latitude //获取纬度

                it.longitude //获取经度

                it.accuracy //获取精度信息

                it.address //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

                it.country //国家信息

                it.province //省信息

                it.city //城市信息

                it.district //城区信息

                it.street //街道信息

                it.streetNum //街道门牌号信息

                it.cityCode //城市编码

                it.adCode //地区编码

                it.aoiName //获取当前定位点的AOI信息

                it.buildingId //获取当前室内定位的建筑物Id

                it.floor //获取当前室内定位的楼层

                it.gpsAccuracyStatus //获取GPS的当前状态

                //获取定位时间
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = Date(it.time)
                val time = df.format(date)
                tv.append("""
                    ${it.locationType}
                    ${it.latitude}
                    ${it.longitude}
                    ${it.accuracy}
                    ${it.address}
                    ${it.country}
                    ${it.province}
                    ${it.city}
                    ${it.district}
                    ${it.street}
                    ${it.streetNum}
                    ${it.cityCode}
                    ${it.adCode}
                    ${it.aoiName}
                    ${it.buildingId}
                    ${it.floor}
                    ${it.gpsAccuracyStatus}
                    $time
                    """.trimIndent())
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                LogUtil.e("AmapError", "location Error, ErrCode:"
                        + it.errorCode + ", errInfo:"
                        + it.errorInfo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_map_location)
        //初始化定位
        mLocationClient = AMapLocationClient(applicationContext)
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener)
        val mLocationOption = AMapLocationClientOption()

        //只定位一次
        mLocationOption.isOnceLocation = true

        mLocationClient.setLocationOption(mLocationOption)
    }

    override fun onStart() {
        super.onStart()
        //启动定位
        mLocationClient.startLocation()
    }

    override fun onStop() {
        super.onStop()
        mLocationClient.stopLocation()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.onDestroy()
    }
}