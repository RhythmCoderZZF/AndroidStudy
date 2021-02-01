package com.example.android_study.kotlin._study

import java.lang.StringBuilder

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/30
 * Description:
 */

fun StringBuilder.add(s: String): StringBuilder {
    apply { append("\n").append(s) }
    return this
}