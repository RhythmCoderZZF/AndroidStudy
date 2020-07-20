package com.example.android_study._performance_optimization;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study._other._status_bar.util.StatusBarUtil;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.cmd.CmdUtil;
import com.example.android_study.util.ToolbarHelper;

import butterknife.BindView;

/**
 * 模拟内存泄漏
 */
public class LeakCanaryAy extends BaseActivity {
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.content)
    TextView tvContent;
    @BindView(R.id.btn_clear)
    Button btnClear;

    private int flag;

    private static final int FLAG_THREAD_LEAK = 0; //0. 子线程以匿名内部类形式内存泄漏
    private Thread mThreadLeak;

    private static final int FLAG_THREAD_LEAK_1 = 1; //1. 子线程引用父类内存泄漏1
    private Thread mMyThreadLeak;

    private static class MyThread extends Thread {
        private Activity mObject;

        public MyThread(Activity object) {
            mObject = object;
        }

        @Override
        public void run() {
            try {
                CmdUtil.e("Thread:" + Thread.currentThread().getName() + "begin sleep");
                Thread.sleep(8000);
                CmdUtil.e("Thread:" + Thread.currentThread().getName() + "wake up!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final int FLAG_HANDLER_LEAK = 2; //2. Handler泄漏
    private Handler mHandlerLeak;

    private static final int FLAG_STATIC_ACTIVITY_LEAK = 3; //3. static activity
    private static Activity mActivityLeak;

    private static final int FLAG_STATIC_VIEW_LEAK = 4; //4. static view
    private static View mViewLeak;

    private static final int FLAG_STATIC_INNER_CLASS_LEAK = 5; //5. static inner class
    private static Object mObjectLeak;

    @Override
    protected int getLayoutId() {
        showLifecycle = true;
        return R.layout.activity_leak_canary_ay;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        ToolbarHelper.setBar(mContext, "内存泄漏", R.mipmap.ic_cmd);

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_thread:
                    flag = FLAG_THREAD_LEAK;
                    tvContent.setText("Thread泄漏1\nThread以内部类或匿名内部类形式声明，执行耗时任务，若在activity销毁前未完成则会发生泄漏——内部类，匿名内部类创建会引用外部类\n（请在8秒内destroy该activity查看泄漏信息）");
                    btnClear.setVisibility(View.GONE);

                    mThreadLeak = new Thread() {
                        @Override
                        public void run() {
                            try {
                                CmdUtil.e("Thread:" + Thread.currentThread().getName() + "begin sleep");
                                Thread.sleep(8000);
                                CmdUtil.e("Thread:" + Thread.currentThread().getName() + "wake up!");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    break;
                case R.id.rb_thread_1:
                    flag = FLAG_THREAD_LEAK_1;
                    tvContent.setText("Thread泄漏2\nThread以静态内部类或外部类形式声明，执行耗时任务且引用activity，若在activity销毁前未完成则会发生泄漏\n（请在8秒内destroy该activity查看泄漏信息）");
                    btnClear.setVisibility(View.GONE);

                    mMyThreadLeak = new MyThread(this);

                    break;
                case R.id.rb_handler:
                    flag = FLAG_HANDLER_LEAK;
                    tvContent.setText("Handler泄漏\n注意Handler声明为内部类或匿名内部类时，未处理完message会引起activity泄漏；若未声明，且runnable未引用activity或任何成员属性，则不会引起泄漏\n（请在8秒内destroy该activity查看泄漏信息");
                    btnClear.setVisibility(View.GONE);

                    mHandlerLeak = new Handler() {
                    };

                    break;
                case R.id.rb_static_activity:
                    flag = FLAG_STATIC_ACTIVITY_LEAK;
                    tvContent.setText("static activity泄漏\nstatic修饰activity\n（destroy该activity查看泄漏信息）");
                    btnClear.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_static_view:
                    flag = FLAG_STATIC_VIEW_LEAK;
                    tvContent.setText("static View泄漏\nstatic修饰view\n（destroy该activity查看泄漏信息）");
                    btnClear.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_static_inner_class:
                    flag = FLAG_STATIC_INNER_CLASS_LEAK;
                    tvContent.setText("static Inner Class内部类泄漏\nstatic修饰内部类\n（destroy该activity查看泄漏信息）");
                    btnClear.setVisibility(View.VISIBLE);
                    break;
            }
        });
        rg.check(R.id.rb_thread);
    }

    /**
     * 开启泄漏
     *
     * @param view
     */
    public void start(View view) {
        switch (flag) {
            case FLAG_THREAD_LEAK:
                mThreadLeak.start();
                break;
            case FLAG_THREAD_LEAK_1:
                mMyThreadLeak.start();
                break;
            case FLAG_HANDLER_LEAK:
                CmdUtil.e("Handler Send Message");
                mHandlerLeak.postDelayed(() -> {
                    CmdUtil.e("Handler Message Received!");
                }, 8000);
                break;
            case FLAG_STATIC_ACTIVITY_LEAK:
                CmdUtil.e("绑定activity到static field");
                mActivityLeak = this;
                break;
            case FLAG_STATIC_VIEW_LEAK:
                CmdUtil.e("绑定view到static field");
                mViewLeak = rg;
                break;
            case FLAG_STATIC_INNER_CLASS_LEAK:
                CmdUtil.e("绑定inner class 到static field");
                class InnerClass {
                }
                mObjectLeak = new InnerClass();
                break;
        }
    }

    /**
     * 清除泄漏
     *
     * @param view
     */
    public void clear(View view) {
        switch (flag) {
            case FLAG_STATIC_ACTIVITY_LEAK:
                CmdUtil.e("清除static activity");
                mActivityLeak = null;
                break;
            case FLAG_STATIC_VIEW_LEAK:
                CmdUtil.e("清除static view");
                mViewLeak = null;
                break;
            case FLAG_STATIC_INNER_CLASS_LEAK:
                CmdUtil.e("清除static inner class");
                mObjectLeak = null;
                break;
        }
    }
}
