package com.example.android_study.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study.base.adapter.Entry;
import com.example.android_study.base.adapter.RvAdapter;
import com.example.android_study.base.cmd.CmdUtil;
import com.example.android_study.util.LogUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 1. 基类
 * <p>
 * 2. cmd命令行
 * onCreate 中自动绑定Cmd Service（全局公用一个连接）
 * onDestroy中自动解除绑定
 * 显示隐藏cmd 调用 cmd.showWindow\dismissWindow
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unBinder;
    protected BaseActivity mContext = this;
    protected boolean showLifecycle;//向cmd打印生命周期
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        initViewAndData(savedInstanceState);

        initToolbar();

        //绑定连接cmd
        CmdUtil.connectCmd(this);
        if (showLifecycle)
            CmdUtil.v("onCreate");
    }

    private void initToolbar() {
        TextView tv = findViewById(R.id.tv_title);
        ImageView iv = findViewById(R.id.iv_toolbar_right);
        String title = getIntent().getStringExtra("title");
        if (iv != null) {
            iv.setOnClickListener(view -> {
                CmdUtil.showWindow();
            });
        }
        if (tv != null && !TextUtils.isEmpty(title)) {
            tv.setText(title);
        }
    }

    protected abstract int getLayoutId();


    protected abstract void initViewAndData(@Nullable Bundle savedInstanceState);


    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (showLifecycle)
            CmdUtil.v("onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (showLifecycle)
            CmdUtil.v("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.v("topActivity", this.getClass().getSimpleName());
        if (showLifecycle)
            CmdUtil.v("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (showLifecycle)
            CmdUtil.v("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (showLifecycle)
            CmdUtil.v("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
        if (showLifecycle)
            CmdUtil.v("onDestroy");
        //解除cmd绑定
        CmdUtil.disConnectCmd(this);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (showLifecycle)
            CmdUtil.v("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (showLifecycle)
            CmdUtil.v("onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CmdUtil.onActivityResult(requestCode);
        if (showLifecycle)
            CmdUtil.v("onActivityResult");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (showLifecycle)
            CmdUtil.v("onTrimMemory");
    }

    protected void addMainPageAdapter(RecyclerView rv, List<Entry> list) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RvAdapter(list));
        rv.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }
}
