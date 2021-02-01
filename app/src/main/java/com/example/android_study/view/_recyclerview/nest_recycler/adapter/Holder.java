package com.example.android_study.view._recyclerview.nest_recycler.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.android_study.R;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: create by RhythmCoder
 * Date: 2020/5/22
 * Description:
 */
public class Holder extends BaseViewHolder {
    @BindView(R.id.tv)
    TextView tvTitle;

    public Holder(@NotNull View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
