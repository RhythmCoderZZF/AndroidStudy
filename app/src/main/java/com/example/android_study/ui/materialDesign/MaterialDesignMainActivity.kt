package com.example.android_study.ui.materialDesign

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui.materialDesign.appbarlayout.AppbarLayoutActivity
import com.example.android_study.ui.materialDesign.bug.CoordinatorLayoutBugActivity
import com.example.android_study.ui.materialDesign.coordinatorLayout.CoordinatorLayoutActivity
import com.example.android_study.ui.materialDesign.sample.MaterialAy
import kotlinx.android.synthetic.main.activity_material_design_main.*

class MaterialDesignMainActivity : BaseActivity(){
    override fun getLayoutId()=R.layout.activity_material_design_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.CoordinatorLayout", CoordinatorLayoutActivity::class.java),
                Entry("2.AppBarLayout", AppbarLayoutActivity::class.java),
                Entry("3.整合案例", MaterialAy::class.java),
                Entry("4.CoordinatorLayout bug", CoordinatorLayoutBugActivity::class.java,"嵌套fragmnet的滑动问题")
        ))
    }
}