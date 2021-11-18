package com.example.android_study.samples.photoTag;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_study.R;
import com.example.android_study._base.cmd.CmdUtil;
import com.example.android_study._base.util.LogUtil;
import com.example.android_study.samples.photoTag.view.ImageDotLayout;

import java.util.ArrayList;
import java.util.List;

public class ImageDotActivity extends AppCompatActivity {

    private ImageDotLayout imageDotLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_dot);
        imageDotLayout = (ImageDotLayout) findViewById(R.id.idl_idl_photo);
        imageDotLayout.setOnImageClickListener(new ImageDotLayout.OnImageClickListener() {
            @Override
            public void onImageClick(ImageDotLayout.IconBean bean) {
                //可以一系列处理后再添加标签
                imageDotLayout.addIcon(bean);
            }
        });
        //设置背景图片
        imageDotLayout.setImage(R.mipmap.bg_big);
        initIcon();
        imageDotLayout.setOnIconClickListener(new ImageDotLayout.OnIconClickListener() {
            @Override
            public void onIconClick(View v) {
                ImageDotLayout.IconBean bean = (ImageDotLayout.IconBean) v.getTag();
                Toast.makeText(ImageDotActivity.this, "位置=" + bean.id, Toast.LENGTH_SHORT).show();
                imageDotLayout.setScale(2f);
            }
        });
    }

    private void initIcon() {
        final List<ImageDotLayout.IconBean> iconBeanList = new ArrayList<>();
        ImageDotLayout.IconBean bean = new ImageDotLayout.IconBean(0, 0.2f, 0.5f, null);
        iconBeanList.add(bean);
        bean = new ImageDotLayout.IconBean(1, 0.5f, 0.3f, null);
        iconBeanList.add(bean);
        //监听图片是否加载完成
        imageDotLayout.setOnLayoutReadyListener(new ImageDotLayout.OnLayoutReadyListener() {
            @Override
            public void onLayoutReady() {
                CmdUtil.v("onLayoutReady");
                imageDotLayout.addIcons(iconBeanList);
            }
        });
    }
}
