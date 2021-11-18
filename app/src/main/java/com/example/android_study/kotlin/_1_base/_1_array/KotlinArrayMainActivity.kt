package com.example.android_study.kotlin._1_base._1_array

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import kotlinx.android.synthetic.main.activity_kotlin_array_main.*

class KotlinArrayMainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_array_main)

        /**
         * 数组
         */

        /*——————————————————————初始化——————————————————————*/
        //基本类型数组
        val intArray = IntArray(3)                  //IntArray:初始值为0
        val intArray1 = IntArray(3) { it + 1 }      //IntArray
        val intArray2 = intArrayOf(1, 2, 3)         //IntArray

        //对象类型数组
        val intArray3 = arrayOfNulls<Int>(3)        //Array<Int?>
        val intArray4 = arrayOf(1, 2, 3)            //Array<Int>
        val intArray5 = Array(3) { it }             //Array<Int>

        tvIntArray.text = "${intArray.contentToString()}\n${intArray1.contentToString()}\n${intArray2.contentToString()}\n${intArray3.contentToString()}\n${intArray4.contentToString()}\n${intArray5.contentToString()}"

        /*——————————————————————遍历——————————————————————*/
        for (i in intArray5) {
            tvForEach.append("$i,")
        }

        intArray5.forEach {
            tvForEach.append("$it,")
        }

        intArray5.forEachIndexed { index, i ->
            tvForEach.append("${intArray5[index]},")
        }

        for(index in intArray5.indices){
            tvForEach.append("${intArray5[index]},")
        }

        if (3 !in intArray5) {
            tvForEach.append("\n‘3’不在数组${intArray5.contentToString()}中！")
        }


        /**
         * 区间
         */
        val intRange = 0..5
        val intRangeStep = 0..5 step 2
        val intRangeExclusive = 0 until 5
        val intRangeExclusiveStep = 0 until 5 step 2
        val intRangeReverse = 5 downTo 0


        val floatRange = 0f..1f         //float区间是个无线float元素的区间

        tvRange.text = """
            ${intRange.joinToString()}
            ${intRangeStep.joinToString()}
            ${intRangeExclusive.joinToString()}
            ${intRangeExclusiveStep.joinToString()}
            ${intRangeReverse.joinToString()}
            ${0.5f in floatRange}
        """.trimIndent()
    }
}