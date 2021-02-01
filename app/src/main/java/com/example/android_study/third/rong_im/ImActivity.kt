package com.example.android_study.third.rong_im

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.LogUtil
import com.example.android_study.ui._popupwindow.util.PopupWindowUtil
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.Group
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.activity_im.*

/**
 * 需要先申请融云appkey，生成两个用户token
 */

class ImActivity : BaseActivity() {
    companion object {
        var isZZF = true
    }

    private lateinit var mPopupWindow: PopupWindow
    private val TAG = "ImActivitytt"

    private val token = "d44tLAOaQM0GyJuP0J4qtl1fk3+GTgC8@ucn5.cn.rongnav.com;ucn5.cn.rongcfg.com" //userId:1 - name:zzf
    private val token1 = "PfH3c+w9g+wGyJuP0J4qtjtX7jV2ex0g@ucn5.cn.rongnav.com;ucn5.cn.rongcfg.com" //userId:2 - name:zcj


    override fun getLayoutId() = R.layout.activity_im

    override fun initViewAndData(savedInstanceState: Bundle?) {
        zzf.setOnClickListener {
            connectRong(zzf)
        }
        zcj.setOnClickListener {
            connectRong(zcj)
        }
        RongIM.setUserInfoProvider({
            LogUtil.d(TAG, "userInfo=$it")
            val name = when (it) {
                "1" -> "zzf"
                "2" -> "zcj"
                else -> null
            }
            UserInfo(it, name, null)
        }, true)

        RongIM.setGroupInfoProvider({
            Group(it, "Love", null)
        }, true)
    }

    private fun connectRong(anchorView: Button) {
        var tk = ""
        when (anchorView.id) {
            R.id.zzf -> {
                isZZF = true
                tk = token
            }
            R.id.zcj -> {
                isZZF = false
                tk = token1
            }
        }
        RongIM.connect(tk, object : RongIMClient.ConnectCallback() {
            override fun onSuccess(p0: String?) {
                LogUtil.v(TAG, "onSuccess $p0")

                showPopWindow(anchorView)
            }

            override fun onDatabaseOpened(p0: RongIMClient.DatabaseOpenStatus?) {
                LogUtil.v(TAG, "onDatabaseOpened ${p0.toString()}")
            }

            override fun onError(p0: RongIMClient.ConnectionErrorCode?) {
                LogUtil.e(TAG, "onError ${p0.toString()}")
                if (p0?.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)!!) {
                    //从 APP 服务获取新 token，并重连
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                    RongIM.getInstance().disconnect()
                }
            }
        })
    }

    private fun showPopWindow(anchorView: Button) {
        val contentView: View = getPopupWindowContentView()
        mPopupWindow = PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        //1. 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(ColorDrawable())
        //2. 设置pop出入动画
        mPopupWindow.animationStyle = R.style.PopupWindowAnimStyle
        //3. 偏移
        val windowPos = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView)
        mPopupWindow.showAsDropDown(anchorView, 0, 0)
    }

    private fun getPopupWindowContentView(): View {
        // 一个自定义的布局，作为显示的内容
        val layoutId = R.layout.layout_third_rong_im_popwindow
        val contentView = LayoutInflater.from(this).inflate(layoutId, null)
        val menuItemOnClickListener = View.OnClickListener { v: View ->
            when (v.id) {
                R.id.single -> {
                    val supportedConversation: MutableMap<String, Boolean> = HashMap()
                    supportedConversation[Conversation.ConversationType.PRIVATE.getName()] = false
                    RongIM.getInstance().startConversationList(this@ImActivity, supportedConversation)
                }
                R.id.group -> {
                    val supportedConversation: MutableMap<String, Boolean> = HashMap()
                    supportedConversation[Conversation.ConversationType.GROUP.getName()] = false
                    RongIM.getInstance().startConversationList(this@ImActivity, supportedConversation)
                }
            }
            mPopupWindow.dismiss()
        }
        val viewById = contentView.findViewById<TextView>(R.id.single)
        viewById.setOnClickListener(menuItemOnClickListener)
        val viewById1 = contentView.findViewById<TextView>(R.id.group)
        viewById1.setOnClickListener(menuItemOnClickListener)
        return contentView
    }
}
