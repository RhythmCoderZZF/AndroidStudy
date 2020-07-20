package com.example.android_study._ui._popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study._ui._popupwindow.util.PopupWindowUtil;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.util.DensityUtil;

import butterknife.BindView;

/**
 * 第三方 popupWindow
 */
public class PopupWindowAy extends BaseActivity {
    @BindView(R.id.rg)
    RadioGroup rg;
    private PopupWindow mPopupWindow;

    private boolean changeMethod;
    private int flag;
    private static final int FLAG_LEFT = 0;
    private static final int FLAG_TOP = 1;
    private static final int FLAG_RIGHT = 2;
    private static final int FLAG_BOTTOM = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_popup_window_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        flag = FLAG_LEFT;
                        break;
                    case R.id.rb_top:
                        flag = FLAG_TOP;
                        break;
                    case R.id.rb_right:
                        flag = FLAG_RIGHT;
                        break;
                    case R.id.rb_bottom:
                        flag = FLAG_BOTTOM;
                        break;
                }
            }
        });
        rg.check(R.id.rb_left);
    }

    public void onLeftTopClick(View view) {
        changeMethod = false;
        showPopupWindow(view);
    }

    public void onRightTopClick(View view) {
        changeMethod = false;
        showPopupWindow(view);
    }

    public void onLeftBottomClick(View view) {
        changeMethod = false;
        showPopupWindow(view);
    }

    public void onRightBottomClick(View view) {
        changeMethod = false;
        showPopupWindow(view);
    }

    public void onParentCenterClick(View view) {
        changeMethod = true;
        showPopupWindow(view);
    }


    /**
     * 弹出 popupWindow
     *
     * @param anchorView 锚点view
     */
    private void showPopupWindow(View anchorView) {
        View contentView = getPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //1. 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //2. 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        //3. 偏移
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView);
        int xOff = 20; // 可以自己调整偏移
//        int yOff = 2; // 可以自己调整偏移
//        windowPos[0] -= xOff;
        // windowPos[1] = yOff;

        //4. showAtLocation相交于anchorView的rootView;showAsDropDown相较于anchorView
        if (!changeMethod) {
            mPopupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, windowPos[0], windowPos[1]);
        } else {
            int[] viewWidthAndHeight = PopupWindowUtil.getViewWidthAndHeight(contentView);
            switch (flag) {
                case FLAG_LEFT:
                    mPopupWindow.showAsDropDown(anchorView, -viewWidthAndHeight[0], -anchorView.getHeight());
                    break;
                case FLAG_TOP:
                    mPopupWindow.showAsDropDown(anchorView, 0, -anchorView.getHeight() - viewWidthAndHeight[1]);
                    break;
                case FLAG_RIGHT:
                    mPopupWindow.showAsDropDown(anchorView, 0, -anchorView.getHeight(), Gravity.END);
                    break;
                case FLAG_BOTTOM:
                    mPopupWindow.showAsDropDown(anchorView, 0, 0);
                    break;
            }
        }
    }

    /**
     * 获取popupWindow content View，并设置点击监听
     *
     * @return popupWindow content View
     */
    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.layout_popup_window_menu;
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = v -> {
            switch (v.getId()) {
                case R.id.menu_item1:
                case R.id.menu_item2:
                case R.id.menu_item3:
                case R.id.menu_item4:
            }
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        };
        TextView viewById = contentView.findViewById(R.id.menu_item1);
        viewById.setOnClickListener(menuItemOnClickListener);
        fixDrawableSize(viewById, R.mipmap.icon_homepage);

        TextView viewById1 = contentView.findViewById(R.id.menu_item2);
        viewById1.setOnClickListener(menuItemOnClickListener);
        fixDrawableSize(viewById1, R.mipmap.icon_homepage);

        TextView viewById2 = contentView.findViewById(R.id.menu_item3);
        viewById2.setOnClickListener(menuItemOnClickListener);
        fixDrawableSize(viewById2, R.mipmap.icon_homepage);

        TextView viewById3 = contentView.findViewById(R.id.menu_item4);
        viewById3.setOnClickListener(menuItemOnClickListener);
        fixDrawableSize(viewById3, R.mipmap.icon_homepage);
        return contentView;
    }

    /**
     * 修复TextView Drawable 大小
     *
     * @param view
     * @param drawableRes
     */
    private void fixDrawableSize(TextView view, int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        drawable.setBounds(0, 0, DensityUtil.dp2px(20), DensityUtil.dp2px(20));
        view.setCompoundDrawables(drawable, null, null, null);
    }


}
