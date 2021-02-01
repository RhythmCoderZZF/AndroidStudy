package com.example.android_study.android.mediaPlayer.douyin.gsy_viewpager2

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.databinding.ItemMediaDouyinGsyBinding
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack

/**
 * Author:create by RhythmCoder
 * Date:2020/12/7
 * Description:
 */
class GSYAdapter : BaseDataAdapter<String, ItemMediaDouyinGsyBinding>(DIFF_CALLBACK) {
    private val gsyVideoOptionBuilder = GSYVideoOptionBuilder()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    override fun getLayoutId() = R.layout.item_media_douyin_gsy

    override fun setVariable(data: String, position: Int, holder: BaseViewHolder<ItemMediaDouyinGsyBinding>) {
        val gsyVideoPlayer = holder.binding.player

        //防止错位，离开释放
        //gsyVideoPlayer.initUIState();
        gsyVideoOptionBuilder
                .setIsTouchWiget(false) //.setThumbImageView(imageView)
                .setUrl(data)
//                .setVideoTitle(title)
                .setCacheWithPlay(false)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag("zzf")
//                .setMapHeadData(header)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(position)
                .setVideoAllCallBack(object : GSYSampleCallBack() {
                    override fun onStartPrepared(url: String?, vararg objects: Any?) {
                        super.onStartPrepared(url, *objects)
                        CmdUtil.v("player$position:onStartPrepared")
                    }
                    override fun onPrepared(url: String, vararg objects: Any) {
                        super.onPrepared(url, *objects)
                        CmdUtil.v("player$position:onPrepared")
//                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen) {
//                            //静音
//                            GSYVideoManager.instance().isNeedMute = true
//                        }
                    }

                    override fun onQuitFullscreen(url: String, vararg objects: Any) {
                        super.onQuitFullscreen(url, *objects)
                        //全屏不静音
//                        GSYVideoManager.instance().isNeedMute = true
                    }

                    override fun onEnterFullscreen(url: String, vararg objects: Any) {
                        super.onEnterFullscreen(url, *objects)
//                        GSYVideoManager.instance().isNeedMute = false
//                        gsyVideoPlayer.currentPlayer.titleTextView.text = objects[0] as String
                    }
                }).build(gsyVideoPlayer)
//        //增加title
//        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE)
//
//
//        //设置返回键
//        gsyVideoPlayer.getBackButton().setVisibility(View.GONE)

        //设置全屏按键功能
//        gsyVideoPlayer.getFullscreenButton().setOnClickListener(View.OnClickListener { resolveFullBtn(gsyVideoPlayer) })


//        封面
//        gsyVideoPlayer.loadCoverImageBy(R.mipmap.xxx2, R.mipmap.xxx2)
    }

//    /**
//     * 全屏幕按键处理
//     */
//    private fun resolveFullBtn(standardGSYVideoPlayer: StandardGSYVideoPlayer) {
//        standardGSYVideoPlayer.startWindowFullscreen(context, true, true)
//    }
}