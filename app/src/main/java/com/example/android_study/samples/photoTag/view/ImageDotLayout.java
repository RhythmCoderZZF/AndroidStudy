package com.example.android_study.samples.photoTag.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.android_study.R;
import com.example.android_study._base.cmd.CmdUtil;
import com.example.android_study.samples.photoTag.util.DensityUtil;
import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cc on 2017/10/25.
 */

public class ImageDotLayout extends FrameLayout implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = ImageDotLayout.class.getSimpleName();
    private List<ImageView> iconList;
    private PhotoView photoView;//背景图
    private RectF tempRectF;
    private OnIconClickListener onIconClickListener;
    private OnIconLongClickListener onIconLongClickListener;
    private OnLayoutReadyListener onLayoutReadyListener;
    private OnImageClickListener onImageClickListener;
    private Matrix photoViewMatrix;
    boolean firstLoadPhotoView = true;

    public ImageDotLayout(@NonNull Context context) {
        this(context, null);
    }

    public ImageDotLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageDotLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private Drawable mIconDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_location);

    void initView(final Context context) {
        photoView = new PhotoView(context);
        LayoutParams layoutParams =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(photoView, layoutParams);
        photoView.setOnMatrixChangeListener(new OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rectF) {
                if (rectF.isEmpty()) {
                    return;
                }
                CmdUtil.v(rectF.toString());
                if (iconList != null && iconList.size() > 0) {
                    for (ImageView icon : iconList) {
                        IconBean bean = (IconBean) icon.getTag();
                        float newX = bean.sx * (rectF.right - rectF.left);
                        float newY = bean.sy * (rectF.bottom - rectF.top);
                        //保持底部中心位置不变
                        icon.setX(rectF.left + newX - DensityUtil.dp2px(getContext(), 45) / 2f);
                        icon.setY(rectF.top + newY - DensityUtil.dp2px(getContext(), 48) * 1f);
                    }
                }
                tempRectF = rectF;
                //图片加载完成后才可以添加图标
                if (onLayoutReadyListener != null) {
                    onLayoutReadyListener.onLayoutReady();
                    //保证只执行一次
                    onLayoutReadyListener = null;
                }

            }
        });
        //实现OnPhotoTapListener接口，监听图片被点击的位置
        photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float v, float v1) {
                Log.i(TAG, "onPhotoTap");
                int id = 0;
                if (iconList != null && iconList.size() > 0) {
                    id = iconList.size();
                }
                IconBean bean = new IconBean(id, v, v1, mIconDrawable);
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick(bean);
                }
            }
        });

    }

    public void setScale(Float scale) {
        photoView.setScale(scale);
    }

    public void setIconDrawable(Drawable drawable) {
        mIconDrawable = drawable;
    }

    public void addIcon(IconBean bean) {
        //记住此时photoView的Matrix
        if (photoViewMatrix == null) {
            photoViewMatrix = new Matrix();
        }
        photoView.getSuppMatrix(photoViewMatrix);
        if (iconList == null) {
            iconList = new ArrayList<>();
        }
        final ImageView icon = new ImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(DensityUtil.dp2px(getContext(), 45), DensityUtil.dp2px(getContext(), 48));
        icon.setImageDrawable(bean.drawable == null ? mIconDrawable : bean.drawable);
        icon.setTag(bean);
        icon.setOnClickListener(this);
        icon.setOnLongClickListener(this);
        addView(icon, layoutParams);
        iconList.add(icon);
    }

    public void updateIconResource(ImageView icon, Drawable drawable) {
        icon.setImageDrawable(drawable);
    }

    public void addIcon(int id, float sx, float sy, Drawable drawable) {
        IconBean iconBean = new IconBean(id, sx, sy, drawable);
        addIcon(iconBean);
    }


    @Override
    public void onClick(View v) {
        if (onIconClickListener != null) {
            onIconClickListener.onIconClick(v);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (photoViewMatrix != null) {
            photoView.setDisplayMatrix(photoViewMatrix);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onIconLongClickListener != null) {
            onIconLongClickListener.onIconLongClick(v);
        }
        return true;
    }


    public static class IconBean {
        public int id;//标签的顺序，从0开始
        public float sx;//左边距比例
        public float sy;//上边距比例
        public Drawable drawable;//图标

        public IconBean(int id, float sx, float sy, Drawable drawable) {
            this.id = id;
            this.sx = sx;
            this.sy = sy;
            this.drawable = drawable;
        }
    }

    public interface OnIconClickListener {
        void onIconClick(View v);
    }

    public interface OnIconLongClickListener {
        void onIconLongClick(View v);
    }

    public interface OnImageClickListener {
        void onImageClick(IconBean bean);
    }

    public interface OnLayoutReadyListener {
        void onLayoutReady();
    }

    public void setOnIconClickListener(OnIconClickListener onIconClickListener) {
        this.onIconClickListener = onIconClickListener;
    }

    public void setOnIconLongClickListener(OnIconLongClickListener onIconLongClickListener) {
        this.onIconLongClickListener = onIconLongClickListener;
    }

    public void setOnLayoutReadyListener(OnLayoutReadyListener onLayoutReadyListener) {
        this.onLayoutReadyListener = onLayoutReadyListener;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    /**
     * 设置图片
     */
    public void setImage(@DrawableRes int id) {
        firstLoadPhotoView = true;
        post(() -> {
            photoView.setImageDrawable(getContext().getDrawable(id));
        });
//        Glide.with(getContext()).load(id).into(photoView);
    }

    /**
     * 移除icon
     */
    public void removeIcon(ImageView icon) {
        removeView(icon);
    }

    /**
     * 移除所有icon
     */
    public void removeAllIcon() {
        if (iconList != null && iconList.size() > 0) {
            for (ImageView icon : iconList) {
                removeView(icon);
            }
            iconList.clear();
        }
    }

    /**
     * 获取所有icon信息
     *
     * @return
     */
    public List<IconBean> getAllIconInfos() {
        List<IconBean> rectBeans = new ArrayList<>();
        if (iconList != null && iconList.size() > 0) {
            for (ImageView icon : iconList) {
                IconBean rectBean = (IconBean) icon.getTag();
                rectBeans.add(rectBean);
            }
        }
        return rectBeans;
    }

    public void addIcons(List<IconBean> iconBeanList) {
        if (iconBeanList != null && iconBeanList.size() > 0) {
            for (IconBean bean : iconBeanList) {
                addIcon(bean);
            }
        }
    }
}
