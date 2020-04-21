package com.shangyi.kt.ui.address

import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAddAddressBinding
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.address.model.AddAddressModel
import com.shangyi.kt.ui.address.weight.AddressSelectDialog
import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddressActivity : BaseKTActivity<ActivityAddAddressBinding, AddAddressModel>() {


    override fun layoutId() = R.layout.activity_add_address
    override fun vmClazz() = AddAddressModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }


    private var id1 = 0
    private var id2 = 0
    private var id3 = 0
    private var areaBean: AreaListBean? = null
    private var adsId = -1  // 修改地址的ID，该ID存在，则为修改，不存在，则是新建。

    /**
     * 位置选择弹框
     */
    private val addressdialog: AddressSelectDialog by lazy {
        val dialog = AddressSelectDialog(this@AddAddressActivity)
        dialog.setOnAddressSelectListener { address, _id1, _id2, _id3 ->
            tvAddress.text = address
            id1 = _id1
            id2 = _id2
            id3 = _id3
        }
        dialog
    }

    override fun initObserve() {
        mBinding.vm?.isAddSuccess?.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }


    override fun initView() {
        areaBean = intent.getParcelableExtra<AreaListBean>("areaBean")
        if (areaBean != null) {
            edName.setText(areaBean?.recipient ?: "")
            edNumber.setText(areaBean?.mobile ?: "")
            tvAddress.text = "${areaBean?.provice?.name}-${areaBean?.city?.name}-${areaBean?.county?.name}"
            addressDetail.setText(areaBean?.detail)
            checkbox.isChecked = areaBean?.is_default == 1
            adsId = areaBean?.id ?: -1
            id1 = areaBean?.province_id ?: 0
            id2 = areaBean?.city_id ?: 0
            id3 = areaBean?.county_id ?: 0
            deleteAddress.visibility = View.VISIBLE
        } else {
            deleteAddress.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvAddress -> {
                // 选择地址
                addressdialog.show()
            }

            R.id.saveAddress -> {
                // 保存地址
                commitAddress()
            }

            R.id.deleteAddress ->{
                // 删除地址
                mBinding.vm?.deleteAddress(adsId)
            }
        }
    }

    /**
     * 提交地址信息
     */
    private fun commitAddress() {
        if (edName.text.toString().trim().isEmpty()) {
            UIUtils.showToast("请填写收货人姓名")
        }
        if (edNumber.text.toString().trim().isEmpty()) {
            UIUtils.showToast("请填写收货人手机号")
        }
        if (tvAddress.text.toString().trim().isEmpty()) {
            UIUtils.showToast("请填写收货地址")
        }
        if (addressDetail.text.toString().trim().isEmpty()) {
            UIUtils.showToast("请填写详细地址")
        }
        mBinding.vm?.saveAddress(
                edName.text.toString().trim(),
                edNumber.text.toString().trim(),
                addressDetail.text.toString().trim(),
                id1,
                id2,
                id3,
                if (checkbox.isChecked) 1 else 0,
                adsId
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        addressdialog.onDestroy()
    }

}
