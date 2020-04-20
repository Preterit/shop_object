package com.shangyi.kt.ui.address

import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAddAddressBinding
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
                if (checkbox.isChecked) 1 else 0
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        addressdialog.onDestroy()
    }

}
