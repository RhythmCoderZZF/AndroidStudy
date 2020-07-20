package com.example.android_study._view._recyclerview.item_touch_helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study._view._recyclerview.item_touch_helper.onMoveAndSwipedListener;
import com.example.android_study._view._recyclerview.nest_recycler.adapter.Datas;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: create by RhythmCoder
 * Date: 2020/5/22
 * Description:
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> implements onMoveAndSwipedListener {
    private LinearLayoutManager mLayoutMagager;
    List<Datas> mItems;
    Context mContext;

    public RvAdapter(Context context, List<Datas> mDatas) {
        this.mContext = context;
        this.mItems = mDatas;
    }

    public RvAdapter(LinearLayoutManager linearLayoutManager, Context context, List<Datas> mDatas) {
        this.mContext = context;
        this.mItems = mDatas;
        this.mLayoutMagager = linearLayoutManager;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvTitle.setText(mItems.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);

//        Snackbar.make(parentView, context.getString(R.string.item_swipe_dismissed), Snackbar.LENGTH_SHORT)
//                .setAction(context.getString(R.string.item_swipe_undo), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        addItem(position, mItems.get(position));
//                    }
//                }).show();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
