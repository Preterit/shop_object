package com.shangyi.kt.ui.pingjia

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityPingjiaBinding
import com.shangyi.kt.ui.pingjia.adapter.PingjiaContentAdapter
import com.shangyi.kt.ui.pingjia.adapter.PingjiaSelecterAdapter
import com.shangyi.kt.ui.pingjia.bean.PingjiaDataBean
import com.shangyi.kt.ui.pingjia.model.PingjiaModel
import kotlinx.android.synthetic.main.activity_pingjia.*

class PingjiaActivity : BaseKTActivity<ActivityPingjiaBinding, PingjiaModel>() {

    override fun vmClazz() = PingjiaModel::class.java
    override fun layoutId() = R.layout.activity_pingjia
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    private var goodId = 0  //	商品id
    private var page = 0  //  从第几条开始取数据
    private var type = 0  // 筛选类型(1-好评，2-中评，3-差评)
    private var img = 0 // 等于1时筛选有图评论
    private var topAdapterList = ArrayList<String>()

    private val topAdapter = PingjiaSelecterAdapter(Color.parseColor("#FF2942"), Color.parseColor("#333333"))  //  筛选条件适配器
    private val contentAdapter = PingjiaContentAdapter()  //  评论内容适配器

    override fun initObserve() {
        mBinding.vm?.pingjiaData?.observe(this, Observer {
            bandData(it)
        })
    }

    override fun initView() {
        goodId = intent.getIntExtra("goodId", 0)
        recyclerSelect.layoutManager = GridLayoutManager(this, 3)
        recyclerSelect.adapter = topAdapter

        recyclerviewContent.layoutManager = LinearLayoutManager(this)
        recyclerviewContent.adapter = contentAdapter

        topAdapter.setOnItemClickListener { adapter, view, position ->
            if (position == adapter.data.size - 1) {
                img = 1
                type = 0
            } else {
                img = 0
                type = position
            }
            mBinding.vm?.loadPingjiaData(goodId, page, type, img)
            topAdapter.setItemSelect(position)
            if (position == 0) {
                startActivity(Intent(this@PingjiaActivity, AddPinglunActivity::class.java))
            }
        }
    }

    override fun initData() {
        mBinding.vm?.loadPingjiaData(goodId, page, type, img)
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: PingjiaDataBean?) {
        if (it != null) {
            topAdapterList.clear()
            topAdapterList.add("全部 (${it.count?.total}) ")
            topAdapterList.add("好评 (${it.count?.praise}) ")
            topAdapterList.add("中评 (${it.count?.inreview}) ")
            topAdapterList.add("差评 (${it.count?.inreview}) ")
            topAdapterList.add("有图 (${it.count?.hasimg}) ")
            topAdapter.setList(topAdapterList)

            if (it.list != null) {
                if (page == 0) {
                    contentAdapter.setList(it.list)
                } else {
                    contentAdapter.addData(it.list)
                }
            }
        }
    }
}
