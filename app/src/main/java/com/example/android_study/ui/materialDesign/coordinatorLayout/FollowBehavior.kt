package com.example.android_study.ui.materialDesign.coordinatorLayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2020/9/13
 * Description:
 *
 * 参考：https://www.jianshu.com/p/640f4ef05fb2
 *
 * 1. 自定义 CoordinatorLayout 的 Behavior， 泛型为观察者 View ( 要跟着别人动的那个 ),必须重写两个方法，layoutDependOn和onDependentViewChanged
 *
 * 2.《深入理解 Behavior》
 * 2.1 ——拦截 Touch 事件
 * 当我们为一个 CoordinatorLayout 的直接子 View 设置了 Behavior 时，这个 Behavior 就能拦截发生在这个 View 上的 Touch 事件，那么它是如何做到的呢？
 * 实际上， CoordinatorLayout 重写了 onInterceptTouchEvent() 方法，并在其中给 Behavior 开了个后门，让它能够先于 View 本身处理 Touch 事件。
 * 具体来说， CoordinatorLayout 的 onInterceptTouchEvent() 方法中会遍历所有直接子 View ，
 * 对于绑定了 Behavior 的直接子 View 调用 Behavior 的 onInterceptTouchEvent() 方法，若这个方法返回 true，
 * 那么后续本该由相应子 View 处理的 Touch 事件都会交由 Behavior 处理，而 View 本身表示懵逼，完全不知道发生了什么。

 * 2.2 ——拦截测量及布局
 * 了解了 Behavior 是怎养拦截 Touch 事件的，想必大家已经猜出来了它拦截测量及布局事件的方式 —— CoordinatorLayout 重写了测量及布局相关的方法并为 Behavior 开了个后门。
 * 没错，真相就是如此。
 * CoordinatorLayout 在 onMeasure() 方法中，会遍历所有直接子 View ，若该子 View 绑定了一个 Behavior ，就会调用相应 Behavior 的 onMeasureChild() 方法，
 * 若此方法返回 true，那么 CoordinatorLayout 对该子 View 的测量就不会进行。这样一来， Behavior 就成功接管了对 View 的测量。
 * 同样，CoordinatorLayout 在 onLayout() 方法中也做了与 onMeasure() 方法中相似的事，让 Behavior 能够接管对相关子 View 的布局。
 */
class FollowBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<TextView>(context, attrs) {



    /**
     * 这个方法在每次onDependentViewChanged前调用，dependency是parent的其中一个childView，child可以选择是否与其建立事件订阅关系
     *
     * 根据逻辑来判断返回值，返回 false 表示不依赖，返回 true 表示依赖
     *
     * 在一个交互行为中，Dependent View 的变化决定了另一个相关 View 的行为。
     * 在这个例子中， Button 就是 Dependent View，因为 TextView 跟随着它。
     * 实际上 Dependent View 就相当于我们前面介绍的被观察者
     */
    override fun layoutDependsOn(parent: CoordinatorLayout, child: TextView, dependency: View): Boolean {
        CmdUtil.i("layoutDependsOn()")
        return dependency is Button
    }

    /**
     * dependency change 回调
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: TextView, dependency: View): Boolean {
        CmdUtil.v("onDependentViewChanged()")
        child.x = dependency.x;
        child.y = dependency.y + 200;
        return true
    }
}