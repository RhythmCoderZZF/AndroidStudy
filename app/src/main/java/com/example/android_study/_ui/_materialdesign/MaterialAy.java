package com.example.android_study._ui._materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.android_study.R;
import com.example.android_study._other._status_bar.util.StatusBarUtil;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.cmd.CmdUtil;
import com.example.android_study.util.ToolbarHelper;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;

/**
 * Material Design 控件
 */
public class MaterialAy extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_metarial_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ToolbarHelper.setBar(mContext, "Material Design");
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.TRANSPARENT);
        StatusBarUtil.setSystemUi(this, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    public void onFloatButtonClick(View view) {
        Snackbar home = Snackbar.make(view, "HOME", Snackbar.LENGTH_SHORT);
        home.setAction("dismiss", v -> home.dismiss()).show();
        CmdUtil.e("\ncollapsingToolbarLayout->" + collapsingToolbarLayout.getPaddingTop());
        CmdUtil.e("appBar->" + appBar.getPaddingTop());
        CmdUtil.e("coordinatorLayout->" + coordinatorLayout.getPaddingTop());
    }
}
