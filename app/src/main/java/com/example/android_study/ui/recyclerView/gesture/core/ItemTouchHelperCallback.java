package com.example.android_study.ui.recyclerView.gesture.core;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study._base.util.LogUtil;

/**
 * 这个接口可以让你监听“move”与 “swipe”事件。这里还是控制view被选中的状态以及重写默认动画的地方
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private onMoveAndSwipedListener moveAndSwipedListener;

    public ItemTouchHelperCallback(onMoveAndSwipedListener listener) {
        this.moveAndSwipedListener = listener;
    }

    /**
     * 初始化拖拽、侧滑的手势
     * 手势有三种Flag——IDLE（闲置）、SWIPE、DRAG，每种可以响应多个方向(UP,DOWN,LEFT,UP,RIGHT)
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            // for recyclerView with gridLayoutManager, support drag all directions, not support swipe
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);

        } else {

            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;    //允许向下滑动
            final int swipeFlags = ItemTouchHelper.START;                       //只允许向左滑动
            return makeMovementFlags(dragFlags, swipeFlags);
        }

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // If the 2 items are not the same type, no dragging
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        LogUtil.d("", "onMove:" + viewHolder.getBindingAdapterPosition() + " | " + target.getBindingAdapterPosition());
        return moveAndSwipedListener.onItemMove(viewHolder.getBindingAdapterPosition(), target.getBindingAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        moveAndSwipedListener.onItemDismiss(viewHolder.getBindingAdapterPosition());
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        LogUtil.d("", "onSelectedChanged:"  + actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        LogUtil.d("", "clearView:"  + viewHolder.getBindingAdapterPosition());
    }


}
