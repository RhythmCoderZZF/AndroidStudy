package com.example.android_study.android.media.douyin.jz_recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JZDataSource
import cn.jzvd.Jzvd
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.media.douyin.jz_recyclerview.TikTokRecyclerViewAdapter.TikTokVH
import com.example.android_study.android.media.videoList

class TikTokRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<TikTokVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokVH {
        return TikTokVH(LayoutInflater.from(
                context).inflate(R.layout.item_media_douyin_jz, parent,
                false))
    }

    @SuppressLint("LongLogTag")
    override fun onBindViewHolder(holder: TikTokVH, position: Int) {
        CmdUtil.v("onBindViewHolder [" + holder.jzvdStd.hashCode() + "] position=" + position)
        val jzDataSource = JZDataSource(videoList[position])
        jzDataSource.looping = true
        holder.jzvdStd.setUp(jzDataSource, Jzvd.SCREEN_NORMAL, JZMediaExo::class.java)
//        holder.jzvdStd.startPreloading()绝对不能在这里加
//        Glide.with(holder.jzvdStd.context).load(Urls.videoPosters.get(0).get(position)).into(holder.jzvdStd.posterImageView)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class TikTokVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var jzvdStd: JzvdStdTikTokPlayer = itemView.findViewById(R.id.videoplayer)

    }

    companion object {
        const val TAG = "AdapterTikTokRecyclerView"
    }
}