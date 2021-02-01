package com.example.android_study.java.juc.interrupt;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.cmd.CmdUtil;

import butterknife.BindView;

public class InterruptActivity extends BaseActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    private Thread t0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_juc_interrupt;
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        CmdUtil.showWindow();

        //1. 中断sleep线程（wait同理）————————————————————————————————————————————————————————————————————————————————
        button.setOnClickListener(v -> {
            t0 = new Thread(() -> {
                CmdUtil.v(Thread.currentThread().getName() + "start... isInterrupted=" + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // 被interrupt后会抛异常，且标志位重新被置为false
                    CmdUtil.e(Thread.currentThread().getName() + "Interrupted! isInterrupted=" + Thread.currentThread().isInterrupted());
                }
                CmdUtil.v(Thread.currentThread().getName() + "finish... isInterrupted=" + Thread.currentThread().isInterrupted());

            });
            t0.start();
            t0.setName("A ");
        });
        button1.setOnClickListener(v -> {
            t0.interrupt();
        });

        //2. 中断正常运行线程————————————————————————————————————————————————————————————————————————————————
        button2.setOnClickListener(v -> {
            t0 = new Thread(() -> {
                CmdUtil.v(Thread.currentThread().getName() + "start... isInterrupted=" + Thread.currentThread().isInterrupted());
                long start = System.currentTimeMillis();
                while (!t0.isInterrupted()) {
                    if (System.currentTimeMillis() - start > 1000) {
                        CmdUtil.i(Thread.currentThread().getName() + "running... isInterrupted=" + Thread.currentThread().isInterrupted());
                        start = System.currentTimeMillis();
                    }
                }
                CmdUtil.e(Thread.currentThread().getName() + "Interrupted! isInterrupted=" + Thread.currentThread().isInterrupted());
            });
            t0.setName("B ");
            t0.start();
        });

        button3.setOnClickListener(v -> {
            t0.interrupt();
        });

        //3. 中断线程while循环(两阶段终止)————————————————————————————————————————————————————————————————————————————————
        button4.setOnClickListener(v -> {
            t0 = new Thread(() -> {
                CmdUtil.v(Thread.currentThread().getName() + "start... isInterrupted=" + Thread.currentThread().isInterrupted());
                while (!t0.isInterrupted()) {
                    try {
                        t0.sleep(1000);
                        CmdUtil.i(Thread.currentThread().getName() + "running... isInterrupted=" + Thread.currentThread().isInterrupted());
                    } catch (InterruptedException e) {
                        CmdUtil.e(Thread.currentThread().getName() + "Interrupted! isInterrupted=" + Thread.currentThread().isInterrupted());
                        //这里需要加上interrupt()否则不会跳出循环
                        t0.interrupt();
                    }
                }
                CmdUtil.v(Thread.currentThread().getName() + "finish... isInterrupted=" + Thread.currentThread().isInterrupted());
            });
            t0.setName("C ");
            t0.start();
        });

        button5.setOnClickListener(v -> {
            t0.interrupt();
        });

    }
}