package com.example.android_study.ui_third.BasePopup

import android.content.Context
import com.example.android_study.R
import razerdp.basepopup.BasePopupWindow

/**
 * Author:create by RhythmCoder
 * Date:2020/9/20
 * Description:
 */
class DemoPopup(context: Context) : BasePopupWindow(context) {
    override fun onCreateContentView() = createPopupById(R.layout.activity_item_touch_hel_recycler_view)!!;
}