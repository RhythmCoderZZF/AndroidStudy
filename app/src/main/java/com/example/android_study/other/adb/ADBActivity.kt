package com.example.android_study.other.adb

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_other_adb.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/28
 * Description:
 */
class ADBActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_other_adb

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn_open.setOnClickListener {
            val root=RootCmd.haveRoot()
            CmdUtil.i("root 权限?:$root")
            if (root) {
                RootCmd.execRootCmdSilent("setprop service.adb.tcp.port 5555\n")
                RootCmd.execRootCmdSilent("stop adbd\n")
                RootCmd.execRootCmdSilent("start adbd\n")
            }
        }
    }
}