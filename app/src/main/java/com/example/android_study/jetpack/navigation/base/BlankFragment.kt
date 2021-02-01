package com.example.android_study.jetpack.navigation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android_study.R
import kotlinx.android.synthetic.main.fragment_blank.*


class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //获取NavController
        val findNavController = Navigation.findNavController(requireView())
        val bundle = Bundle()

        button.setOnClickListener {
            val str = editTextTextPersonName.text.toString()
            if (str.isEmpty()) {
                Toast.makeText(requireContext(), "请输入name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            bundle.putString("name", editTextTextPersonName.text.toString())

            //此处设置传递arguments是通过动态传递，NavGraph中定义的arguments是动态注册
            findNavController.navigate(R.id.action_blankFragment_to_blankFragment2, bundle)
        }
    }
}