package com.example.android_study.android.media.douyin.ex_viewpager2

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_media_player_dou_yin_view_pager2.*

class MediaPlayerViewPager2Activity : BaseActivity() {

    private val videoList = mutableListOf(
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/aa669338280dbf1f39034ecbb147f7b1.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/7cba946c7773b12cb6cc06c20ec84abf.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/15139f236888f45a4e8e1b964eb151f2.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12e2a93631b384add8b1951d2cac6461.mp4"
    )

    override fun getLayoutId() = R.layout.activity_media_player_dou_yin_view_pager2

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    override fun run() {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = videoList.size

            override fun createFragment(position: Int): MediaPlayerVideoPlayFragment {
                return MediaPlayerVideoPlayFragment(position, videoList[position])
            }
        }
        viewPager.offscreenPageLimit = 1
    }
}