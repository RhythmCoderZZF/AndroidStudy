package com.example.android_study.ui_custom.demo.standard.falling_ball

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_s_d_falling_ball.*

class UISDFallingBallFragment : BaseFragment() {

    private lateinit var mAnim: ValueAnimator
    private lateinit var mAnim1: ObjectAnimator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_u_i_s_d_falling_ball, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*ValueAnimator*/
        mAnim = ValueAnimator.ofObject(FallingBallEvaluator(), Point(0, 0), Point(1000, 1000)).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
        }
        mAnim.addUpdateListener {
            val value = it.animatedValue as Point
            ball.layout(value.x, value.y, value.x + ball.width, value.y + ball.height)
        }


        /*ObjectAnimator*/
        mAnim1 = ObjectAnimator.ofObject(ball1, "fall", FallingBallEvaluator1(), Point(1000, 2000)).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
        }

    }

    override fun onResume() {
        super.onResume()
        mAnim.start()
        mAnim1.start()
    }

    override fun onPause() {
        super.onPause()
        mAnim.cancel()
        mAnim1.cancel()
    }

    class FallingBallEvaluator : TypeEvaluator<Point> {
        private val point = Point()
        override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
            point.x = startValue.x + (fraction * (endValue.x - startValue.x)).toInt()
            point.y = (startValue.y + fraction * 3 * (endValue.y - startValue.y)).toInt()
            if (point.y > 1000) {
                point.y = 1000
            }
            return point
        }
    }

    class FallingBallEvaluator1 : TypeEvaluator<Point> {
        private val point = Point()
        override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
            point.x = startValue.x + (fraction * (endValue.x - startValue.x)).toInt()
            point.y = (startValue.y + fraction * 9 * (endValue.y - startValue.y)).toInt()
            if (point.y > 2000) {
                point.y = 2000
            }
            return point
        }
    }
}