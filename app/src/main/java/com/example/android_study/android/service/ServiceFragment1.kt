package com.example.android_study.android.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_study.R
import kotlinx.android.synthetic.main.fragment_service1.*


class ServiceFragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener {
            requireActivity().startService(Intent(requireContext(),MyService::class.java))
        }

        button1.setOnClickListener {
            requireActivity().stopService(Intent(requireContext(),MyService::class.java))
        }
    }
}