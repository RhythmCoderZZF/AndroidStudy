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

import com.example.android_study.Java.Juc.JavaJucActivity;
import com.example.android_study.Java.MemoryModel.JavaMMActivity;
import com.example.android_study.Java.io.JavaIOActivity;
import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.NavigationAdapter;
import com.example.android_study._base.NavigationListData;
import com.example.android_study._base.util.ToolbarHelper;
import com.example.android_study.android.activity.AndroidActivity;
import com.example.android_study.android.bluetooth.AndroidBluetoothMainActivity;
import com.example.android_study.android.database.MainDataBaseAy;
import com.example.android_study.android.notification.NotificationMainActivity;
import com.example.android_study.android.webview.AndroidWebViewMainActivity;
import com.example.android_study.android.data_and_file.AndroidDataAndFileMainActivity;
import com.example.android_study.android.drawable_and_graph.AndroidDrawableGraphMainActivity;
import com.example.android_study.android.ipc.IPCMainActivity;
import com.example.android_study.android.location.AndroidLocationManagerMainActivity;
import com.example.android_study.android.wifi.WifiMainActivity;
import com.example.android_study.design.DesignMainActivity;
import com.example.android_study.event_system.multi_touch.EventMultiTouchMainActivity;
import com.example.android_study.framework.glide.FWGlideAy;
import com.example.android_study.jetpack.camera.CameraXMainActivity;
import com.example.android_study.android.handler.HandlerMainActivity;
import com.example.android_study.android.media.MediaPlayerMainActivity;
import com.example.android_study.android.service.ServiceMainActivity;
import com.example.android_study.framework._network.NetWorkAy;
import com.example.android_study.jetpack.databinding.DataBindingAy;
import com.example.android_study.jetpack.demo_aac.ui.activity.AACDemoActivity;
import com.example.android_study.jetpack.demo_wanandroid.WanandroidMainActivity;
import com.example.android_study.jetpack.demos.DemosAy;
import com.example.android_study.jetpack.lifecycle.LifecycleAy;
import com.example.android_study.jetpack.livedata.LiveDataAy;
import com.example.android_study.jetpack.navigation.NavigationTestActivity;
import com.example.android_study.jetpack.paging3.PagingAy;
import com.example.android_study.jetpack.room.RoomAy;
import com.example.android_study.jetpack.viewmodel.JetpackViewModelMainActivity;
import com.example.android_study.kotlin.base.KotlinBaseMainActivity;
import com.example.android_study.kotlin.channel.KotlinChannelMainActivity;
import com.example.android_study.kotlin.collection.CollectionMainActivity;
import com.example.android_study.kotlin.coroutine.CoroutinesAy;
import com.example.android_study.kotlin.flow.KotlinFlowMainActivity;
import com.example.android_study.kotlin.select.KotlinSelectMainActivity;
import com.example.android_study.network.okhttp.OkHttpMainActivity;
import com.example.android_study.other.adb.ADBActivity;
import com.example.android_study.other.status_bar.common.StatusBarAy;
import com.example.android_study.other.utils.UtilsActivity;
import com.example.android_study.performance.anr.PerformanceANRActivity;
import com.example.android_study.performance.crash.PerformanceCrashActivity;
import com.example.android_study.performance.layout.PerformanceLayoutActivity;
import com.example.android_study.performance.memory.PerformanceMemoryLeakAy;
import com.example.android_study.performance.power.PerformancePowerMainActivity;
import com.example.android_study.samples.gallery.GalleryMainActivity;
import com.example.android_study.samples.largePhoto.LargePhotoMainActivity;
import com.example.android_study.samples.photoTag.PhotoTagMainActivity;
import com.example.android_study.ui.popup_window.PopupWindowAy;
import com.example.android_study.ui.materialDesign.MaterialDesignMainActivity;
import com.example.android_study.ui.recyclerView.UIRecyclerViewMainActivity;
import com.example.android_study.ui.viewSystem.UIViewSystemActivity;
import com.example.android_study.ui.viewpager2.UIViewPager2MainActivity;
import com.example.android_study.ui_custom.anim.UICusAnimActivity;
import com.example.android_study.ui_custom.calendar.CalendarAy;
import com.example.android_study.event_system.event.UICusEventActivity;
import com.example.android_study.event_system.event_util.UICusEventUtilActivity;
import com.example.android_study.event_system.event_nest.UICusEventNestActivity;
import com.example.android_study.ui_custom.demo.UICusDemoMainActivity;
import com.example.android_study.ui_custom.samples.UICusSamplesActivity;
import com.example.android_study.ui_custom.study.UICusStudyActivity;

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

    // Android
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Android");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Activity与Fragment");
            entry.setPath(AndroidActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Service");
            entry.setPath(ServiceMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("IPC");
            entry.setPath(IPCMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("应用数据与文件");
            entry.setPath(AndroidDataAndFileMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("持久化技术");
            entry.setPath(MainDataBaseAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("图片与图形");
            entry.setPath(AndroidDrawableGraphMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Handler");
            entry.setPath(HandlerMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("WebView");
            entry.setPath(AndroidWebViewMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("多媒体");
            entry.setPath(MediaPlayerMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("WI-FI");
            entry.setPath(WifiMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Location");
            entry.setPath(AndroidLocationManagerMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("通知");
            entry.setPath(NotificationMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("蓝牙");
            entry.setPath(AndroidBluetoothMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // #Kotlin
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Kotlin");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("基础");
            entry.setPath(KotlinBaseMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("集合");
            entry.setPath(CollectionMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("协程");
            entry.setPath(CoroutinesAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Channnel");
            entry.setPath(KotlinChannelMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Select");
            entry.setPath(KotlinSelectMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Flow");
            entry.setPath(KotlinFlowMainActivity.class);
            entries.add(entry);
        }

        data.setContent(entries);
        navigationDataList.add(data);
    }

    // JetPack组件
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("JetPack组件");
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
            entry.setPath(JetpackViewModelMainActivity.class);
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
            entry.setContent("DataBinding");
            entry.setPath(DataBindingAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Room");
            entry.setPath(RoomAy.class);
            entries.add(entry);
        }

        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Navigation");
            entry.setPath(NavigationTestActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Paging3");
            entry.setPath(PagingAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("CameraX");
            entry.setPath(CameraXMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Demos");
            entry.setPath(DemosAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("aac demo");
            entry.setPath(AACDemoActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("wanandroid demo");
            entry.setPath(WanandroidMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 图形系统
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Android图形系统");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("🚀Android图形系统");
            entry.setPath(UIViewSystemActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 事件系统
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Android事件系统");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("🚀Android事件系统");
            entry.setPath(UICusEventActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("事件处理工具类");
            entry.setPath(UICusEventUtilActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("嵌套滑动");
            entry.setPath(UICusEventNestActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("多点触控");
            entry.setPath(EventMultiTouchMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 自定义View
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("自定义控件");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("学习系列🚀🚀");
            entry.setPath(UICusStudyActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("过渡与动画学习");
            entry.setPath(UICusAnimActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("开发demo");
            entry.setPath(UICusDemoMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("基础案例");
            entry.setPath(UICusSamplesActivity.class);
            entries.add(entry);
        }

        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("日历");
            entry.setPath(CalendarAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 控件
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("基础控件");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("MaterialDesign");
            entry.setPath(MaterialDesignMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PopupWindow");
            entry.setPath(PopupWindowAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("ViewPager2&TabLayout");
            entry.setPath(UIViewPager2MainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("RecyclerView");
            entry.setPath(UIRecyclerViewMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // Java
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Java");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("并发编程");
            entry.setPath(JavaJucActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("IO");
            entry.setPath(JavaIOActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("JMM");
            entry.setPath(JavaMMActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 网络编程
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("网络编程");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Okhttp");
            entry.setPath(OkHttpMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 框架
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("框架");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("网络框架");
            entry.setPath(NetWorkAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Glide");
            entry.setPath(FWGlideAy.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // performance 性能优化
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("优化");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("内存泄漏");
            entry.setPath(PerformanceMemoryLeakAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("异常崩溃");
            entry.setPath(PerformanceCrashActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("ANR");
            entry.setPath(PerformanceANRActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("布局优化");
            entry.setPath(PerformanceLayoutActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("功耗优化");
            entry.setPath(PerformancePowerMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 设计模式
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("设计模式");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("设计模式大全");
            entry.setPath(DesignMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 示例demo
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("示例demo");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("简易画廊");
            entry.setPath(GalleryMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("图片标注");
            entry.setPath(PhotoTagMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("加载超大图");
            entry.setPath(LargePhotoMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // other-其他
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("其他");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("ADB");
            entry.setPath(ADBActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("状态栏适配");
            entry.setPath(StatusBarAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("工具类");
            entry.setPath(UtilsActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("git");
            entry.setPath(UtilsActivity.class);
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
