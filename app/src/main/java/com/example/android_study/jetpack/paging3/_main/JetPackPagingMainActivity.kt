package com.example.android_study.jetpack.paging3._main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.jetpack.paging3._main.ui.adapter.ArticleAdapter
import com.example.android_study.jetpack.paging3._main.ui.adapter.PagingLoadStateAdapter
import com.example.android_study.jetpack.paging3._main.viewModel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_jet_pack_paging_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class JetPackPagingMainActivity : BaseActivity() {

    private val viewModel by viewModels<ArticleViewModel>()
    private val mAdapter: ArticleAdapter by lazy { ArticleAdapter() }
    override fun getLayoutId() = R.layout.activity_jet_pack_paging_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        rv.apply {
            layoutManager = LinearLayoutManager(this@JetPackPagingMainActivity)
            adapter = mAdapter.apply {
                addLoadStateListener { loadState ->
                    //下拉刷新
                    refreshLayout.isRefreshing = loadState.refresh == LoadState.Loading
//                    //下一页加载
//                    showLoading(loadState.append == LoadState.Loading)
//                if (loadState.prepend == LoadState.Loading) showToast("预加载")
                }

            }.withLoadStateHeaderAndFooter(
                    PagingLoadStateAdapter { mAdapter.retry() }, PagingLoadStateAdapter { mAdapter.retry() }
            )
        }

        refreshLayout.setOnRefreshListener {
            mAdapter.refresh()
        }
        lifecycleScope.launch {
            viewModel.getHomeArticles().collectLatest { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }

        change.setOnClickListener {
            viewModel.changeFirst()
            mAdapter.notifyDataSetChanged()
        }
    }

}