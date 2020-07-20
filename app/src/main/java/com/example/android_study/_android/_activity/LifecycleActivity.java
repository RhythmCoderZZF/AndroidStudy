package com.example.android_study._android._activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.cmd.CmdUtil;
import com.example.android_study.util.ToolbarHelper;

import butterknife.BindView;

/**
 * 生命周期
 */
public class LifecycleActivity extends BaseActivity {
    @BindView(R.id.content)
    TextView tvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CmdUtil.v("onCreate");
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        ToolbarHelper.setBar(mContext, "Activity Lifecycle");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_explore;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CmdUtil.v("onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        CmdUtil.v("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CmdUtil.v("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CmdUtil.v("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CmdUtil.v("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CmdUtil.v("onDestroy");

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        CmdUtil.v("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CmdUtil.v("onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CmdUtil.v("onActivityResult");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        CmdUtil.v("onTrimMemory");
    }

    public void startNewActivity(View view) {
        startActivity(new Intent(this, Lifecycle2Activity.class));
    }

    public void startDialogFragment(View view) {
        startActivity(new Intent(this, LifecycleActivity.class));
    }

    public void startNewDialogActivity(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void startIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }
}
