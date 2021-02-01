package com.example.android_study.framework._permission

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity_permission_ay.*
import permissions.dispatcher.*

@RuntimePermissions
class PermissionAy : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_permission_ay
    }
    init {
        showLifecycle=true
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(mContext, "PermissionsDispatcher", R.mipmap.ic_cmd)
        des.text="""
            1. NeedsPermission：表示用户同意权限
            2. OnPermissionDenied：表示用户拒绝权限
            3. OnShowRationale：表示用户已经拒绝权限，下次再启动该页面，先调用该注解方法,request.proceed()表示再次请求权限
            4. OnNeverAskAgain：表示用户点击了永久拒绝，下次启动该页面，先调用该注解方法
            5. 某些需要权限的动作一定要在NeedsPermission中执行！
        """.trimIndent()
        CmdUtil.showWindow()
        CmdUtil.v("requestPermission")
        needsPermissionWithPermissionCheck()

    }
    //3. 权限申请通过入口
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun needsPermission() {
        tv.text = "权限请求成功~"
        CmdUtil.v("needsPermission")
        val intent = Intent(Intent.ACTION_CALL)
        val data = Uri.parse("tel:5154554545454")
        intent.data = data
        startActivity(intent)
    }

    //5. 被拒绝后下一次继续请求权限
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    fun OnShowRationale(request: PermissionRequest) {
        tv.text = "需要开启电话权限才能正常使用app"
        request.proceed()
        CmdUtil.v("OnShowRationale")
    }

    //4. 拒绝权限
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    fun OnPermissionDenied() {
        tv.text = "你拒绝了权限请求"
        CmdUtil.v("OnPermissionDenied")
    }

    //6. 永久拒绝
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    fun OnNeverAskAgain() {
        tv.text = "你永久的拒绝了！！！"
        CmdUtil.v("OnNeverAskAgain")
    }

    //2. 请求回调，框架执行
    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        CmdUtil.v("onRequestPermissionsResult")
        onRequestPermissionsResult(requestCode, grantResults)
    }
}