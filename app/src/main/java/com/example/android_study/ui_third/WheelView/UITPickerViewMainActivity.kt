package com.example.android_study.ui_third.WheelView

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter
import com.contrarywind.view.WheelView
import com.example.android_study.R
import kotlinx.android.synthetic.main.activity_u_i_t_picker_view_main.*

class UITPickerViewMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_i_t_picker_view_main)
        initWheelView(wheelView1, wheelView2, wheelView3, wheelView4)
    }

    private fun initWheelView(vararg wheelView: WheelView) {
        repeat(wheelView.count()) {
            when (it) {
                0, 2 -> {
                    wheelView[it].apply {
                        adapter = ArrayWheelAdapter((0..24).toList())
                        setItemsVisibleCount(3)
                        setLabel("时")
                        setDividerColor(Color.TRANSPARENT)

                    }
                }
                else -> {
                    wheelView[it].adapter = ArrayWheelAdapter((0..59).toList())
                }
            }
        }
    }
}