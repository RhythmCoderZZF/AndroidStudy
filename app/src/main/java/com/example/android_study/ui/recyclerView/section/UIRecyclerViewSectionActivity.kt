package com.example.android_study.ui.recyclerView.section

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.ui.recyclerView.section.adapter.SectionAdapter
import kotlinx.android.synthetic.main.activity_rv.*

const val DEFAULT_SHOW_LIST = 3

class UIRecyclerViewSectionActivity : BaseActivity() {
    private val mAdapter = SectionAdapter()
    private var mList: MutableList<Bean>? = null

    private val originList = mutableListOf(
            OriginBean("1", listOf(
                    Sub("a"),
                    Sub("b"),
                    Sub("c"),
                    Sub("d"),
                    Sub("e")
            )),
            OriginBean("2", listOf(
                    Sub("f"),
                    Sub("g"),
                    Sub("h"),
            )),
            OriginBean("3", listOf(
                    Sub("i"),
                    Sub("j"),
                    Sub("k"),
                    Sub("l"),
            )),
            OriginBean("4", listOf(
                    Sub("m"),
                    Sub("n"),
                    Sub("o"),
                    Sub("p"),
                    Sub("q"),
                    Sub("r"),
            ))
    )


    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {

        createViewList()
        rv.apply {
            adapter = mAdapter
            mAdapter.submitList(mList)
            mAdapter.expendListener = { data ->
                changeExpendTip(data)
            }
        }

    }

    private fun changeExpendTip(data: Bean.Footer) {
        val itemIndex = data.itemIndex
        val notifyStartIndex = data.notifyStartIndex

        //每次点击展开，增加三条显示数据（注意会大于数据条数）
        val originShowCount = data.contentShowSize
        data.contentShowSize = originShowCount + DEFAULT_SHOW_LIST
        val fixShowCount = data.contentShowSize

        //1. 要显示的条数>=sub list 的条数：显示剩余隐藏的条数|隐藏【展开】
        if (fixShowCount >= originList[itemIndex].sub.size) {
            val itemCount = fixShowCount - originList[itemIndex].sub.size //6-4=2 6-5=1 6-6=0
            val needNotifyCount = DEFAULT_SHOW_LIST - itemCount//剩余要显示的条数

            for (i in notifyStartIndex..notifyStartIndex + needNotifyCount) {
                mList?.let {
                    if (it[i] is Bean.Content) {
                        (it[i] as Bean.Content).show = true
                    } else if (it[i] is Bean.Footer) {
                        (it[i] as Bean.Footer).show = false
                    }
                }
            }
            mAdapter.notifyItemRangeChanged(data.notifyStartIndex, needNotifyCount + 1)

            //2. 要显示的条数<sub list 的条数：显示要显示的条数就可以
        } else {
            for (i in notifyStartIndex..notifyStartIndex + DEFAULT_SHOW_LIST) {
                mList?.let {
                    (it[i] as Bean.Content).show = true
                }
            }
            mAdapter.notifyItemRangeChanged(data.notifyStartIndex, DEFAULT_SHOW_LIST)
        }
    }

    private fun createViewList() {
        mList = mutableListOf()
        mList?.apply {
            var expendIndex = 0//记录【展开】的position
            var listSize = 0
            originList.forEachIndexed { itemIndex, header ->
                this.add(Bean.Header(header.string))
                expendIndex++
                listSize += 2//加一个header和footer[展开]
                header.sub.forEachIndexed { index, s ->
                    var contentShow = false//content是否显示（当大于默认显示的条数就不显示）
                    listSize++
                    if (index < DEFAULT_SHOW_LIST) {
                        contentShow = true
                        expendIndex++
                    }
                    this.add(Bean.Content(s.str, contentShow))
                }
                if (header.sub.size > DEFAULT_SHOW_LIST) {
                    this.add(Bean.Footer(itemIndex, expendIndex, true))
                } else {
                    this.add(Bean.Footer(itemIndex, expendIndex, false))
                }
                expendIndex = listSize
            }
        }

    }
}