package com.example.android_study.jetpack.demo_wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study.jetpack.demo_wanandroid.adapters.HomeAdapter
import com.example.android_study.jetpack.demo_wanandroid.net.Resource
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var mAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(HomeViewModel::class.java)
        setUpView()

        viewModel.liveArticles.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let {
                        mAdapter.submitList(it.data.datas)
                    }
                }
                is Resource.Loading -> showProgressBar()
                is Resource.Error -> Toast.makeText(activity, "${resource.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setUpView() {
        mAdapter=HomeAdapter()
        rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(activity,RecyclerView.VERTICAL))
        }
    }


}