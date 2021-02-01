package com.example.android_study._base.cmd;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.example.android_study.R;
import com.example.android_study._base.util.DensityUtil;
import com.example.android_study._base.util.LogUtil;


/**
 * Author: create by RhythmCoder
 * Date: 2020/6/12
 * Description: 浮窗service，模拟cmd方式显示浮窗log日志输出
 */
public class FloatViewService extends Service {
    private String TAG = this.getClass().getSimpleName();

    private ContentBinder binder = new ContentBinder();

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams params;
    private View mFloatingView;

    private TextView tvContent;//日志
    private TextView tvFps;//fps
    private ScrollView scrollView;

    public FloatViewService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.v(TAG, "onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.v(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.v(TAG, "onCreate");
        showWindow();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.v(TAG, "onDestroy");
        //移除FloatingView
        if (mWindowManager != null && mFloatingView != null) {
            mWindowManager.removeView(mFloatingView);
            mWindowManager = null;
            mFloatingView = null;
        }
    }


    public void showWindow() {
        //获取WindowManager对象
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //设置WindowManger布局参数以及相关属性
        params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Android 8.0
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            //其他版本
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params.format = PixelFormat.RGBA_8888;   //窗口透明
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //初始化位置
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.width = DensityUtil.dp2px(150);
        params.height = DensityUtil.dp2px(500);

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_view, null);


        initViewAndListener();
        mWindowManager.addView(mFloatingView, params);
    }

    private void initViewAndListener() {
        //内容
        tvContent = mFloatingView.findViewById(R.id.content);
        //fps
        tvFps = mFloatingView.findViewById(R.id.tv_fps);
        scrollView = mFloatingView.findViewById(R.id.scrollview);

        //清空
        mFloatingView.findViewById(R.id.iv_clear).setOnClickListener(view -> {
            tvContent.setText("");
        });

        //隐藏
        mFloatingView.findViewById(R.id.iv_close).setOnClickListener(v -> {
            binder.dismissWindow();
        });

        //拖动
        mFloatingView.findViewById(R.id.iv_move).setOnTouchListener(new View.OnTouchListener() {
            //获取X坐标
            private int startX;
            //获取Y坐标
            private int startY;
            //初始化X的touch坐标
            private float startTouchX;
            //初始化Y的touch坐标
            private float startTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        startX = params.x;
                        startY = params.y;
                        startTouchX = event.getRawX();
                        startTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = startX - (int) (event.getRawX() - startTouchX);
                        params.y = startY + (int) (event.getRawY() - startTouchY);
                        //更新View的位置
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Service Binder 通信类
     */
    public class ContentBinder extends Binder {
        /**
         * 向cmd添加数据
         *
         * @param s
         */
        public void appendContent(@ColorRes int colorRes, String s) {
            if (mFloatingView == null || mFloatingView.getVisibility() == View.GONE) {
                return;
            }
            mFloatingView.post(() -> {
                SpannableStringBuilder spannable = new SpannableStringBuilder(s);
                spannable.setSpan(new ForegroundColorSpan(getResources().getColor(colorRes)), 0, s.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                tvContent.append(spannable);
                tvContent.append("\n");

            });
            mFloatingView.postDelayed(() -> {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }, 100);
        }

        public void setFps(String fps, @ColorInt int colorInt) {
            if (mFloatingView != null)
                mFloatingView.post(() -> {
                    tvFps.setText(fps + "fps");
                    tvFps.setTextColor(colorInt);
                });
        }

        public void setFps(String fps) {
            if (mFloatingView != null)
                mFloatingView.post(() -> {
                    tvFps.setText(fps + "fps");
                    tvFps.setTextColor(Color.GRAY);
                });
        }

        /**
         * 开启 cmd
         */
        public void showWindow() {
            if (mFloatingView != null && mFloatingView.getVisibility() != View.VISIBLE) {
                mFloatingView.post(() -> {
                    mFloatingView.setVisibility(View.VISIBLE);
                });
                return;
            }
            if (mFloatingView == null)
                FloatViewService.this.showWindow();

        }

        /**
         * 隐藏 cmd
         */
        public void dismissWindow() {
            if (mFloatingView != null && mFloatingView.getVisibility() == View.VISIBLE) {
                mFloatingView.post(() -> {
                    mFloatingView.setVisibility(View.GONE);
                });
            }
        }
    }


}
