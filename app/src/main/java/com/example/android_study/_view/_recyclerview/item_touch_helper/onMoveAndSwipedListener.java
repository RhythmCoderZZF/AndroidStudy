package com.example.android_study._view._recyclerview.item_touch_helper;

/**
 * Created by zhang on 2016.08.21.
 */
public interface onMoveAndSwipedListener {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
