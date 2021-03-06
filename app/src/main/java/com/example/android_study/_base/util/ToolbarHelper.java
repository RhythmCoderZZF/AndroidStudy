package com.example.android_study._base.util;

import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android_study.R;


/**
 * @auther：hysj created at 2019/01/25
 * description：Toolbar 封装类
 */
public class ToolbarHelper {

    public static void setBar(AppCompatActivity activity, String title, String toolbarRightTitle) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        TextView mTvtitle = (TextView) activity.findViewById(R.id.tv_title);
        TextView toolbarRight = (TextView) activity.findViewById(R.id.tv_toolbar_right);
        toolbar.setTitle("");
        mTvtitle.setText(title);
        toolbarRight.setText(toolbarRightTitle);
        activity.setSupportActionBar(toolbar);
    }

    public static void setBar(AppCompatActivity activity, String title) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        TextView mTvtitle = activity.findViewById(R.id.tv_title);
        toolbar.setTitle("");
        mTvtitle.setText(title);
        activity.setSupportActionBar(toolbar);
    }

    public static void setBar(final AppCompatActivity activity, String title, int leftIcon, String toolbarRightTitle) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        TextView tv = (TextView) activity.findViewById(R.id.tv_title);
        TextView toolbarRight = (TextView) activity.findViewById(R.id.tv_toolbar_right);
        toolbar.setTitle("");
        tv.setText(title);
        toolbarRight.setText(toolbarRightTitle);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public static void setBar(final AppCompatActivity activity, String s, int rightIcon) {
        Toolbar toolbar =  activity.findViewById(R.id.toolbar);
        TextView tv =  activity.findViewById(R.id.tv_title);
        ImageView ivRight =  activity.findViewById(R.id.iv_toolbar_right);
        ivRight.setImageResource(rightIcon);
        toolbar.setTitle("");
        tv.setText(s);


        activity.setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(leftIcon);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.onBackPressed();
//            }
//        });
    }

    public static void setBar(final DialogFragment dialogFragment, View view, String s, int leftIcon) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        toolbar.setTitle("");
        tv.setText(s);
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.dismiss();
            }
        });
    }

    public static void setBar(final AppCompatActivity activity, int resId, int leftIcon, int type) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        TextView tv = (TextView) activity.findViewById(R.id.tv_title);
        toolbar.setTitle("");
        tv.setText(resId);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public static void setBar(final AppCompatActivity activity, String s, int leftIcon, int rightIcon) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        TextView tv = activity.findViewById(R.id.tv_title);
        ImageView ivRight = activity.findViewById(R.id.iv_toolbar_right);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(rightIcon);

        toolbar.setTitle("");
        tv.setText(s);

        activity.setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(leftIcon);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.onBackPressed();
//            }
//        });
    }

    public static void setHeaderBar(final AppCompatActivity activity, String title, int leftIcon, int rightIcon) {
        ImageButton mIbtnBack = activity.findViewById(R.id.iv_back);
        TextView mTvTitle = activity.findViewById(R.id.tv_title);
        ImageView mImgbarRight =  activity.findViewById(R.id.iv_toolbar_right);

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        mIbtnBack.setImageResource(leftIcon);
        mImgbarRight.setImageResource(rightIcon);
        mIbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public static void setLoginBar(final AppCompatActivity activity, String title, int leftIcon, String toolbarRightTitle) {
        ImageButton mIbtnBack =  activity.findViewById(R.id.iv_back);
        TextView mTvTitle = (TextView) activity.findViewById(R.id.tv_title);
        TextView toolbarRight = (TextView) activity.findViewById(R.id.tv_toolbar_right);

        mIbtnBack.setImageResource(leftIcon);
        toolbarRight.setText(toolbarRightTitle);
        mTvTitle.setText(title);
        mIbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public static void setBar(final AppCompatActivity activity, String s, int leftIcon, int type, final String bundle) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        TextView tv = (TextView) activity.findViewById(R.id.tv_title);

        toolbar.setTitle("");
        tv.setText(s);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

    }

    public static void setBar(final AppCompatActivity activity, int resId, int leftIcon, int type, final String bundle) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        TextView tv = (TextView) activity.findViewById(R.id.tv_title);
        toolbar.setTitle("");
        tv.setText(resId);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

    }

//    public static void setHeadBar(final AppCompatActivity activity, String title, int leftIcon, String oprate) {
//        ImageButton mIbtnBack = (ImageButton) activity.findViewById(R.id.iv_back);
//        TextView mTvTitle = (TextView) activity.findViewById(R.id.tv_title);
//        TextView mTvSave = (TextView) activity.findViewById(R.id.tv_save);
//        mTvTitle.setText(title);
//        {
//            mTvSave.setVisibility(View.VISIBLE);
//            mTvSave.setText(oprate);
//        }
//        mIbtnBack.setImageResource(leftIcon);
//        mIbtnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.onBackPressed();
//            }
//        });
//
//    }

    /**
     * Unfortunately Android doesn't have an official API to retrieve the height of
     * StatusBar. This is just a way to hack around, may not work on some devices.
     *
     * @return The height of StatusBar.
     */
    public static int getStatusBarHeight(Context mContext) {

        Resources resources = mContext.getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        Log.v("CollectedShop", "getStatusBarHeight: " + result);
        return result;
    }
}
