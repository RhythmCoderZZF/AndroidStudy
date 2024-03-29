package com.example.android_study._base.cmd;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_study.R;
import com.example.android_study._base.util.LogUtil;

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/14
 * Description: cmd 打印
 */
public class CmdUtil {
    public static FloatViewService.ContentBinder mCmd;//cmd 连接
    private static AlertDialog mOverlayAskDialog;

    /**
     * cmd 打印
     *
     * @param s
     */
    public static void v(String s) {
        LogUtil.v(LogUtil.TAG, s);
        if (mCmd != null)
            mCmd.appendContent(R.color.transparent_white, s);
    }

    public static void i(String s) {
        LogUtil.i(LogUtil.TAG, s);
        if (mCmd != null)
            mCmd.appendContent(R.color.transparent_green, s);

    }

    public static void e(String s) {
        LogUtil.e(LogUtil.TAG, s);
        if (mCmd != null)
            mCmd.appendContent(R.color.transparent_red, s);
    }

    public static void fps(String fps) {
        if (mCmd != null)
            mCmd.setFps(fps);
    }

    public static void fps(String fps, @ColorInt int color) {
        if (mCmd != null)
            mCmd.setFps(fps, color);
    }

    public static void connectCmd(AppCompatActivity activity) {
        if (RomUtils.checkFloatWindowPermission(activity)) {
            activity.bindService(new Intent(activity, FloatViewService.class), floatLogConnection, Context.BIND_AUTO_CREATE);
        } else {
            overlayPermissionRequest(activity);
        }
    }

    public static void overlayPermissionRequest(AppCompatActivity activity) {
        mOverlayAskDialog = new AlertDialog.Builder(activity)
                .setTitle("请求浮窗权限")
                .setMessage("需要开启浮窗权限")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RomUtils.applyPermission(activity, () -> {
                            new Handler().postDelayed(() -> {
                                if (!RomUtils.checkFloatWindowPermission(activity)) {
                                    // 授权失败，继续请求，需强制同意
                                    overlayPermissionRequest(activity);
                                } else {
                                    //授权成功bind cmd
                                    connectCmd(activity);
                                }
                            }, 500);
                        });
                        mOverlayAskDialog.dismiss();
                        mOverlayAskDialog = null;
                    }
                })
                .setCancelable(false)
                .create();
        mOverlayAskDialog.show();
    }

    public static ServiceConnection floatLogConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.v("ServiceConnection", "onServiceConnected");
            mCmd = (FloatViewService.ContentBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.w("ServiceConnection", "onServiceDisconnected!");
        }
    };

    public static void disConnectCmd(AppCompatActivity activity) {
        activity.unbindService(floatLogConnection);
    }

    public static void onActivityResult(int requestCode) {
        RomUtils.onActivityResult(requestCode);
    }

    public static void showWindow() {
        if (mCmd != null)
            mCmd.showWindow();
    }
}
