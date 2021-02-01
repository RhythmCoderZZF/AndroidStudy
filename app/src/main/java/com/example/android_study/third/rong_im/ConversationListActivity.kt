package com.example.android_study.third.rong_im

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.android_study.R
import com.example.android_study.third.rong_im.ImActivity.Companion.isZZF
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_conversation_list.*


class ConversationListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_list)

        val conversationListFragment = ConversationListFragment()
        val uri = Uri.parse("rong://" +
                this.applicationInfo.packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

        conversationListFragment.uri = uri
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, conversationListFragment)
        transaction.commit()


        if (isZZF) {
            toConversation.isVisible = false
        }
        toConversation.setOnClickListener {
            val conversationType = Conversation.ConversationType.PRIVATE;
            val targetId = "1";
            val title = "这里可以填写名称"
            RongIM.getInstance().startConversation(this, conversationType, targetId, title, null)
        }
        group.setOnClickListener {
            val conversationType = Conversation.ConversationType.GROUP
            val targetId = "12";
            val title = "❤";
            RongIM.getInstance().startConversation(this, conversationType, targetId, title, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RongIM.getInstance().disconnect()
    }
}