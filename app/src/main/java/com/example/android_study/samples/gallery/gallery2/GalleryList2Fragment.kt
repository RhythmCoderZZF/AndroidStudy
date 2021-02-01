package com.example.android_study.samples.gallery.gallery2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study.samples.gallery.gallery2.adapter.GalleryList2Adapter
import com.example.android_study.samples.gallery.model.GalleryListViewModel
import com.example.android_study.samples.gallery.model.GalleryListViewModelFactory
import com.example.android_study.samples.gallery.model.GalleryRepository
import kotlinx.android.synthetic.main.fragment_gallery_list.*
import kotlinx.coroutines.Job

/**
 * 画廊首页
 */

class GalleryList2Fragment : BaseFragment() {
    private val galleryAdapter = GalleryList2Adapter()
    private lateinit var viewModel: GalleryListViewModel
    lateinit var job: Job

    init {
        showLifecycle = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel = ViewModelProvider(this, GalleryListViewModelFactory(GalleryRepository())).get(GalleryListViewModel::class.java)
        viewModel.galleryLiveData.observe(viewLifecycleOwner) {
            galleryAdapter.submitList(it)
            if (refreshLayout.isRefreshing)
                refreshLayout.finishRefresh()
        }
        refreshLayout.autoRefresh()
        refreshLayout.setOnRefreshListener {
            job = viewModel.fetchData()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
       job.cancel()
    }


    private fun initView() {
        rv.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        }
    }
}