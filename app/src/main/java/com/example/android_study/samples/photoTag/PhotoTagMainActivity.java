package com.example.android_study.samples.photoTag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_study.R;
import com.example.android_study._base.cmd.CmdUtil;

public class PhotoTagMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CmdUtil.showWindow();
        setContentView(R.layout.activity_sample_photo_tag_main);
    }

    public void toImageDot(View v) {
        startActivity(new Intent(this, ImageDotActivity.class));
    }
    public void toImageEdit(View v) {
        startActivity(new Intent(this, ImageEditActivity.class));
    }

}
