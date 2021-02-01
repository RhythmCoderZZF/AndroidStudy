package com.example.android_study.third.rong_im

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import io.rong.imkit.fragment.ConversationFragment

class ConversationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        // 添加会话界面
        val conversationFragment = ConversationFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, conversationFragment)
        transaction.commit()
    }
}