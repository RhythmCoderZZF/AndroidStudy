package com.example.android_study.ui.recyclerView.section

/**
 * Author:create by RhythmCoder
 * Date:2020/12/28
 * Description:
 */
data class OriginBean(
        var string: String,
        var sub: List<Sub>,
)

data class Sub(var str: String)
