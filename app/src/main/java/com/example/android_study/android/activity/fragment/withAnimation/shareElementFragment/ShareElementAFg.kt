package com.example.android_study.android.activity.fragment.withAnimation.shareElementFragment

import android.os.Bundle
import android.service.autofill.ImageTransformation
import android.transition.ChangeBounds
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.commit
import androidx.transition.ChangeImageTransform
import androidx.transition.Slide
import com.example.android_study.R
import com.example.android_study._base.BaseFragment

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class ShareElementAFg : BaseFragment() {
    init {
        showLifecycle=true
    }
    private lateinit var root: View
    private lateinit var img: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition=ChangeBounds()
//        sharedElementReturnTransition=ChangeBounds()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share_element_a, container, false)
            .also { root = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img = root.findViewById<ImageView>(R.id.img)
        img.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addSharedElement(img,"big_img")
                addToBackStack(null)
                replace(R.id.container2,ShareElementBFg::class.java,null)
            }
        }
    }
}