package com.example.android_study.Java.Throwable;

/**
 * Author:create by RhythmCoder
 * Date:2021/12/12
 * Description:
 */

public class Test {
    @org.junit.Test
    public void main() {
        try {
            err();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("ok");
    }

    private void err() {
        throw new IllegalArgumentException("haha");
    }
}
