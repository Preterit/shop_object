package com.shangyi.kt.ui.mine.mine_vip

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityMySyBinding
import com.shangyi.kt.ui.mine.bean.MyDataBean
import com.shangyi.kt.ui.mine.mine_vip.adapter.MySyAdapter
import com.shangyi.kt.ui.mine.mine_vip.model.ShouyiModel
import kotlinx.android.synthetic.main.activity_my_sy.*
import java.text.SimpleDateFormat
import java.util.*

class MySyActivity : BaseKTActivity<ActivityMySyBinding, ShouyiModel>() {
    override fun vmClazz() = ShouyiModel::class.java
    override fun layoutId() = R.layout.activity_my_sy
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.dataList?.observe(this, Observer {
            if (it != null) {
                bandData(it)
                adapter.setList(it.list)
            }
        })
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: MyDataBean) {
        tvMonthPrice.text = "￥ ${it.month_total}"
        tvTotalPrice.text = "￥ ${it.count_total}"

    }

    private var adapter = MySyAdapter()    // 适配器。

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        val simpleDateFormat = SimpleDateFormat("yyyy.MM") // HH:mm:ss
        //获取当前时间
        val date = Date(System.currentTimeMillis())
        tvDateSelect.text = simpleDateFormat.format(date)
    }

    override fun initData() {
        mBinding.vm?.getShouyi()
    }

}
