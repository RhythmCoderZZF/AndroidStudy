package com.example.android_study.ui_custom.demo.flipbord.flip_page;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author:create by RhythmCoder
 * Date:2021/9/7
 * Description:
 */
public class TurnPage extends View {
    /**
     * 页角的枚举
     */

    public enum Corner {
        LeftTop,

        LeftBottom,

        RightTop,

        RightBottom,

        None

    }

    PointF touch = new PointF();// 触摸点

    PointF corner = new PointF();// 页角点

    PointF middle = new PointF();// 触摸与页角的中点

    PointF crossX = new PointF();// 与X轴的焦点

    PointF crossY = new PointF();// 与Y轴的焦点

    Corner footer = Corner.None;// 表示页脚

    Path all = new Path();// 全部掀起的Path

    Path part = new Path();// 掀起背面的Path

    public TurnPage(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public TurnPage(Context context) {
        super(context);

    }

    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        if (footer == Corner.None) {
            return;

        }

        this.transPath();

        canvas.clipPath(all);

        canvas.drawColor(Color.LTGRAY);

        canvas.clipPath(part, Region.Op.INTERSECT);

        canvas.drawColor(Color.YELLOW);

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        touch.x = event.getX();

        touch.y = event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            this.calCorner();

        }

        if (action == MotionEvent.ACTION_MOVE) {
        }

        if (action == MotionEvent.ACTION_UP) {
        }

        this.calPoints();

        this.judgePoint();

        this.postInvalidate();

        return true;

    }

    /**
     * 计算页角
     */

    private void calCorner() {
        if (touch.x < this.getWidth() / 2 && touch.y < this.getHeight() / 2) {
            this.footer = Corner.LeftTop;

            corner.x = 0;

            corner.y = 0;

        } else if (touch.x < this.getWidth() / 2 && this.touch.y >= this.getHeight() / 2) {
            this.footer = Corner.LeftBottom;

            corner.x = 0;

            corner.y = this.getHeight();

        } else if (touch.x >= this.getWidth() / 2 && this.touch.y < this.getHeight() / 2) {
            this.footer = Corner.RightTop;

            corner.x = this.getWidth();

            corner.y = 0;

        } else if (touch.x >= this.getWidth() / 2 && this.touch.y >= this.getHeight() / 2) {
            this.footer = Corner.RightBottom;

            corner.x = this.getWidth();

            corner.y = this.getHeight();

        } else {
            this.footer = Corner.None;

        }

    }

    /**
     * 计算点
     */

    private void calPoints() {
        this.middle.x = (touch.x + corner.x) / 2;

        this.middle.y = (touch.y + corner.y) / 2;

//

        this.crossX.x = middle.x - (corner.y - middle.y) * (corner.y - middle.y) / (corner.x - middle.x);

        this.crossX.y = corner.y;

        this.crossY.x = corner.x;

        this.crossY.y = middle.y - (corner.x - middle.x) * (corner.x - middle.x) / (corner.y - middle.y);

    }

    /**
     * 转换路径的方法
     */

    private void transPath() {
        this.all.reset();

        this.all.moveTo(touch.x, touch.y);

        this.all.lineTo(crossX.x, crossX.y);

        this.all.lineTo(corner.x, corner.y);

        this.all.lineTo(crossY.x, crossY.y);

        this.all.close();

        this.part.reset();

        this.part.moveTo(touch.x, touch.y);

        this.part.lineTo(crossX.x, crossX.y);

        this.part.lineTo(crossY.x, crossY.y);

        this.part.close();

    }

    /**
     * 判断点是否正确
     */

    private void judgePoint() {
        if (touch.x < 0 && touch.x > this.getWidth()) {
            return;

        }

        if (footer == Corner.LeftTop || footer == Corner.LeftBottom) { // 如果在左边

            if (crossX.x >= 0 && crossX.x < this.getWidth() / 2) {
                return;

            }

            if (footer == Corner.LeftTop) {
                this.recalLeftTop();

            } else {
                this.recalLeftBottom();

            }

        }

        if (footer == Corner.RightTop || footer == Corner.RightBottom) {// 如果在右边

            if (crossX.x >= this.getWidth() / 2 && crossX.x < this.getWidth()) {
                return;

            }

            if (footer == Corner.RightTop) {
                this.recalRightTop();

            } else {
                this.recalRightBottom();

            }

        }

    }

// 重新计算左上

    private void recalLeftTop() {
        PointF mid = new PointF(this.getWidth() / 2, 0);

        float incline = (float) Math.hypot(touch.x - mid.x, touch.y - mid.y);

        float height = Math.abs(mid.y - touch.y);

        float bottom = Math.abs(mid.x - touch.x);

        float x = mid.x * bottom / incline;

        float y = mid.x * height / incline;

        this.touch.y = y;

        if (this.touch.x > mid.x) {
            touch.x = mid.x + x;

        } else {
            touch.x = mid.x - x;

        }

        this.calPoints();

    }

// 重新计算左下

    private void recalLeftBottom() {
        PointF mid = new PointF(this.getWidth() / 2, this.getHeight());

        float incline = (float) Math.hypot(touch.x - mid.x, touch.y - mid.y);

        float height = Math.abs(mid.y - touch.y);

        float bottom = Math.abs(mid.x - touch.x);

        float x = mid.x * bottom / incline;

        float y = mid.x * height / incline;

        this.touch.y = this.getHeight() - y;

        if (this.touch.x > mid.x) {
            touch.x = mid.x + x;

        } else {
            touch.x = mid.x - x;

        }

        this.calPoints();

    }

// 重新计算右上

    private void recalRightTop() {
        PointF mid = new PointF(this.getWidth() / 2, 0);

        float incline = (float) Math.hypot(touch.x - mid.x, touch.y - mid.y);

        float height = Math.abs(touch.y);

        float bottom = Math.abs(touch.x - mid.x);

        float x = mid.x * bottom / incline;

        float y = mid.x * height / incline;

        this.touch.y = y;

        if (touch.x > mid.x) {
            touch.x = mid.x + x;

        } else {
            touch.x = mid.x - x;

        }

        this.calPoints();

    }

// 重新计算右下

    private void recalRightBottom() {
        PointF mid = new PointF(this.getWidth() / 2, this.getHeight());

        float incline = (float) Math.hypot(touch.x - mid.x, touch.y - mid.y);

        float height = Math.abs(mid.y - touch.y);

        float bottom = Math.abs(touch.x - mid.x);

        float x = mid.x * bottom / incline;

        float y = mid.x * height / incline;

        this.touch.y = mid.y - y;

        if (touch.x > mid.x) {
            touch.x = mid.x + x;

        } else {
            touch.x = mid.x - x;

        }

        this.calPoints();

    }

}
