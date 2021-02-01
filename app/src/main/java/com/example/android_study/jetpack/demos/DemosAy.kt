package com.example.android_study.jetpack.demos

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.jetpack.demos.navigation_viewmodel.NavigationViewModelAy
import com.example.android_study.jetpack.demos.viewmodel.ScoreBoardAy
import kotlinx.android.synthetic.main.activity_demos_ay.*

class DemosAy : BaseActivity() {
    private val list = listOf(
            //1. DataBinding + ViewModel
            Entry("1. ViewModel", ScoreBoardAy::class.java),
            //2. DataBinding + ViewModel + Navigation
            Entry("2. Navigation + ViewModel", NavigationViewModelAy::class.java)
            //3. BottomNavigationView + Navigation
//            Entry("3. app模板", BottomNavigationAy::class.java)
    )

    override fun getLayoutId() = R.layout.activity_demos_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
    }
}