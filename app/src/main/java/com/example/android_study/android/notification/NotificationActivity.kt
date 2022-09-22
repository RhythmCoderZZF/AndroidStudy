package com.example.android_study.android.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bennyhuo.kotlin.coroutines.delay
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification_main.*
import kotlinx.coroutines.launch


class NotificationActivity : BaseActivity() {

    private val CHANNEL_ID = "testNotification1"
    private val CHANNEL_NAME = "测试渠道1"
    private val CHANNEL_DESCRIPTION = "测试渠道11111111111111"
    private lateinit var mNotificationManager: NotificationManager


    override fun getLayoutId() = R.layout.activity_notification_main


    override fun initViewAndData(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* 1.基础使用 */
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            channel.description = CHANNEL_DESCRIPTION
            channel.enableLights(true)
            channel.lightColor= Color.GREEN
            channel.enableVibration(true)

            mNotificationManager = getSystemService(
                NotificationManager::class.java
            )
            mNotificationManager.createNotificationChannel(channel)

            val intent = Intent(mContext, NotificationActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0)

            start.setOnClickListener {
                launch {
                    delay(5000)
                    val notificationBuilder =
                        Notification.Builder(this@NotificationActivity, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    resources,
                                    R.mipmap.ic_launcher_round
                                )
                            )
                            .setContentTitle("HELLO")
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                    val notification = notificationBuilder.build()

                        notificationBuilder.setContentText("this is a test message:$it")
                        mNotificationManager.notify(1, notification)
                }
            }
            start1.setOnClickListener {
                launch {
                    val notificationBuilder =
                        Notification.Builder(this@NotificationActivity, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    resources,
                                    R.mipmap.ic_launcher
                                )
                            )
                            .setContentTitle("WORLD")

                    val notification = notificationBuilder.build()
                    repeat(5) {
                        notificationBuilder.setContentText("this is a test message:$it")
                        mNotificationManager.notify(it, notification)
                        delay(1000)
                    }
                }
            }

            part2()
        }

    }

    /**
     * 进度条通知
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun part2() {
        start2_1.setOnClickListener {
            launch {
                val notificationBuilder =
                    Notification.Builder(this@NotificationActivity, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.mipmap.ic_launcher_round
                            )
                        )
                        .setContentTitle("Download")
                        .setProgress(
                            100,
                            0,
                            false
                        )//第一个参数是“完成”值（如 100）；第二个参数是当前完成的进度，最后一个参数表明这是一个确定性进度条。
                val notification = notificationBuilder.build()
                repeat(100) {
                    notificationBuilder.setProgress(100, it + 1, false)
                    mNotificationManager.notify(2, notification)
                    delay(100)
                }
                notificationBuilder.setContentText("Download complete")
                    .setProgress(0, 0, false)
                mNotificationManager.notify(2, notification)
            }
        }

        start2_2.setOnClickListener {
            launch {
                val notificationBuilder =
                    Notification.Builder(this@NotificationActivity, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_looks_two_24)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.mipmap.ic_launcher
                            )
                        )
                        .setContentTitle("Download2")
                        .setProgress(0, 0, true)
                val notification = notificationBuilder.build()
                mNotificationManager.notify(2, notification)
                delay(5000)
                notificationBuilder
                    .setContentText("Download2 complete")
                    .setProgress(0, 0, false)//显示操作已完成。如需移除进度条，请调用 setProgress(0, 0, false)
                mNotificationManager.notify(2, notification)
            }
        }
    }
}