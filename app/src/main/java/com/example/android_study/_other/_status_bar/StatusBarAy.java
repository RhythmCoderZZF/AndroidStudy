package com.example.android_study._other._status_bar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.android_study.R;
import com.example.android_study._other._status_bar.util.StatusBarUtil;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.cmd.CmdUtil;
import com.example.android_study.util.CommonUtils;
import com.example.android_study.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * 状态栏适配
 * 1. 状态栏也是DecorView的一部番
 */
public class StatusBarAy extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cb_1)
    CheckBox cb1;
    @BindView(R.id.cb_2)
    CheckBox cb2;
    @BindView(R.id.cb_3)
    CheckBox cb3;
    @BindView(R.id.cb_4)
    CheckBox cb4;
    @BindView(R.id.fl_3)
    View fl3;
    @BindView(R.id.fl_2)
    View fl2;
    @BindView(R.id.fl_1)
    FrameLayout fl;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private int option;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status_bar_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        ToolbarHelper.setBar(mContext,"状态栏适配");
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @OnCheckedChanged({R.id.cb_1, R.id.cb_2, R.id.cb_3, R.id.cb_4, R.id.cb_5, R.id.cb_6, R.id.cb_7, R.id.cb_8, R.id.cb_9})
    public void onCheckedChanged(CompoundButton button, boolean checked) {
        switch (button.getId()) {
            case R.id.cb_1:
                if (checked)
                    option |= View.SYSTEM_UI_FLAG_FULLSCREEN;
                else
                    option &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
                break;
            case R.id.cb_2:
                if (checked)
                    option |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                else
                    option &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                break;
            case R.id.cb_3:
                if (checked)
                    option |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                else
                    option &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                break;
            case R.id.cb_4:
                if (checked)
                    option |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                else
                    option &= ~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                break;
            case R.id.cb_5:
                if (checked)
                    option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                else
                    option &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            case R.id.cb_6:
                if (checked)
                    rl.setFitsSystemWindows(true);
                else
                    rl.setFitsSystemWindows(false);
                break;
            case R.id.cb_7:
                if (checked)
                    fl.setFitsSystemWindows(true);
                else
                    fl.setFitsSystemWindows(false);
                break;
            case R.id.cb_8:
                if (checked)
                    fl2.setFitsSystemWindows(true);
                else
                    fl2.setFitsSystemWindows(false);
                break;
            case R.id.cb_9:
                if (checked)
                    fl3.setFitsSystemWindows(true);
                else
                    fl3.setFitsSystemWindows(false);
                break;
        }

        StatusBarUtil.setSystemUi(mContext, option);
    }

    public void onRandomColor(View view) {
        int i = CommonUtils.randomColor();
        StatusBarUtil.setStatusBarColor(mContext, i);
    }

    public void onTransparentColor(View view) {
        StatusBarUtil.setStatusBarColor(mContext, Color.TRANSPARENT);
    }

    public void onTest(View view) {
        CmdUtil.i("");
        CmdUtil.i("rl0: " + rl.getPaddingTop());
        CmdUtil.i("fl1: " + fl.getPaddingTop());
        CmdUtil.i("fl2: " + fl2.getPaddingTop());
        CmdUtil.i("fl3: " + fl3.getPaddingTop());
    }

    public void onRemove(View view) {
    }
}
