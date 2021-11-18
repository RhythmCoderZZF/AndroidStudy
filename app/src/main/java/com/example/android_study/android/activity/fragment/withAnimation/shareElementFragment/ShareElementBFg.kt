package com.example.android_study.android.activity.fragment.withAnimation.shareElementFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionSet
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class ShareElementBFg : BaseFragment() {
    init {
        showLifecycle = true
    }

    private lateinit var root: View
    private lateinit var img: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition= TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeImageTransform())
        }
        sharedElementReturnTransition=TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeImageTransform())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share_element_b, container, false)
            .also { root = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        lifecycleScope.launch {
            delay(2000)
            startPostponedEnterTransition()
        }
        img = root.findViewById<ImageView>(R.id.img)
        img.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                parentFragmentManager.popBackStack()
            }
        }
    }
}