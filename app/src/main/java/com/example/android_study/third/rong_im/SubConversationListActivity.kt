package com.example.android_study.third.rong_im

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import io.rong.imkit.fragment.SubConversationListFragment

class SubConversationListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_conversation_list)

        val subconversationListFragment = SubConversationListFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, subconversationListFragment)
        transaction.commit();
    }
}