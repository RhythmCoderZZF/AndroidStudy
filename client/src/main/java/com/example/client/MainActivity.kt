package com.example.client

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.client._Android.content_provider.AndroidContentProviderMainActivity
import com.example.client.base.BaseActivity
import com.example.client.base.Entry
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView(rv, listOf(
                Entry("1.ContentProvider",AndroidContentProviderMainActivity::class.java)
        ))
    }
}