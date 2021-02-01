package com.example.android_study._base;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android_study.R;
import com.example.android_study._base.util.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;


/**
 * @author quchao
 * @date 2018/3/2
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationListData, NavigationViewHolder> {
    private TagClickListener listener;

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NavigationViewHolder helper, NavigationListData item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.item_navigation_tv, item.getTitle());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<NavigationListData.Entry> mArticles = item.getContent();
        mTagFlowLayout.setAdapter(new TagAdapter<NavigationListData.Entry>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, NavigationListData.Entry feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_flow_tv,
                        mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
//                tv.setPadding(CommonUtils.dp2px(10), CommonUtils.dp2px(10),
//                        CommonUtils.dp2px(10), CommonUtils.dp2px(10));
                tv.setText(feedArticleData.getContent());
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    if (listener != null) {
                        listener.onClick(mArticles.get(position1).getPath());
                    }
                    return true;
                });
                return tv;
            }
        });
    }

    public void setOnTagClickListener(TagClickListener listener) {
        this.listener = listener;
    }

    public interface TagClickListener {
        void onClick(Class path);
    }

}
