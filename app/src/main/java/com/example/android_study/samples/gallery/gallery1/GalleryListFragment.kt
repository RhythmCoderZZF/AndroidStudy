package com.example.android_study.samples.gallery.gallery1

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
import com.example.android_study.samples.gallery.gallery1.adapter.GalleryListAdapter
import com.example.android_study.samples.gallery.model.GalleryListViewModel
import com.example.android_study.samples.gallery.model.GalleryListViewModelFactory
import com.example.android_study.samples.gallery.model.GalleryRepository
import kotlinx.android.synthetic.main.fragment_gallery_list.*

/**
 * 画廊首页
 */

class GalleryListFragment : BaseFragment() {
    private val galleryAdapter = GalleryListAdapter()
    private lateinit var viewModel: GalleryListViewModel

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
            viewModel.fetchData()
        }

    }

    private fun initView() {
        rv.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        }
    }
}