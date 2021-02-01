package com.example.android_study.java.juc._production_consumption;

import com.example.android_study._base.cmd.CmdUtil;

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/27
 * Description:
 * Synchronize模拟生产消费：
 * 1. 判断count一定要使用while，多个线程被唤醒后必须再次根据count进行判断（此时的count大概率已经被其他线程修改，防止虚假唤醒）
 */
class SyncTicket {
    private int count = 0;

    public synchronized void increment() throws InterruptedException {
        CmdUtil.v(Thread.currentThread().getName() + "  increment <<<");
        while (count > 0) {
            wait();
        }
        count++;
        CmdUtil.i(Thread.currentThread().getName() + "生产1 > " + count);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        CmdUtil.v(Thread.currentThread().getName() + "  decrement <<<");
        while (count < 1) {
            wait();
        }
        count--;
        CmdUtil.i(Thread.currentThread().getName() + "消费1 > " + count);
        notifyAll();
    }
}
