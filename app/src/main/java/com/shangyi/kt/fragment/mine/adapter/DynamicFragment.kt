package com.shangyi.kt.fragment.mine.adapter

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseNormalFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentTestBinding
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
class DynamicFragment : BaseNormalFragment<FragmentTestBinding>() {

    companion object {
        fun newInstance(): DynamicFragment {
            val args = Bundle()
            val fragment = DynamicFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun layoutId() = R.layout.fragment_test

    override fun initView() {
        smartLayout.setEnableRefresh(false)
        smartLayout.setOnLoadMoreListener {
            it.finishLoadMore()
        }

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = BottomAdapter()
    }

    override fun initData() {

    }

    override fun loadData() {

    }

}