package com.example.android_study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study._android._activity.LifecycleActivity;
import com.example.android_study._android._database.MainDataBaseAy;
import com.example.android_study._android._fragment.FragmentDemoAy;
import com.example.android_study._android._webview.WebViewAy;
import com.example.android_study._framework._network.NetWorkAy;
import com.example.android_study._framework._permission.PermissionAy;
import com.example.android_study._jetpack._aac_demo.ui.activity.AACDemoActivity;
import com.example.android_study._jetpack._lifecycle.LifecycleAy;
import com.example.android_study._jetpack._livedata.LiveDataAy;
import com.example.android_study._jetpack._room.RoomAy;
import com.example.android_study._jetpack._viewmodel.ViewModelAy;
import com.example.android_study._kotlin._coroutines.CoroutinesAy;
import com.example.android_study._other._status_bar.StatusBarAy;
import com.example.android_study._other._zxing.ZXingDemoAy;
import com.example.android_study._performance_optimization.LeakCanaryAy;
import com.example.android_study._ui._materialdesign.MaterialAy;
import com.example.android_study._ui._popupwindow.PopupWindowAy;
import com.example.android_study._ui_custom.calendar.CalendarAy;
import com.example.android_study._view._recyclerview.nest_recycler.RecyclerViewActivity;
import com.example.android_study.base.BaseActivity;
import com.example.android_study.base.NavigationAdapter;
import com.example.android_study.base.NavigationListData;
import com.example.android_study.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class MainActivity extends BaseActivity implements NavigationAdapter.TagClickListener {
    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.navigation_divider)
    View navigationDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private List<NavigationListData> navigationDataList = new ArrayList<>();
    private NavigationAdapter mNavigationAdapter;
    private LinearLayoutManager mManager;

    private boolean needScroll;
    private int index;
    private boolean isClickTab;

    //Android
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Android");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Activity");
            entry.setPath(LifecycleActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Fragment");
            entry.setPath(FragmentDemoAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("持久化");
            entry.setPath(MainDataBaseAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("WebView");
            entry.setPath(WebViewAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    //RecyclerView
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("RecyclerView");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Nest");
            entry.setPath(RecyclerViewActivity.class);
            entries.add(entry);
        }
        {

            NavigationListData.Entry entry1 = new NavigationListData.Entry();
            entry1.setContent("手势");
            entry1.setPath(RecyclerViewActivity.class);
            entries.add(entry1);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }


    //Framework
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Framework");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PermissionsDispatcher");
            entry.setPath(PermissionAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("网络框架");
            entry.setPath(NetWorkAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    //原生UI
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("原生UI");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PopupWindow");
            entry.setPath(PopupWindowAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 第三方UI
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("第三方UI");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PopupWindow");
            entry.setPath(PopupWindowAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 自定义UI
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("自定义UI");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("日历");
            entry.setPath(CalendarAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // Material Design
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Material Design");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Material Design");
            entry.setPath(MaterialAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 优化
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("优化");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("内存泄漏");
            entry.setPath(LeakCanaryAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // Kotlin
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Kotlin");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("协程");
            entry.setPath(CoroutinesAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // JetPack
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("JetPack");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Lifecycle");
            entry.setPath(LifecycleAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("ViewModel");
            entry.setPath(ViewModelAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("LiveData");
            entry.setPath(LiveDataAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Room");
            entry.setPath(RoomAy.class);
            entries.add(entry);
        }  {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("aac demo");
            entry.setPath(AACDemoActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 其他
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("其他");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("状态栏适配");
            entry.setPath(StatusBarAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("zxing");
            entry.setPath(ZXingDemoAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        ToolbarHelper.setBar(mContext, "首页");
        initRecyclerView();
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null ? 0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int i) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(i).getTitle())
                        .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                                ContextCompat.getColor(MainActivity.this, R.color.shallow_grey))
                        .setTextSize(14)
                        .build();
            }

            @Override
            public int getBackground(int i) {
                return -1;
            }
        });
        mNavigationAdapter.replaceData(navigationDataList);
        leftRightLinkage();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    private void initRecyclerView() {
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, navigationDataList);
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNavigationAdapter);
        mNavigationAdapter.setOnTagClickListener(this);
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (mTabLayout == null) {
                return;
            }
            mTabLayout.setTabSelected(index);
        }
        index = position;
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }

    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    @Override
    public void onClick(Class path) {
        startActivity(new Intent(this, path));
    }


}
