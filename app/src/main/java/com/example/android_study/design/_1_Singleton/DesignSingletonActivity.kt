package com.example.android_study.design._1_Singleton

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.google.android.material.transition.Hold
import kotlinx.android.synthetic.main.activity_design_singleton.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.concurrent.thread

class DesignSingletonActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_design_singleton

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn.setOnClickListener {
            repeat(10) {
                thread {
                    CEO.doWork()
                }
            }
        }
        btn1.setOnClickListener {
            repeat(10) {
                thread {
                    CEO1.getInstance().doWork()
                }
            }
        }
        btn2.setOnClickListener {
            repeat(10) {
                thread {
                    CEO2.getInstance().doWork()
                }
            }
        }
        btn3.setOnClickListener {
            repeat(10) {
                thread {
                    CEO3.getInstance().doWork()
                }
            }
        }
    }

    //1.饿汉式
    object CEO {
        fun doWork() {
            CmdUtil.v("饿汉式:${hashCode()}")
        }
    }

    //2.懒汉式
    class CEO1 private constructor() {
        companion object {
            private var INSTANCE: CEO1? = null

            @Synchronized
            fun getInstance() = INSTANCE ?: CEO1().also { INSTANCE = it }
        }

        fun doWork() {
            CmdUtil.v("懒汉式:${hashCode()}")
        }
    }

    //3.Double Check单例
    class CEO2 private constructor() {
        companion object {
            @Volatile
            private var INSTANCE: CEO2? = null

            fun getInstance(): CEO2 {
                if (INSTANCE == null) {
                    synchronized(CEO2::class) {
                        if (INSTANCE == null) {
                            INSTANCE = CEO2()
                        }
                    }
                }
                return INSTANCE!!
            }
        }

        fun doWork() {
            CmdUtil.v("双重检查:${hashCode()}")
        }
    }

    //4.静态内部类方式
    class CEO3 private constructor() {
        companion object {
            fun getInstance() = Holder.sInstance
        }

        private class Holder {
            companion object {
                val sInstance = CEO3()
            }
        }
        fun doWork() {
            CmdUtil.v("静态内部类:${hashCode()}")
        }
    }
}