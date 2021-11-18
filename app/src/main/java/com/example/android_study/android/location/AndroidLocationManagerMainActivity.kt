package com.example.android_study.android.location

import android.annotation.SuppressLint
import android.app.Service
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil
import com.permissionx.guolindev.PermissionX

class AndroidLocationManagerMainActivity : BaseActivity() {

    private var locationManager: LocationManager? = null
    private val callBack = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            CmdUtil.i("onLocationChanged:$location")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            CmdUtil.v("onStatusChanged:$provider | $status")
        }

        override fun onProviderEnabled(provider: String) {
            CmdUtil.v("onProviderEnabled:$provider")
        }

        override fun onProviderDisabled(provider: String) {
            CmdUtil.v("onProviderDisabled:$provider")
        }
    }

    override fun getLayoutId() = R.layout.activity_android_location_manager_main

    @SuppressLint("MissingPermission")
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    @SuppressLint("MissingPermission")
    override fun run() {
        PermissionX.init(this).permissions(android.Manifest.permission.ACCESS_FINE_LOCATION).request { allGranted, _, _ ->
            if (allGranted) {
                locationManager = getSystemService(Service.LOCATION_SERVICE) as LocationManager
                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, callBack)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager?.removeUpdates(callBack)
        locationManager=null
    }
}