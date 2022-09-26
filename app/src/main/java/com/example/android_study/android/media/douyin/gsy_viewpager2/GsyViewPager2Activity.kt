package com.example.android_study.android.media.douyin.gsy_viewpager2

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.databinding.ItemMediaDouyinGsyBinding
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.activity_gsy_view_pager2.*

class GsyViewPager2Activity : BaseActivity() {
    private val videoList = mutableListOf(
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/aa669338280dbf1f39034ecbb147f7b1.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/7cba946c7773b12cb6cc06c20ec84abf.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/15139f236888f45a4e8e1b964eb151f2.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12e2a93631b384add8b1951d2cac6461.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/aa669338280dbf1f39034ecbb147f7b1.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/7cba946c7773b12cb6cc06c20ec84abf.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/15139f236888f45a4e8e1b964eb151f2.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12e2a93631b384add8b1951d2cac6461.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/aa669338280dbf1f39034ecbb147f7b1.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/7cba946c7773b12cb6cc06c20ec84abf.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/15139f236888f45a4e8e1b964eb151f2.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12e2a93631b384add8b1951d2cac6461.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/aa669338280dbf1f39034ecbb147f7b1.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/7cba946c7773b12cb6cc06c20ec84abf.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/15139f236888f45a4e8e1b964eb151f2.mp4",
            "http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/12e2a93631b384add8b1951d2cac6461.mp4",
    )

    override fun getLayoutId() = R.layout.activity_gsy_view_pager2

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)

    }

    override fun run() {
        viewPager.apply {
            adapter = GSYAdapter().apply {
                submitList(videoList)
            }
        }

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //大于0说明有播放
                val playPosition = GSYVideoManager.instance().playPosition
                if (playPosition >= 0) {
                    //对应的播放列表TAG
                    if (position != playPosition) {
                        playPosition(position)
                    }
                }
            }
        })
        viewPager.post { playPosition(0) }
    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }


    private fun playPosition(position: Int) {
        val viewHolder = (viewPager.getChildAt(0) as RecyclerView).findViewHolderForAdapterPosition(position)
        if (viewHolder != null) {
            val vh=viewHolder as BaseViewHolder<ItemMediaDouyinGsyBinding>
            vh.binding.player.startPlayLogic()
        }
    }
}
