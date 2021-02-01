package com.example.android_study.kotlin.collection.stream_sequence

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_collection_stream_sequence.*

/**
 *
 * 1 Sequence：=Java8的Stream
 * Sequence属于流式API，调用terminal操作符才会启动流，其中的元素会依次通过操作符
 *
 * 2 操作符：
 * ———— filter: 过滤出符合条件的元素
 * ———— map：元素->元素，最后将所有元素拼接成一个list
 * ———— flatMap：元素->List，最后将所有list拼接成一个list
 *
 * 3 terminal操作符（具备遍历功能,一般在序列结尾用于启动序列的阀门）
 * ———— forEach:
 * ———— fold: 初始initial + element -> 生成新的initial + element2 -> ...
 * ———— toList: 遍历item添加到一个list中
 */

class KotlinCollectionStreamSequenceActivity : BaseActivity() {
    private val list = listOf(0, 1, 2, 3, 4)

    override fun getLayoutId() = R.layout.activity_kotlin_collection_stream_sequence

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /*集合映射*/
        translate_1.setOnClickListener {
            translate_1_r.text = ""
            val res = list.filter {
                CmdUtil.v("filter:$it")
                it % 2 == 0                         //过滤出集合[0,2,4]
            }.map {
                CmdUtil.i("map:$it")
                it * 2                              //映射出集合[0,4,8]
            }.flatMap {
                CmdUtil.e("flatMap:$it")
                0..it                               //映射出集合[0,0,1,2,3,0,1,2,3,4,5,6,7,8]
            }
            translate_1_r.text = res.toString()
        }


        /*懒序列-集合映射*/
        translate_2.setOnClickListener {
            translate_2_r.text = ""
            list.asSequence()
                    .filter {
                        CmdUtil.v("filter:$it")
                        it % 2 == 0                     //已0为例：过滤出元素0
                    }.map {
                        CmdUtil.i("map:$it")
                        it * 2                          //映射出元素0
                    }.flatMap {
                        CmdUtil.e("flatMap:$it")
                        0..it                           //映射出元素0
                    }.forEach {
                        CmdUtil.e("forEach:$it")        //遍历出元素0
                        translate_2_r.append("$it,")
                    }
        }

        /*懒序列-聚合fold*/
        translate_3.setOnClickListener {
            translate_3_r.text = ""
            val res = list.asSequence()
                    .filter {
                        CmdUtil.v("filter:$it")
                        it % 2 == 0
                    }.fold(StringBuilder()) { acc, i ->
                        CmdUtil.i("fold:$i")
                        acc.append("\"$i\",")
                    }
            translate_3_r.text = res
        }

        /*聚合fold*/
        translate_4.setOnClickListener {
            translate_4_r.text = ""
            val res = list
                    .filter {
                        CmdUtil.v("filter:$it")
                        it % 2 == 0
                    }.fold(StringBuilder()) { acc, i ->
                        CmdUtil.i("fold:$i")
                        acc.append("\"$i\",")
                    }
            translate_4_r.text = res
        }

        /*——————————————————————————————构造sequence的几种方式——————————————————————————————————*/

        build_1.setOnClickListener {
            build_1_text.text = ""
            /*sequenceOf()*/
            val sequenceOf = sequenceOf("q", "w", "e")
            build_1_text.text = sequenceOf.toList().toString()

            /*list.asSequence()*/
            val listOf = listOf("a", "b", "c.txt").asSequence()
            build_1_text.append("\n${listOf.toList()}")

            /*
            generateSequence()
            注意generateSequence需要声明序列结束标志，否则生成无限序列
            */
            var generateSequence = generateSequence(0) { it + 1 }.take(3)
            build_1_text.append("\n${generateSequence.toList()}")

            var generateSequence1 = generateSequence(0) { it + 1 }.takeWhile { it < 3 }
            build_1_text.append("\n${generateSequence1.toList()}")

            var generateSequence2 = generateSequence(0) { if (it < 2) it + 1 else null }
            build_1_text.append("\n${generateSequence2.toList()}")

            /*sequence{}组块*/
            val sequence = sequence {
                yield(1)
                yieldAll(listOf(2, 3))
                yieldAll(generateSequence(4) { it + 1 })
            }
            build_1_text.append("\n${sequence.take(5).toList()}")
        }
    }
}