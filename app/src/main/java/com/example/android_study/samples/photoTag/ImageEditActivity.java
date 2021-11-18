package com.example.android_study.samples.photoTag;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_study.R;
import com.example.android_study.samples.photoTag.view.ImageDragRectLayout;

/**
 * Created by KF on 2017/10/27.
 */

public class ImageEditActivity extends AppCompatActivity {
    private int mImgPath = R.drawable.bg_clear_day;
    private ImageDragRectLayout mImageIdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_drag_rect);
        mImageIdr = (ImageDragRectLayout) findViewById(R.id.idr_image);
        initData();
    }

    public void addIcon(View v) {
        mImageIdr.addMiddleIcon();
    }

    public void saveImage(View v) {
//        mImageIdr.savePhotoView(mImgPath);
        //如果是网络图片需要先把图片下载到本地
    }

    private void initData() {
        mImageIdr.setImage(mImgPath);
    }

}
