package com.example.android_study.view._recyclerview.nest_recycler.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study._base.cmd.CmdUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: create by RhythmCoder
 * Date: 2020/5/22
 * Description:
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> {
    private LinearLayoutManager mLayoutMagager;
    List<Datas> mDatas;
    Context mContext;

    public RvAdapter(Context context, List<Datas> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    public RvAdapter(LinearLayoutManager linearLayoutManager, Context context, List<Datas> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutMagager = linearLayoutManager;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CmdUtil.v("onCreateViewHolder");
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_gesture, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CmdUtil.v("onBindViewHolder - " + position);
        holder.tvTitle.setText(mDatas.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tvTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public int getCurrentViewIndex() {
        int firstVisibleItem = mLayoutMagager.findFirstVisibleItemPosition();
        int lastVisibleItem = mLayoutMagager.findLastVisibleItemPosition();
        int firstVisibleItem1 = mLayoutMagager.findFirstCompletelyVisibleItemPosition();
        int lastVisibleItem1 = mLayoutMagager.findLastCompletelyVisibleItemPosition();

//        Cmd.v("zzz", firstVisibleItem + " " + firstVisibleItem1 + " " + lastVisibleItem + " " + lastVisibleItem1);

        int currentIndex = firstVisibleItem;
        int lastHeight = 0;
        for (int i = firstVisibleItem; i <= lastVisibleItem; i++) {
            View view = mLayoutMagager.findViewByPosition(i - firstVisibleItem);
            if (null == view) {
                continue;
            }
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            Rect localRect = new Rect();
            view.getLocalVisibleRect(localRect);
            int showHeight = localRect.bottom - localRect.top;
            if (showHeight > lastHeight) {
                currentIndex = i;
                lastHeight = showHeight;
            }
        }

        if (currentIndex < 0) {
            currentIndex = 0;
        }
        return currentIndex;
    }
}
