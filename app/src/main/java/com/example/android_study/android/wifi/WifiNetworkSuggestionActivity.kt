package com.example.android_study.android.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import android.net.wifi.WifiNetworkSuggestion
import android.net.wifi.hotspot2.PasspointConfiguration
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import androidx.databinding.DataBindingUtil
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.LogUtil
import com.example.android_study.databinding.ItemMediaDouyinGsyBinding
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.PrintWriter
import java.net.InetAddress
import java.net.Socket

class WifiNetworkSuggestionActivity : BaseActivity() {
    private var wifiManager: WifiManager? = null
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    override fun getLayoutId() = R.layout.activity_android_wifi_connection_manager

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initViewAndData(savedInstanceState: Bundle?) {
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        PermissionX.init(this).permissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CHANGE_WIFI_STATE ,android.Manifest.permission.ACCESS_WIFI_STATE)
                .request { allGranted, _, _ ->
                    if (allGranted) {
                        launch(IO) {
//                            addNetworkAndroidQ("HYSJ-DMB", "hysj1234")
                            connectUsingNetworkSuggestion("HYSJ-DMB", "hysj1234")
                        }
                    }
                }

    }

    @WorkerThread
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun connectUsingNetworkSuggestion(ssid: String, password: String) {
        val wifiNetworkSuggestion = WifiNetworkSuggestion.Builder()
                .setSsid(ssid)
                .setWpa2Passphrase(password)
                .setIsAppInteractionRequired(true)
                .build()

        // Optional (Wait for post connection broadcast to one of your suggestions)
        val intentFilter = IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION);

        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.i("zzz" , "${intent.action}")
                if (!intent.action.equals(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                    return
                }
                Log.i("zzz" , "Connection Suggestion Succeeded")
                // do post connect processing here
            }
        }
        registerReceiver(broadcastReceiver, intentFilter)
        wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val suggestionsList = listOf(wifiNetworkSuggestion)
        var status = wifiManager?.addNetworkSuggestions(suggestionsList)
        Log.i("zzz", "Adding Network suggestions status is $status")
        if (status == WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE) {
            status = wifiManager?.removeNetworkSuggestions(suggestionsList)
            Log.i("zzz", "Removing Network suggestions status is $status")
            status = wifiManager?.addNetworkSuggestions(suggestionsList)
        }
        if (status == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
            Log.i("zzz", "Suggestion Added")
        }
    }

}