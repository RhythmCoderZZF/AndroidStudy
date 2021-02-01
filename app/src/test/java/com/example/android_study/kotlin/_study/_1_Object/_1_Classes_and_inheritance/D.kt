package com.example.android_study.kotlin._study._1_Object._1_Classes_and_inheritance

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:调用超类
 */
class D {
    open class Rectangle {
        open fun draw() {
            println("Drawing a rectangle")
        }

        open val borderColor: String get() = "black"
    }

    class FilledRectangle : Rectangle() {
        override fun draw() {
            super.draw()
            println("Filling the rectangle")
        }

        val fillColor: String get() = super.borderColor
    }


    class RE : Rectangle() {
        override fun draw() { /* …… */
        }

        override val borderColor: String get() = "black"

        inner class Filler {
            fun fill() { /* …… */
            }

            fun drawAndFill() {
                super@RE.draw() // 调用 Rectangle 的 draw() 实现
                fill()
                println("Drawn a filled rectangle with color ${super@RE.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
            }
        }
    }


    /**
     * 覆盖规则
    在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现，
    它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
    为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>
     */
    interface Polygon {
        fun draw() { /* …… */
        } // 接口成员默认就是“open”的
    }

    class Square() : Rectangle(), Polygon {
        // 编译器要求覆盖 draw()：
        override fun draw() {
            super<Rectangle>.draw() // 调用 Rectangle.draw()
            super<Polygon>.draw() // 调用 Polygon.draw()
        }
    }
}