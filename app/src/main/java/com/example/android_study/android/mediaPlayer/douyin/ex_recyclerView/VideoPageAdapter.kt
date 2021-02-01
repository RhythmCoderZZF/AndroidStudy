package com.example.android_study.android.mediaPlayer.douyin.ex_recyclerView

import android.media.MediaPlayer
import android.view.SurfaceHolder
import androidx.recyclerview.widget.DiffUtil
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.databinding.ItemMediaDouYinRecyclerViewBinding

/**
 * Author:create by RhythmCoder
 * Date:2020/12/6
 * Description:
 */


class VideoPageAdapter : BaseDataAdapter<String, ItemMediaDouYinRecyclerViewBinding>(DIFF_CALLBACK) {
    private var firstViewPagerFlag = true
    var firstViewPagerInitListener: ((BaseViewHolder<*>) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = false
            override fun areContentsTheSame(oldItem: String, newItem: String) = false
        }
    }

    override fun getLayoutId() = R.layout.item_media_dou_yin_recycler_view

    override fun setVariable(data: String, position: Int, holder: BaseViewHolder<ItemMediaDouYinRecyclerViewBinding>) {
        CmdUtil.v("加载$position")

        val mMediaPlayer = MediaPlayer()
//        holder.mediaPlayer = mMediaPlayer
        holder.binding.root.tag=position
        mMediaPlayer.apply {
            setOnPreparedListener {
                seekTo(1)
                if (position == 0 && firstViewPagerFlag) {
                    firstViewPagerFlag = false
                    firstViewPagerInitListener?.invoke(holder)
                    mMediaPlayer.start()
                }
            }
            setDataSource(data)
            prepareAsync()
        }

        holder.binding.surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                CmdUtil.v("surface${position}创建")
                mMediaPlayer.setDisplay(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                CmdUtil.v("surface${position}改变[$format $width $height]")
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                CmdUtil.v("surface${position}销毁")
            }
        })
    }

    override fun onViewRecycled(holder: BaseViewHolder<ItemMediaDouYinRecyclerViewBinding>) {
        super.onViewRecycled(holder)
//        holder.mediaPlayer?.release()
//        holder.mediaPlayer = null

        CmdUtil.i("${holder.binding.root.tag as Int} 被销毁了！")
    }

}