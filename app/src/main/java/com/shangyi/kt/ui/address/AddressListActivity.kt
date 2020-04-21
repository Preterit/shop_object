package com.shangyi.kt.ui.address

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAddressListBinding
import com.shangyi.kt.ui.address.adapter.AddressListAdapter
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.address.model.AddAddressModel
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseKTActivity<ActivityAddressListBinding, AddAddressModel>() {
    override fun vmClazz() = AddAddressModel::class.java
    override fun layoutId() = R.layout.activity_address_list
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    private val adapter = AddressListAdapter()

    override fun initObserve() {
        mBinding.vm?.areaBean?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                noAddressLayout.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            } else {
                noAddressLayout.visibility = View.GONE
                recyclerview.visibility = View.VISIBLE
            }
            adapter.setList(it)
        })
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.data[position] as AreaListBean
            val intent = Intent()
            var address = "${item.provice?.name}${item.city?.name}${item.county?.name}${item.detail}"
            var addressId = item?.id
            intent.putExtra("address", address)
            intent.putExtra("addressId", addressId)
            setResult(2, intent)
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addAddress -> {  // 添加地址
                startActivity(Intent(this, AddAddressActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.vm?.loadAddressList()
    }

}
