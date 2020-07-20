package com.example.android_study._view._recyclerview.item_touch_helper;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_study.R;
import com.example.android_study._view._recyclerview.item_touch_helper.adapter.RvAdapter;
import com.example.android_study._view._recyclerview.nest_recycler.adapter.Datas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTouchHelRecyclerView extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private List<Datas> mDatas = new ArrayList<>(40);
    private RvAdapter adapter;

    {
        for (int i = 0; i < 150; i++) {
            Datas data = new Datas();
            data.title = i + "";
            mDatas.add(data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_hel_recycler_view);
        ButterKnife.bind(this);

        adapter = new RvAdapter(this, mDatas);


        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

    }
}
