package com.example.android_study.samples.largePhoto.region_inflate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_study.R;
import com.example.android_study._base.cmd.CmdUtil;

public class LargePhotoRegionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CmdUtil.showWindow();
        setContentView(R.layout.activity_sample_large_photo_region);
    }
}
