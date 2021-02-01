package com.example.android_study.android.drawable_and_graph.transtionDrawable

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.fragment._lifecycle.FragmentDemoAy
import com.example.android_study.android.fragment.fragmentManager.FragmentManagerActivity
import kotlinx.android.synthetic.main.activity_android_drawable_transition.*
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidTransitionDrawableActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_android_drawable_transition

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val transition= ResourcesCompat.getDrawable(
                resources,
                R.drawable.transition_drawable,
                null
        ) as TransitionDrawable

        val image: ImageView = findViewById(R.id.iv)
        image.setImageDrawable(transition)

        // Description of the initial state that the drawable represents.
        image.contentDescription ="hello!"



        // After the transition is complete, change the image's content description
        // to reflect the new state.
        button.setOnClickListener {
            // Then you can call the TransitionDrawable object's methods.
            transition.startTransition(1000)
        }
    }
}