package com.example.android_study.android._activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.cmd.CmdUtil;

/**
 * 生命周期
 */
public class Lifecycle2Activity extends BaseActivity {
    private String TAG = "New Activity：";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CmdUtil.i(TAG + "onCreate");
    }

    @Override
    protected void initViewAndData(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_explore1;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CmdUtil.i(TAG + "onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        CmdUtil.i(TAG + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CmdUtil.i(TAG + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CmdUtil.i(TAG + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CmdUtil.i(TAG + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CmdUtil.i(TAG + "onDestroy");

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        CmdUtil.i(TAG + "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CmdUtil.i(TAG + "onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CmdUtil.i(TAG + "onActivityResult");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        CmdUtil.i(TAG + "onTrimMemory");
    }

    public void finish(View view) {
        finish();
    }


}
