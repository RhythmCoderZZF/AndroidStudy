package com.example.android_study.view._recyclerview.nest_recycler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study.view._recyclerview.nest_recycler.adapter.Adapter;
import com.example.android_study.view._recyclerview.nest_recycler.adapter.Datas;
import com.example.android_study.view._recyclerview.nest_recycler.adapter.RvAdapter;
import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * NestScrollView嵌套RecyclerView 的item无法复用问题
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_1)
    RecyclerView mRecyclerView1;

    private RvAdapter mRvAdapter;
    private Adapter mAdapter;


    private List<Datas> mDatas = new ArrayList<>(40);

    {
        for (int i = 0; i < 150; i++) {
            Datas data = new Datas();
            data.title = i + "";
            mDatas.add(data);
        }
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvAdapter = new RvAdapter(manager, this, mDatas);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setAdapter(mRvAdapter);

        //BaseAdapter
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        mAdapter = new Adapter(manager1, R.layout.item_recycler_view_gesture, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    LogUtil.v("zzz", lastItemPosition + "   " + firstItemPosition);
                }
            }
        });
        mRecyclerView1.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    LogUtil.v("zzz", lastItemPosition + "   " + firstItemPosition);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

}
