package com.example.android_study.kotlin.flow.switchThread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

class kotlinFlowSwitchThreadActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_kotlin_flow_switch_thread

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    override fun run() {
        launch {

            flow {
                emit(_1())
            }.flatMapConcat {
                flow {
                    emit(_2())
                }
            }.flowOn(IO)
                    .catch {
                        CmdUtil.i("catch所在线程:${Thread.currentThread().name} ${it.message}")
                    }.collectLatest {
                        CmdUtil.i("${Thread.currentThread().name} $it")
                    }

            delay(1000)

            flow {
                emit(_1())
            }.flowOn(Main)
                    .flatMapConcat {
                        flow {
                            emit(_2())
                        }
                    }.flowOn(IO)
                    .catch {
                        CmdUtil.i("catch所在线程:${Thread.currentThread().name} ${it.message}")
                    }.collectLatest {
                        CmdUtil.i("${Thread.currentThread().name} $it")
                    }

            delay(1000)

            flow {
                emit(withContext(IO) {
                    _1()
                })
            }.flatMapConcat {
                flow {
                    emit(withContext(IO) {
                        _2()
                    })
                }
            }.catch {
                CmdUtil.i("catch所在线程:${Thread.currentThread().name} ${it.message}")
            }.collectLatest {
                CmdUtil.i("${Thread.currentThread().name} $it")
            }

        }


    }

    suspend fun _1(): Int {
        delay(1000)
        CmdUtil.v("fun 1所在线程:${Thread.currentThread().name}")
        return 1
    }

    suspend fun _2(): Int {
        delay(2000)
        CmdUtil.v("fun 2所在线程:${Thread.currentThread().name}")
        throw RuntimeException("崩溃")
        return 2
    }

}