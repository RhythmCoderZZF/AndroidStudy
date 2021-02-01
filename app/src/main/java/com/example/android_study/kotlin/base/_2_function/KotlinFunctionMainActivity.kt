package com.example.android_study.kotlin.base._2_function

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import kotlinx.android.synthetic.main.activity_kotlin_function_main.*

/**
 * 1 函数
 * 2 扩展函数
 * 3 解构
 * 4 扩展属性
 */
class KotlinFunctionMainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_function_main)

        /**
         * 1 函数
         */
        val f: (Int) -> Unit = fun(p0: Int) { tvFun.append("$p0\n") }
        val f2: (Int) -> Unit = ::a
        val f3: (Int) -> Unit = this::a
        useNoReceiver(f)
        useNoReceiver(f2)
        useNoReceiver(f3)

        /**
         * 2 扩展函数——带Receiver类型的函数，该函数必须在Receiver对象中调用或者第一个param传入Receiver
         *
         *    2.1 r1定义的的函数实际上有两个参数，第一个是Receiver KotlinFunctionMainActivity(编译器隐藏掉了)实际上就是r2的写法
         *    2.2 r3是带Receiver的匿名函数的写法
         *        r4是引用写法
         */
        val r1: KotlinFunctionMainActivity.(Int) -> Unit = KotlinFunctionMainActivity::a

        val r2: (KotlinFunctionMainActivity, Int) -> Unit = KotlinFunctionMainActivity::a

        /* r1，r2 等价于 val r3: Function2<KotlinFunctionMainActivity, Int, Unit>=KotlinFunctionMainActivity::a */

        useReceiver(r1)
        useReceiver(r2)

        val r3: Receiver.(String) -> String = fun Receiver.(str: String) = str + this.end //匿名未初始化Receiver
        val r4: Receiver.(String) -> String = Receiver::append                             //未初始化Receiver

        val a3 = Receiver().r3("hello")
        val a4 = r4(Receiver(), "world")

        val r5: (String) -> String = Receiver()::append                                  //已经初始化Receiver

        val a5 = r5("> <!")

        tvFun.append("$a3\n$a4\n$a5")




        /**
         * 3 解构
         */

        val (a, b, c) = Triple(1, 2, 3)
        tvDestruct.text = "${a + b + c}"

        /**
         * 4 扩展属性
         */
        tvProperty.text = "$property"

    }

    fun a(p0: Int) {
        tvFun.append("$p0\n")
    }

    /*参数action是函数类型，且带Receiver，则action必须在Receiver中才能调用*/
    private fun useReceiver(action: KotlinFunctionMainActivity.(p0: Int) -> Unit) {
        action(2) //action 等价于 action.invoke(this, 1)
    }

    private fun useNoReceiver(action: (Int) -> Unit) {
        action(1)
    }
}


class Receiver {
    val end: String = ""
        get() = "$field<<<"         //backing field属于类

    fun append(str: String) = str + end
}

/* property = backing field + get() +set(value)
*
*  接口只能定义行为（方法），不能保存东西（field），所以没有backing field
*  扩展属性不属于类，所以也没有backing field
*  */
var KotlinFunctionMainActivity.property: Int
    //  err: get() = field              //扩展属性没有backing field
    get() = 8
    set(value) {

    }

interface Guy {
    // err: var money = 0                  //接口没有backing field
    var money: Int
        get() = 100
        set(value) {}

    fun printMoney(){
        print("no money hahaha!")
    }
}