package com.shangyi.kt.ui.order

import android.content.Intent
import android.widget.Toast
import com.sdxxtop.base.BaseNormalActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityPaySuccessBinding
import com.shangyi.kt.fragment.other.lookmore.LookMoreFragment
import com.shangyi.kt.ui.mine.order.OrderInfoActivity
import kotlinx.android.synthetic.main.activity_pay_success.*

class PaySuccessActivity : BaseNormalActivity<ActivityPaySuccessBinding>() {

    override fun layoutId() = R.layout.activity_pay_success

    override fun initView() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, lookMoreFragment).commit()

        val totalPrice = intent.getFloatExtra("totalPrice", 0f)
        tvPrice.text = "¥${totalPrice}"
        llShopContinue.setOnClickListener {
            Toast.makeText(this, "继续购物", Toast.LENGTH_SHORT).show()
        }
        llLookOrder.setOnClickListener {
            val intent = Intent(this@PaySuccessActivity, OrderInfoActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
    }
}
