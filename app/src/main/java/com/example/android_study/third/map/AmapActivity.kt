package com.example.android_study.third.map

import android.Manifest
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.third.map._3dmap.ThirdMapChooseLocationActivity
import com.example.android_study.third.map._3dmap.ThirdMapLocationActivity
import com.example.android_study.third.map._3dmap.ThridMapShowActivity
import kotlinx.android.synthetic.main.activity_amap.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnShowRationale
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class AmapActivity : BaseActivity() {
    private val list = listOf(
            Entry("1.地图", ThridMapShowActivity::class.java),
            Entry("2.定位", ThirdMapLocationActivity::class.java),
            Entry("3.地图选点", ThirdMapChooseLocationActivity::class.java)
    )

    override fun getLayoutId() = R.layout.activity_amap
    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
        needPermissionWithPermissionCheck()
    }

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE)
    fun needPermission() {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE)
    fun showRationale(request: PermissionRequest) {
        request.proceed()
    }
}