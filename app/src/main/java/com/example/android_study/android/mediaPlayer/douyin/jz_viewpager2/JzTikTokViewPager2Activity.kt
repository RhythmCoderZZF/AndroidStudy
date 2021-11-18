package com.example.android_study.android.mediaPlayer.douyin.jz_viewpager2

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cn.jzvd.Jzvd
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.mediaPlayer.douyin.jz_recyclerview.TikTokRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_gsy_view_pager2.*
import kotlinx.android.synthetic.main.activity_media_player_dou_yin_view_pager2.*
import kotlinx.android.synthetic.main.activity_media_player_dou_yin_view_pager2.viewPager

class JzTikTokViewPager2Activity : BaseActivity() {
    private var currPlayPosition=0
    override fun getLayoutId()=R.layout.activity_jz_tik_tok_view_pager2

    override fun initViewAndData(savedInstanceState: Bundle?) {
        viewPager.adapter =TikTokRecyclerViewAdapter(this)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //大于0说明有播放
                if (currPlayPosition >= 0) {
                    //对应的播放列表TAG
                    if (position != currPlayPosition) {
                        autoPlayVideo(position)
                    }
                }
                currPlayPosition=position
            }
        })
        viewPager.offscreenPageLimit=1
        viewPager.post {
            autoPlayVideo(0)
        }
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }


    private fun autoPlayVideo(position: Int) {
        val viewHolder = (viewPager.getChildAt(0) as RecyclerView).findViewHolderForAdapterPosition(position)
        if (viewHolder != null) {

            val vh=viewHolder as TikTokRecyclerViewAdapter.TikTokVH
            CmdUtil.v("hash [" +vh.jzvdStd.hashCode() + "]")
            vh.jzvdStd.startVideoAfterPreloading()
        }
    }


}
