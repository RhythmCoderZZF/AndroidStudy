package com.example.android_study.android.activity.fragment.withAnimation

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.commit
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.android.activity.fragment.SampleFragment
import com.example.android_study.android.activity.fragment.withAnimation.shareElementFragment.ShareElementAFg
import kotlinx.android.synthetic.main.activity_calendar_ay.*
import kotlinx.android.synthetic.main.activity_fragment_with_animation.*

class FragmentWithAnimationActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_fragment_with_animation

    override fun initViewAndData(savedInstanceState: Bundle?) {
        /* 1.使用动画框架 ——————————————————————————————————————————————————————————————*/
        btn_add_fragment.setOnClickListener {
            supportFragmentManager.commit {
                //注意这里可以使用Animation动画也可以使用Animator动画
                setCustomAnimations(
                    R.anim.fragment_in,
                    R.anim.fragment_out,
                    R.anim.pop_add_show,
                    R.anim.pop_add_hide
                )
                setReorderingAllowed(true)
                addToBackStack(null)
                val fragment = SampleFragment()
                add(R.id.container, fragment)
            }
        }
        btn_remove_fragment.setOnClickListener {
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.fragment_in,
                    R.anim.fragment_out,
                    R.anim.pop_add_show,
                    R.anim.pop_add_hide
                )
                setReorderingAllowed(true)
                remove(supportFragmentManager.fragments.last())
            }
        }

        btn_pop_fragment.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        /* 2.使用transition ———————————————————————————————————————————————————————————— */
        btn_add_fragment1.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                val fragment = SampleFragment().apply {
                    enterTransition = TransitionInflater.from(this@FragmentWithAnimationActivity)
                        .inflateTransition(R.transition.fade)
                    exitTransition = TransitionInflater.from(this@FragmentWithAnimationActivity)
                        .inflateTransition(R.transition.slide)
                }
                add(R.id.container1, fragment)
            }
        }
        btn_remove_fragment1.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                remove(supportFragmentManager.fragments.last())
            }
        }

        btn_pop_fragment1.setOnClickListener {
            supportFragmentManager.popBackStack()
        }

        /* 3.共享元素过渡 ————————————————————————————————————————————————————————————————————————*/
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace(R.id.container2, ShareElementAFg::class.java, null)
        }
    }
}