package com.shangyi.kt.ui.mine.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityChangeAddressBinding
import com.shangyi.kt.ui.address.AddressListActivity
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.mine.bean.OrderDetailAddress
import com.shangyi.kt.ui.mine.order.model.OrderListFragmentModel
import kotlinx.android.synthetic.main.activity_change_address.*
import kotlinx.android.synthetic.main.item_goods_detail_goodsinfo.view.*

class ChangeAddressActivity : BaseKTActivity<ActivityChangeAddressBinding, OrderListFragmentModel>() {

    override fun layoutId() = R.layout.activity_change_address
    override fun vmClazz() = OrderListFragmentModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.changeAds?.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }

    private var orderId = 0  // 订单ID
    private var address: OrderDetailAddress? = null  // 地址
    private var addressId = -1  // 地址ID

    override fun initView() {
        orderId = intent.getIntExtra("orderId", 0)
        address = intent.getParcelableExtra("address")

        if (address != null) {
            tvName.text = "收货人：${address?.recipient}"
            tvNumber.text = address?.mobile
            tvAddress.text = "收货地址：${address?.country ?: ""}${address?.province ?: ""}${address?.city ?: ""}${address?.county ?: ""}${address?.detail}"
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.chooseAddressLayout -> {
                // 选择地址
                val intent = Intent(this@ChangeAddressActivity, AddressListActivity::class.java)
                startActivityForResult(intent, 11)
            }
            R.id.tvCancel -> {
                // 取消
                finish()
            }
            R.id.tvChange -> {
                // 修改地址
                if (addressId == -1) {
                    UIUtils.showToast("当前地址未改动")
                    return
                }
                mBinding.vm?.changeAddress(orderId, addressId)
            }
        }
    }

    /**
     * 开启activity收到的结果。
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        if (requestCode == 11) {
            val item = data.getParcelableExtra<AreaListBean?>("areaBean")
            addressId = item!!.id
            tvName.text = "收货人：${item.recipient}"
            tvNumber.text = item.mobile
            tvAddress.text = "收货地址：${item.provice?.name}${item.city?.name}${item.county?.name}${item.detail}"
        }
    }
}
