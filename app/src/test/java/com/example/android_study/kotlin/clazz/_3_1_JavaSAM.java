package com.example.android_study.kotlin.clazz;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class _3_1_JavaSAM {
    @Test
    public void main() {
        f(() -> {});
    }

    private void f(Runnable action) {
        action.run();
    }
}