package com.example.android_study.other.zxing;

import android.Manifest;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.util.LogUtil;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class ZXingDemoAy extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    @Override
    protected int getLayoutId() {
        showLifecycle = true;
        return R.layout.activity_z_xing_demo_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        tvTitle.setText("ZXing");
        mZXingView.setDelegate(this);
        //1. 发起权限请求
        ZXingDemoAyPermissionsDispatcher.startScanWithPermissionCheck(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startScan();
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZXingDemoAyPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.CAMERA})
    public void startScan() {
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @OnShowRationale({Manifest.permission.CAMERA})
    void OnShowRationale(PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA})
    void OnPermissionDenied() {
        finish();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA})
    void OnNeverAskAgain() {
        finish();
    }

    /**
     * 扫描成功
     *
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
        showToast(result);
        vibrate();
        mZXingView.startSpot(); // 开始识别
    }

    /**
     * 亮暗回调
     *
     * @param isDark
     */
    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    /**
     * 错误
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        LogUtil.e("camera", "error");
    }
}