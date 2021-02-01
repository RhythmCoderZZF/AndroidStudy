package com.example.android_study.ui._popupwindow.util;

import android.content.Context;
import android.view.View;


public class PopupWindowUtil {
    /**
     * 根据anchorView和屏幕的限制自动计算contentView需要显示的位置(默认显示在anchorView的正下方，与左侧对齐)
     *
     * @param anchorView  呼出window的锚点view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorWidth = anchorView.getWidth();
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());

        int[] viewWidthAndHeight = getViewWidthAndHeight(contentView);
        final int windowWidth = viewWidthAndHeight[0];
        final int windowHeight = viewWidthAndHeight[1];
        //1. 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight) < windowHeight;
        // 依据下方距离限制，判断需要左边弹出还是右边弹出
        if (isNeedShowUp) {
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        //2. 依据左侧距离限制，判断是左侧对齐还是右侧对齐
        final boolean isNeedShowAlignLeft = (screenWidth - anchorLoc[0]) > windowWidth;
        if (isNeedShowAlignLeft) {
            windowPos[0] = anchorLoc[0];
        } else {
            windowPos[0] = anchorLoc[0] + anchorWidth - windowWidth;
        }
        return windowPos;
    }

    /**
     * 计算view宽高
     *
     * @param view
     * @return
     */
    public static int[] getViewWidthAndHeight(View view) {
        int[] size = new int[2];
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
