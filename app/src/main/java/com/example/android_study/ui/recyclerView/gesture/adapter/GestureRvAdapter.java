package com.example.android_study.ui.recyclerView.gesture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study._base.util.LogUtil;
import com.example.android_study.ui.recyclerView.gesture.core.onMoveAndSwipedListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: create by RhythmCoder
 * Date: 2020/5/22
 * Description:
 */
public class GestureRvAdapter extends RecyclerView.Adapter<GestureRvAdapter.Holder> implements onMoveAndSwipedListener {
    List<String> mItems;

    public GestureRvAdapter(List<String> mDatas) {
        this.mItems = mDatas;
    }

    public void setList(List<String> mDatas) {
        this.mItems = mDatas;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_gesture, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvTitle.setText(mItems.get(position));
        LogUtil.d("zzz", position + "");
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
        @BindView(R.id.tv)
        public TextView tvTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
