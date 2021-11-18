package com.example.android_study.Java.socket

import org.junit.Test
import java.io.*
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

/**
 * Author:create by RhythmCoder
 * Date:2020/10/16
 * Description:
 */
class SocketTest {
//    val ip="192.168.51.128"
    val ip="192.168.137.1"
    val port=12345
    @Test
    fun t() {

        //1.创建客户端Socket，指定服务器地址和端口
        val socket = Socket(ip, port)
        //2.获取输出流，向服务器端发送信息
        val os = socket.getOutputStream() //字节输出流

        val pw = PrintWriter(os) //将输出流包装为打印流

        //获取客户端的IP地址
        val address = InetAddress.getLocalHost()
        val ip = address.hostAddress
        pw.write("客户端：~$ip~ 接入服务器！！")
        pw.flush()
        socket.shutdownOutput() //关闭输出流

        socket.close()
    }

    @Test
    fun server() {
        //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口

        val serverSocket = ServerSocket(port)
        val address: InetAddress = InetAddress.getLocalHost()
        val ip: String = address.getHostAddress()
        var socket: Socket? = null
        //2.调用accept()等待客户端连接
        println("~~~服务端已就绪，等待客户端接入~，服务端ip地址: $ip")
        socket = serverSocket.accept()
        //3.连接后获取输入流，读取客户端信息
        //3.连接后获取输入流，读取客户端信息
        var `is`: InputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null
        val os: OutputStream? = null
        val pw: PrintWriter? = null
        `is` = socket.getInputStream() //获取输入流

        isr = InputStreamReader(`is`, "UTF-8")
        br = BufferedReader(isr)
        var info: String? = null
        while (br.readLine().also { info = it } != null) { //循环读取客户端的信息
            println("客户端发送过来的信息$info")
        }
        socket.shutdownInput() //关闭输入流

        socket.close()
    }

}