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

import com.example.android_study._base.BaseActivity;
import com.example.android_study._base.NavigationAdapter;
import com.example.android_study._base.NavigationListData;
import com.example.android_study._base.util.ToolbarHelper;
import com.example.android_study.android._activity.LifecycleActivity;
import com.example.android_study.android._database.MainDataBaseAy;
import com.example.android_study.android._webview.WebViewAy;
import com.example.android_study.android.data_and_file.AndroidDataAndFileMainActivity;
import com.example.android_study.android.drawable_and_graph.AndroidDrawableGraphMainActivity;
import com.example.android_study.android.location.AndroidLocationManagerMainActivity;
import com.example.android_study.android.wifi.WifiMainActivity;
import com.example.android_study.java.io.JavaIOActivity;
import com.example.android_study.jetpack.camera.CameraXMainActivity;
import com.example.android_study.android.fragment.FragmentMainActivity;
import com.example.android_study.android.handler.HandlerMainActivity;
import com.example.android_study.android.mediaPlayer.MediaPlayerMainActivity;
import com.example.android_study.android.service.ServiceMainActivity;
import com.example.android_study.dispatchEvent.DispatchEventMainActivity;
import com.example.android_study.framework._network.NetWorkAy;
import com.example.android_study.framework._permission.PermissionAy;
import com.example.android_study.java.juc.JavaJucActivity;
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
import com.example.android_study.kotlin.collection.CollectionMainActivity;
import com.example.android_study.kotlin.coroutine.CoroutinesAy;
import com.example.android_study.kotlin.coroutine_core.CoroutineCoreActivity;
import com.example.android_study.kotlin.flow.KotlinFlowMainActivity;
import com.example.android_study.other.bugly.OtherBuglyActivity;
import com.example.android_study.other.status_bar.common.StatusBarAy;
import com.example.android_study.other.utils.UtilsActivity;
import com.example.android_study.other.zxing.ZXingDemoAy;
import com.example.android_study.performance_optimization.LeakCanaryAy;
import com.example.android_study.performance_optimization.POMemoryMainActivity;
import com.example.android_study.samples.gallery.GalleryMainActivity;
import com.example.android_study.third.map.AmapActivity;
import com.example.android_study.third.picture_selector.ThirdPictureSelectorMainActivity;
import com.example.android_study.third.rong_im.ImActivity;
import com.example.android_study.ui._popupwindow.PopupWindowAy;
import com.example.android_study.ui.materialDesign.MaterialDesignMainActivity;
import com.example.android_study.ui.recyclerView.UIRecyclerViewMainActivity;
import com.example.android_study.ui.viewSystem.UIViewSystemActivity;
import com.example.android_study.ui.viewpager2.UIViewPager2MainActivity;
import com.example.android_study.ui_custom.calendar.CalendarAy;
import com.example.android_study.ui_custom.event.UICusEventActivity;
import com.example.android_study.ui_custom.samples.UICusSamplesActivity;
import com.example.android_study.ui_custom.study.UICusStudyActivity;
import com.example.android_study.ui_third.AAChartCore.AAChartCoreMainActivity;
import com.example.android_study.ui_third.BasePopup.UITBasePopupMainActivity;
import com.example.android_study.ui_third.BaseRecyclerViewHolder.UITBaseRecyclerViewHolderMainActivity;
import com.example.android_study.ui_third.WheelView.UITPickerViewMainActivity;
import com.example.android_study.view._recyclerview.nest_recycler.RecyclerViewActivity;

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
            entry.setPath(FragmentMainActivity.class);
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
            entry.setContent("应用数据与文件");
            entry.setPath(AndroidDataAndFileMainActivity.class);
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
            entry.setPath(WebViewAy.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("播放器");
            entry.setPath(MediaPlayerMainActivity.class);
            entries.add(entry);
        } {
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
        data.setContent(entries);
        navigationDataList.add(data);
    }

    //Java
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("Java");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("Juc");
            entry.setPath(JavaJucActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("IO");
            entry.setPath(JavaIOActivity.class);
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
            entry.setContent("协程核心");
            entry.setPath(CoroutineCoreActivity.class);
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
        data.setTitle("UI");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("View System");
            entry.setPath(UIViewSystemActivity.class);
            entries.add(entry);
        }
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



    // 自定义View
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("自定义View");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("学习系列🚀🚀");
            entry.setPath(UICusStudyActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("事件分发🚀🚀");
            entry.setPath(UICusEventActivity.class);
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


    // 第三方UI
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("第三方UI");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("BasePopup");
            entry.setPath(UITBasePopupMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PickerView");
            entry.setPath(UITPickerViewMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("万能RecyclerViewAdapter");
            entry.setPath(UITBaseRecyclerViewHolderMainActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("超级图标AAChart");
            entry.setPath(AAChartCoreMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }

    // 事件分发
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("事件分发");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("事件拦截");
            entry.setPath(DispatchEventMainActivity.class);
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
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("内存");
            entry.setPath(POMemoryMainActivity.class);
            entries.add(entry);
        }
        data.setContent(entries);
        navigationDataList.add(data);
    }


    // 第三方
    {
        NavigationListData data = new NavigationListData();
        data.setTitle("第三方");
        List<NavigationListData.Entry> entries = new ArrayList<>();
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("高德地图");
            entry.setPath(AmapActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("融云");
            entry.setPath(ImActivity.class);
            entries.add(entry);
        }
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("PictureSelector");
            entry.setPath(ThirdPictureSelectorMainActivity.class);
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
        {
            NavigationListData.Entry entry = new NavigationListData.Entry();
            entry.setContent("bugly");
            entry.setPath(OtherBuglyActivity.class);
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
