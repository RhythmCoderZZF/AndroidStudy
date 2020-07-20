package com.example.android_study._framework._permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.cmd.CmdUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionAy extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_permission_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        //1. 发起权限请求
        PermissionAyPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
        CmdUtil.v("requestPermission");
    }

    //3. 权限申请通过入口
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void needsPermission() {
        CmdUtil.v("needsPermission");
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:5154554545454");
        intent.setData(data);
        startActivity(intent);
    }

    //5. 被拒绝后下一次继续请求权限
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void OnShowRationale(PermissionRequest request) {
        showToast("需要开启电话权限才能正常使用app");
        request.proceed();
        CmdUtil.v("OnShowRationale");
    }

    //4. 拒绝权限
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void OnPermissionDenied() {
        CmdUtil.v("OnPermissionDenied");
    }

    //6. 永久拒绝
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void OnNeverAskAgain() {
        CmdUtil.v("OnNeverAskAgain");
    }

    //2. 请求回调，框架执行
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        CmdUtil.v("onRequestPermissionsResult");
        PermissionAyPermissionsDispatcher.onRequestPermissionsResult(PermissionAy.this, requestCode, grantResults);
    }
}