package com.example.android_study.ui_custom.demo.flipbord.flipViewGroup

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.example.android_study._base.util.LogUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/9/7
 * Description:
 */
class FlipViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var mViews: List<View>

    //翻动状态0为松手，1为向下翻，-1为向上翻
    private var statusFlip = 0
    private val standDisRatio = 0.75f

    private val LEFT_FLIP = -1
    private val RIGHT_FLIP = 1

    private var rotateL = 0f//左半边旋转角度
    private var rotateR = 0f//右半边旋转角度
    private var mAlreadyDealHalfRotate = false
    private val Z_TOP = 10f
    private val Z_BOTTOM = 5f
    private val Z_INVALIDATE = 1f

    private var startX: Float = 0f
    private var startY: Float = 0f

    private val centerX by lazy {
        width / 2.toFloat()
    }

    private val centerY by lazy {
        height / 2.toFloat()
    }

    private var mTotalDis = 0f

    private val mAnimRotate = ValueAnimator.ofFloat(0f, 1f)

    init {
        mAnimRotate.addUpdateListener {
            val ratio = it.animatedValue as Float
            rotateChild(ratio)
        }

        mAnimRotate.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                postDelayed({
                    finishToFinalRotate()
                    resetValue()
                }, 200)
            }

            override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
            }
        })
        mAnimRotate.apply {
            interpolator = LinearInterpolator()
            duration = 200
            repeatCount = 0
            repeatMode = ValueAnimator.RESTART
        }
    }

    //当前页
    private var curPage = 0
        get() {
            return if (field < 0) childCount - 1 else if (field > childCount - 1) 0 else field
        }

    init {
        setWillNotDraw(false)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                return childCount > 0 && !mAnimRotate.isRunning
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y
                //当y运动距离大于x的1.5倍时，才判断为垂直翻动
                val disX = x - startX
                val absX = Math.abs(disX)
                mTotalDis += absX
                if (absX > 1f && absX >= Math.abs(y - startY) * 1.5f && !mAnimRotate.isRunning) {
                    //左滑还是右滑
                    if (statusFlip == 0) {
                        statusFlip = if (disX > 0 && curPage != childCount - 1) RIGHT_FLIP
                        else if (disX < 0 && curPage != 0) LEFT_FLIP else 0
                    }
                    val ratio = Math.abs(disX) / (centerX * standDisRatio)
                    rotateChild(ratio)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (mTotalDis < 10f) {
                    if (curPage != 0 && event.x > centerX) {
                        statusFlip = LEFT_FLIP
                        if (!mAnimRotate.isRunning) {
                            mAnimRotate.start()
                        }
                    } else if (curPage != childCount - 1 && event.x <= centerX) {
                        statusFlip = RIGHT_FLIP
                        if (!mAnimRotate.isRunning) {
                            mAnimRotate.start()
                        }
                    } else {
                        statusFlip = 0
                    }
                } else  {
                    checkFinishToFinalRotate(event)
                    resetValue()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 根据旋转角度对Child进行旋转
     */
    private fun rotateChild(ratio: Float) {
        //右翻页（当前Child-left和上一页Child-right响应）
        if (statusFlip == RIGHT_FLIP) {
            rotateL = ratio * 180f
            if (rotateL > 180) {
                rotateL = 180f
            }
            (getChildAt(curPage) as FlipListener).onFlip(rotateL, 0f)
            (getChildAt(curPage + 1) as FlipListener).onFlip(0f, rotateL - 180)
            changeZIndex()

            //左翻页（当前Child-right和下一页Child-left响应）
        } else if (statusFlip == LEFT_FLIP) {
            rotateR = ratio * -180f
            if (rotateR < -180f) {
                rotateR = -180f
            }
            LogUtil.d("FLIP", "rotateR:$rotateR")
            (getChildAt(curPage) as FlipListener).onFlip(0f, rotateR)
            (getChildAt(curPage - 1) as FlipListener).onFlip(rotateR + 180, 0f)
            changeZIndex()
        }
    }

    /**
     * 结束滑动至最终角度
     */
    private fun checkFinishToFinalRotate(event: MotionEvent) {
        if (statusFlip != 0) {
            //滑动距离小于1/4屏幕高，判定仍停留在当前页
            if (Math.abs(event.x - startX) <= centerX * standDisRatio / 2) {
                (getChildAt(curPage) as FlipListener).onFlip(0f, 0f)
                if (statusFlip == RIGHT_FLIP) {
                    (getChildAt(curPage + 1) as FlipListener).onFlip(0f, -180f)
                }
                if (statusFlip == LEFT_FLIP) {
                    (getChildAt(curPage - 1) as FlipListener).onFlip(180f, 0f)
                }
            } else {
                finishToFinalRotate()
            }
        }
    }

    private fun finishToFinalRotate() {
        //滑动距离超过临界值，判定为跳过当前页
        if (statusFlip == RIGHT_FLIP) {
            //自动执行完下翻到上一页的动作
            (getChildAt(curPage) as FlipListener).onFlip(180f, 0f)
            (getChildAt(curPage + 1) as FlipListener).onFlip(0f, 0f)
            curPage++
        } else if (statusFlip == LEFT_FLIP) {
            //自动执行完上翻到下一页的动作
            (getChildAt(curPage) as FlipListener).onFlip(0f, -180f)
            (getChildAt(curPage - 1) as FlipListener).onFlip(0f, 0f)
            curPage--
        }
    }

    /**
     * 重置
     */
    private fun resetValue() {
        mTotalDis = 0f
        rotateL = 0f
        rotateR = 0f
        statusFlip = 0
        mAlreadyDealHalfRotate = false
        repeat(childCount) {
            val child = getChildAt(it) as FlipContentView
            child.onFlipEnd(child.z == Z_TOP)
        }
    }


    /**
     * 翻页90度修改z-index
     */
    private fun changeZIndex() {
        if (statusFlip == LEFT_FLIP) {
            if (rotateR < -90 && !mAlreadyDealHalfRotate) {
                mViews.forEach {
                    it.z = Z_INVALIDATE
                }
                getChildAt(curPage).z = Z_BOTTOM
                getChildAt(curPage - 1).z = Z_TOP
                mAlreadyDealHalfRotate = true
            } else if (rotateR >= -90 && mAlreadyDealHalfRotate) {
                mViews.forEach {
                    it.z = Z_INVALIDATE
                }
                getChildAt(curPage).z = Z_TOP
                getChildAt(curPage - 1).z = Z_BOTTOM
                mAlreadyDealHalfRotate = false
            }
        } else {
            if (rotateL > 90 && !mAlreadyDealHalfRotate) {
                mViews.forEach {
                    it.z = Z_INVALIDATE
                }
                getChildAt(curPage).z = Z_BOTTOM
                getChildAt(curPage + 1).z = Z_TOP
                mAlreadyDealHalfRotate = true
            } else if (rotateL <= 90 && mAlreadyDealHalfRotate) {
                mViews.forEach {
                    it.z = Z_INVALIDATE
                }
                getChildAt(curPage).z = Z_TOP
                getChildAt(curPage + 1).z = Z_BOTTOM
                mAlreadyDealHalfRotate = false
            }
        }
    }

    /**
     * 倒序设置Children
     */
    fun setViewList(views: List<FlipContentView>) {
        if (views.isEmpty()) {
            return
        }
        mViews = views
        removeAllViews()
        for (i in mViews.size - 1 downTo 0) {
            addView(views[i].apply {
                z = 100f - i
            })
        }
        curPage = childCount - 1
    }
}