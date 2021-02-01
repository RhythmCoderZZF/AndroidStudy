package com.example.android_study.ui_third.AAChartCore

import android.os.Bundle
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.*
import com.aachartmodel.aainfographics.AAInfographicsLib.AAOptionsModel.*
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_a_a_chart_core_main.*

class AAChartCoreMainActivity : BaseActivity() {

    private val oneHour = arrayOf("00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00", "07.00", "08.00", "09.00",
            "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00")


    override fun getLayoutId() = R.layout.activity_a_a_chart_core_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val aaChartModel: AAChartModel = AAChartModel()
                .chartType(AAChartType.Spline)
                .title("title")
                .subtitle("subtitle")
                .backgroundColor("#ffffff")
                .legendEnabled(false)//显示下方图例
                .tooltipEnabled(false)//显示弹窗
                .markerSymbol(AAChartSymbolType.Square)//连接点形状————————————
                .markerRadius(0f)//连接点半径
                .dataLabelsEnabled(true)//连接点数据显示
//                .scrollablePlotArea(AAScrollablePlotArea()//配置可滑动的
//                        .minWidth(3000)//x宽度
//                        .scrollPositionX(0f))!!//初始移动到x=0位置
                .series(arrayOf(
                        AASeriesElement()
                                .name("温度")
                                .data(arrayOf(14, 25, 32, 12, 40))
                ))


        val aaPlotLinesElementsArr: Array<AAPlotLinesElement> = arrayOf<AAPlotLinesElement>(
                AAPlotLinesElement()
                        .color("#AEE8D6") //颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.Solid) //样式：Dash,Dot,Solid等,默认Solid
                        .width(1f) //标示线粗细
                        .value(12f) //所在位置
                        .zIndex(1) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
//                        .label(AALabel()
//                                .text("PLOT LINES ONE")
//                                .style(AAStyle()
//                                        .color("#1e90ff")
//                                        .fontWeight(AAChartFontWeightType.Bold)
//                                )
//                        )
                ,
                AAPlotLinesElement()
                        .color("#FFBABA") //颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.Solid) //样式：Dash,Dot,Solid等,默认Solid
                        .width(1f) //标示线粗细
                        .value(24f) //所在位置
                        .zIndex(1) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前

        )
        val aaOptions: AAOptions = AAOptionsConstructor.configureChartOptions(aaChartModel)
                .yAxis(AAYAxis()
                        .plotLines(aaPlotLinesElementsArr)
                        .visible(true)
                        .gridLineWidth(0f)
                        .labels(AALabels()
                                .enabled(false))
                        .title(AATitle().text("")))

                .xAxis(AAXAxis().categories(oneHour))

        chartView.aa_drawChartWithChartOptions(aaOptions)
//        chartView.aa_drawChartWithChartModel(aaChartModel)
    }
}