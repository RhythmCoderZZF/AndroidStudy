package com.example.android_study.kotlin.clazz

import com.google.android.exoplayer2.offline.DownloadService.start
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/25
 * Description:
 */
class _7_SealedAndEnumClass {
    /**
     * 枚举
     */
    @Test
    fun main() {
        val color = Color.Red
        println(color)//0:Red

        println(Color.valueOf("Red"))//0:Red
        println(Color.values().joinToString())//0:Red, 1:Orange, 2:Yellow, 3:Green

        //1.when表达式
        when (color) {
            Color.Red -> {
            }
            Color.Green -> {
            }
        }

        //2.进行比较（枚举实例是有顺序的，索引为ordinal）
        if (color < Color.Yellow) {
        }

        //3.判断区间
        if (color in Color.Red..Color.Yellow) {
        }
    }

    private enum class Color(val color: String) : Runnable {
        Red("Red"), Orange("Orange"), Yellow("Yellow"), Green("Green");

        override fun toString() = "$ordinal:$color"

        override fun run() {}
    }

    /**
     * 密封类
     */
    @Test
    fun main1() {
        val player=Player()
        player.play("《青花瓷》")
        player.play("《双截棍》")
    }

    sealed class State {
        object Idle : State()
        class Playing(val song: String) : State() {
            fun start() {
                println("start playing $song")
            }
            fun stop() {
                println("stop play $song")
            }
        }

        class PlayError(val errorInfo: String) : State() {
            fun reset() {}
        }
    }

    class Player {
        var state: State = State.Idle

        fun play(song: String) {
            state = when (val st = this.state) {
                State.Idle -> State.Playing(song).also(State.Playing::start)
                is State.Playing -> {
                    st.stop()
                    State.Playing(song).also(State.Playing::start)
                }
                is State.PlayError -> {
                    st.reset()
                    State.Playing(song).also(State.Playing::start)
                }
            }
        }
    }
}