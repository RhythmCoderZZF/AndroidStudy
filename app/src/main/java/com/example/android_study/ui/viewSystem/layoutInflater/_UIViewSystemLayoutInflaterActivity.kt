package com.example.android_study.ui.viewSystem.layoutInflater

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_view_post.*
import kotlinx.android.synthetic.main.activity_u_i_view_system_windowmanager.*
import kotlin.concurrent.thread

class _UIViewSystemLayoutInflaterActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_u_i_view_system_view_post

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        textViewInner.text="text=<p><strong class=\"ql-size-large\">北极狐</strong><strong class=\"ql-size-large\" style=\"color: rgb(51, 51, 51);\">&nbsp;</strong><strong class=\"ql-size-large\">（犬科动物）</strong></p><p>北极狐（学名：<em>Vulpes lagopus</em>）：体长50-60厘米，尾长20-25厘米，体重2.5-4千克。颜面窄，嘴尖，耳圆，尾毛蓬松，尖端白色。冬季全身体毛为白色，仅鼻尖为黑色；夏季体毛为灰黑色，腹面颜色较浅。具有很密的绒毛和较少的针毛，可在零下50℃的冰原上生活。足底毛特别厚。</p><p>单独或结群活动。食物主要为旅鼠，也吃鱼、鸟、鸟蛋、贝类、北极兔和浆果等。为珍贵毛皮兽，已人工繁殖，称蓝狐、白狐等，突变品种如影狐、北极珍珠狐、北极蓝宝石狐、北极白金狐和白色北极狐等，统称为彩色北极狐。</p><p>北极狐分布于北极地区，活动于整个北极范围，其中包括：俄罗斯，加拿大，阿拉斯加，格陵兰和斯瓦尔巴群岛的外缘，以及亚北极和高山地区，如冰岛和斯堪的纳维亚大陆。</p>"

    }

    override fun onResume() {
        super.onResume()
        window.decorView.post {
            CmdUtil.i("${text.width}|${text.height}")

            CmdUtil.e("innerTV:${textViewInner.width},${textViewInner.height}")
            CmdUtil.e("scrollView:${scrollView.width},${scrollView.height}")
        }

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            CmdUtil.e("--innerTV:${textViewInner.width},${textViewInner.height}")
            CmdUtil.e("--scrollView:${scrollView.width},${scrollView.height}")
        }
        window.decorView.viewTreeObserver.addOnDrawListener {
            CmdUtil.e("draw")
        }
    }

}