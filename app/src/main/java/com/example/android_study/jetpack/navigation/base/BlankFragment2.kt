package com.example.android_study.jetpack.navigation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android_study.R
import kotlinx.android.synthetic.main.fragment_blank2.*


class BlankFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val findNavController = Navigation.findNavController(requireView())
        button.setOnClickListener {
            findNavController.navigate(R.id.action_blankFragment2_to_blankFragment)
        }
        val name = requireArguments()["name"]
        textView.text = name.toString()
    }
}