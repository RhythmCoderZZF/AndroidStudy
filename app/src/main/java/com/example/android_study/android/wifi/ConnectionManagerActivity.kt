package com.example.android_study.android.wifi

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.LogUtil
import com.permissionx.guolindev.PermissionX

class ConnectionManagerActivity : BaseActivity() {
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    override fun getLayoutId() = R.layout.activity_android_wifi_connection_manager

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initViewAndData(savedInstanceState: Bundle?) {

        PermissionX.init(this).permissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CHANGE_WIFI_STATE)
                .request { allGranted, _, _ ->
                    if (allGranted) {
                        if (VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            connectToTankWifi29Above()
                        } else {
                            connectToTankWifi29Blew()
                        }
                    }
                }

    }

    private fun connectToTankWifi29Blew() {
        WifiUtil.startScan()
//        if (WifiUtil.connect("EZVIZ_E37249275", "EZVIZ_TSGFXQ")) {
        if (WifiUtil.connect("zzf_love_zcj", "12345678")) {

        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun connectToTankWifi29Above() {
        val specifier = WifiNetworkSpecifier.Builder()
                .setSsid("zzf_love_zcj")
//                    .setSsidPattern(PatternMatcher("ChinaNet-503", PatternMatcher.PATTERN_PREFIX))
                //            .setBssidPattern(MacAddress.fromString("10:03:23:00:00:00"), MacAddress.fromString("ff:ff:ff:00:00:00"))
                .setWpa2Passphrase("12345678")
                .build()


        val request = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .setNetworkSpecifier(specifier)
                .build()

        connectivityManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                LogUtil.d("zzz", network.toString())
                LogUtil.d("zzz", connectivityManager.bindProcessToNetwork(network).toString())
////                    network.socketFactory.
////                    val socket = network!!.socketFactory.createSocket("192.168.137.1", 12345)
//
//                //1.创建客户端Socket，指定服务器地址和端口
//                val socket = Socket("192.168.137.1", 12345)
//                //2.获取输出流，向服务器端发送信息
//                val os = socket.getOutputStream() //字节输出流
//
//                val pw = PrintWriter(os) //将输出流包装为打印流
//
//                //获取客户端的IP地址
//                val address = InetAddress.getLocalHost()
//                val ip = address.hostAddress
//                pw.write("客户端：~$ip~ 接入服务器！！")
//                pw.flush()
//                socket.shutdownOutput() //关闭输出流
//
//                socket.close()

                // do success processing here..
                runOnUiThread { showLoading(true, "连接成功！") }
            }

            override fun onUnavailable() {
                // do failure processing here..
                runOnUiThread { showLoading(true, "连接失败！") }
            }
//            ...
        }
        connectivityManager.requestNetwork(request, networkCallback)
    }
}