package com.example.android_study;

import org.junit.Test;

import kotlin.jvm.functions.Function1;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void t() throws InterruptedException {;
        Thread t = new Thread(() -> {
            int i=1/0;
            System.out.println("am i reached?");
        });
        t.start();
        t.join();
        System.out.println("success!");
    }
    @Test
    public void main() throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, throwable) -> {
            System.out.println("全局：" + throwable.getMessage());
        });
        Thread t = new Thread(() -> {
            throw new RuntimeException(";)");
        });
        t.setUncaughtExceptionHandler((t1, throwable) -> {
            System.out.println("私有：" + throwable.getMessage());
        });
        t.start();
        t.join();
    }

    @Test
    public void main1() throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, throwable) -> {
            System.out.println("全局：" + throwable.getMessage());
        });
        Thread t = new Thread(() -> {
            throw new RuntimeException(";)");
        });
        t.start();
        t.join();
    }

    @Test
    public void testEnum() {
    }

    private enum E{

    }
}