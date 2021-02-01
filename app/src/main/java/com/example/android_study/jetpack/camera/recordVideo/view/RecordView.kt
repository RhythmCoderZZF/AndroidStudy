package com.example.android_study.jetpack.camera.recordVideo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewConfiguration
import com.example.android_study.R
import com.example.android_study._base.util.dp2px

/**
 * author：chs
 * date：2020/5/18
 * des： 录制按钮
 */
class RecordView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr), OnLongClickListener, View.OnClickListener {
    private val mBgColor: Int
    private val mStrokeColor: Int
    private val mStrokeWidth: Int
    private val mDuration: Int
    private var mWidth = 0
    private var mHeight = 0
    private val mRadius: Int
    private var mProgressValue = 0
    private var isRecording = false
    private var mArcRectF: RectF? = null
    private val mBgPaint: Paint
    private val mProgressPaint: Paint
    private var mOnRecordListener: OnRecordListener? = null
    private var mStartRecordTime: Long = 0
    fun setOnRecordListener(onRecordListener: OnRecordListener?) {
        mOnRecordListener = onRecordListener
    }

    private fun setEvent() {
        val handler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mProgressValue++
                postInvalidate()
                if (mProgressValue < mDuration * 10) {
                    sendEmptyMessageDelayed(0, PROGRESS_INTERVAL.toLong())
                } else {
                    finishRecord()
                }
            }
        }
        setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                mStartRecordTime = System.currentTimeMillis()
                handler.sendEmptyMessage(0)
            } else if (event.action == MotionEvent.ACTION_UP) {
                val duration = System.currentTimeMillis() - mStartRecordTime
                //是否大于系统设定的最小长按时间
                if (duration > ViewConfiguration.getLongPressTimeout()) {
                    finishRecord()
                }
                handler.removeCallbacksAndMessages(null)
                isRecording = false
                mStartRecordTime = 0
                mProgressValue = 0
                postInvalidate()
            }
            false
        }
        setOnClickListener(this)
        setOnLongClickListener(this)
    }

    private fun finishRecord() {
        if (mOnRecordListener != null) {
            mOnRecordListener!!.onFinish()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = w
        mArcRectF = RectF(mStrokeWidth / 2f, mStrokeWidth / 2f,
                mWidth - mStrokeWidth / 2f, mHeight - mStrokeWidth / 2f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius.toFloat(), mBgPaint)
        if (isRecording) {
            canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius / 10f * 11, mBgPaint)
            val sweepAngle = 360f * mProgressValue / (mDuration * 10)
            Log.i("sweepAngle", sweepAngle.toString() + "")
            canvas.drawArc(mArcRectF!!, -90f, sweepAngle, false, mProgressPaint)
        }
    }

    override fun onLongClick(v: View): Boolean {
        isRecording = true
        if (mOnRecordListener != null) {
            mOnRecordListener!!.onRecordVideo()
        }
        return true
    }

    override fun onClick(v: View) {
        if (mOnRecordListener != null) {
            mOnRecordListener!!.onTackPicture()
        }
    }

    interface OnRecordListener {
        fun onTackPicture()
        fun onRecordVideo()
        fun onFinish()
    }

    companion object {
        private const val PROGRESS_INTERVAL = 100
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RecordView)
        mBgColor = typedArray.getColor(R.styleable.RecordView_bg_color, Color.WHITE)
        mStrokeColor = typedArray.getColor(R.styleable.RecordView_stroke_color, Color.RED)
        mStrokeWidth = typedArray.getDimensionPixelOffset(R.styleable.RecordView_stroke_width, 5f.dp2px())
        mDuration = typedArray.getInteger(R.styleable.RecordView_duration, 10)
        mRadius = typedArray.getDimensionPixelOffset(R.styleable.RecordView_radius, 40f.dp2px())
        typedArray.recycle()
        mBgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBgPaint.style = Paint.Style.FILL
        mBgPaint.color = mBgColor
        mProgressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mProgressPaint.style = Paint.Style.STROKE
        mProgressPaint.color = mStrokeColor
        mProgressPaint.strokeWidth = mStrokeWidth.toFloat()
        setEvent()
    }
}