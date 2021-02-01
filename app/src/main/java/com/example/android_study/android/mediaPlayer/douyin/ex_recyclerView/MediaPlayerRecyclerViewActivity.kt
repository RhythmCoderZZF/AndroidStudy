package com.example.android_study.android.mediaPlayer.douyin.ex_recyclerView

import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_media_player_recycler_view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MediaPlayerRecyclerViewActivity : BaseActivity() {
    private val mAdapter = VideoPageAdapter()
    private val mSnapHelper = PagerSnapHelper()
    private var mCurrMediaPlayer: MediaPlayer? = null
    private var currentPosition = 0

    private var loopStartJob: Job? = null
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

    override fun getLayoutId() = R.layout.activity_media_player_recycler_view

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)

    }

    override fun run() {
        mSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MediaPlayerRecyclerViewActivity)
            adapter = mAdapter.apply {
//                firstViewPagerInitListener = {
//                    mCurrMediaPlayer = it.mediaPlayer
//                }
                submitList(videoList)
            }

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            CmdUtil.v("停止滚动")
                            //找到当前最中间的view
                            val view = mSnapHelper.findSnapView(recyclerView.layoutManager)!!
                            //当前固定后的item position
                            val position = recyclerView.getChildAdapterPosition(view)
                            CmdUtil.e("currpos:$currentPosition pos:$position")
                            if (currentPosition != position) {
                                //如果当前position 和 上一次固定后的position 相同, 说明是同一个, 只不过滑动了一点点, 然后又释放了

                                val viewHolder = recyclerView.getChildViewHolder(view) as BaseViewHolder<*>
//                                val viewHolder=recyclerView.findViewHolderForAdapterPosition(position) as BaseViewHolder<*>
                                loopStartJob?.cancel()
//                                loopStartJob = lifecycleScope.launchWhenResumed {
//                                    while (viewHolder.mediaPlayer?.isPlaying == false) {
//                                        viewHolder.mediaPlayer?.start()
//                                        delay(500)
//                                    }
//                                }
//                                mCurrMediaPlayer = viewHolder.mediaPlayer
                            }
                            currentPosition = position
                        }
                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                        }
                        RecyclerView.SCROLL_STATE_SETTLING -> {
                        }
                    }

                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        mCurrMediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        mCurrMediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCurrMediaPlayer?.release()
        mCurrMediaPlayer = null
    }
}