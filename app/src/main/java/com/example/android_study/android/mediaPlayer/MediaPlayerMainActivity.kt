package com.example.android_study.android.mediaPlayer

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.mediaPlayer.douyin.ex_viewpager2.MediaPlayerViewPager2Activity
import com.example.android_study.android.mediaPlayer.douyin.gsy_viewpager2.GsyViewPager2Activity
import com.example.android_study.android.mediaPlayer.douyin.jz_recyclerview.JzTikTokActivity
import com.example.android_study.android.mediaPlayer.douyin.jz_viewpager2.JzTikTokViewPager2Activity
import com.example.android_study.android.mediaPlayer.custom_mediaplayer.MediaPlayerCustomActivity
import com.example.android_study.android.mediaPlayer.douyin.ex_recyclerView.MediaPlayerRecyclerViewActivity
import com.example.android_study.android.mediaPlayer.simple.MediaPlayerSimpleActivity
import kotlinx.android.synthetic.main.activity_media_player_main.*

val videoList = mutableListOf(
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

class MediaPlayerMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_media_player_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(
            rv, listOf(
                Entry("1. VideoView播放", MediaPlayerSimpleActivity::class.java, "简单播放"),
                Entry("2. 自定义MediaPlayer播放", MediaPlayerCustomActivity::class.java, "简单播放"),
                Entry(
                    "3. 仿抖音",
                    MediaPlayerViewPager2Activity::class.java,
                    "viewPager2+MediaPlayer"
                ),
                Entry(
                    "4. 仿抖音",
                    MediaPlayerRecyclerViewActivity::class.java,
                    "RecyclerView+MediaPlayer"
                ),
                Entry("5. 仿抖音_GSY", GsyViewPager2Activity::class.java, "ViewPager2+GSYVideoPlay"),
                Entry("6. 仿抖音_JZ", JzTikTokActivity::class.java, "JzVideoPlayer+RecyclerView"),
                Entry(
                    "7. 仿抖音_JZ_2",
                    JzTikTokViewPager2Activity::class.java,
                    "JzVideoPlayer+viewPager2"
                )
            )
        )
    }
}