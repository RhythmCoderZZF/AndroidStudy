package com.example.android_study.view._recyclerview.nest_recycler.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android_study._base.cmd.CmdUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Author: create by RhythmCoder
 * Date: 2020/5/22
 * Description:
 */
public class Adapter extends BaseQuickAdapter<Datas, Holder> {

    private LinearLayoutManager mLayoutManager;

    public Adapter(int layoutResId, @Nullable List<Datas> data) {
        super(layoutResId, data);
    }

    public Adapter(LinearLayoutManager linearLayoutManager, int layoutResId, @Nullable List<Datas> data) {
        super(layoutResId, data);
        this.mLayoutManager = linearLayoutManager;
    }

    @Override
    protected void convert(@NotNull Holder holder, Datas data) {
        CmdUtil.v( "convert - " + getCurrentViewIndex());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.tvTitle.setText(data.title);
    }

    public int getCurrentViewIndex() {
        int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        int firstVisibleItem1 = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        int lastVisibleItem1 = mLayoutManager.findLastCompletelyVisibleItemPosition();

//        Cmd.v("zzz", firstVisibleItem + " " + firstVisibleItem1 + " " + lastVisibleItem + " " + lastVisibleItem1);

        int currentIndex = firstVisibleItem;
        int lastHeight = 0;
        for (int i = firstVisibleItem; i <= lastVisibleItem; i++) {
            View view = mLayoutManager.findViewByPosition(i - firstVisibleItem);
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
