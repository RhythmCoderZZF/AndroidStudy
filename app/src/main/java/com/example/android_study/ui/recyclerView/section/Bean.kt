package com.example.android_study.ui.recyclerView.section

/**
 * Author:create by RhythmCoder
 * Date:2020/12/28
 * Description:
 */

sealed class Bean() {
    class Header(var string: String) : Bean()
    class Content(var string: String,var show:Boolean) : Bean()
    class Footer(val itemIndex: Int,val notifyStartIndex:Int,var show:Boolean,var contentShowSize:Int= DEFAULT_SHOW_LIST) : Bean()
}