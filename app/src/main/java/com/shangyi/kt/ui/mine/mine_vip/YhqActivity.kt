package com.shangyi.kt.ui.mine.mine_vip

import androidx.fragment.app.Fragment
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityYhqBinding
import com.shangyi.kt.ui.mine.bean.Number
import com.shangyi.kt.ui.mine.mine_vip.model.YhqModel
import com.shangyi.kt.ui.mine.order.OrderListFragment
import com.shangyi.kt.ui.mine.order.adapter.MineOrderFragmentAdapter
import kotlinx.android.synthetic.main.activity_all_order.*

class YhqActivity : BaseKTActivity<ActivityYhqBinding, YhqModel>() {

    override fun layoutId() = R.layout.activity_yhq
    override fun vmClazz() = YhqModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {

    }

    val list = arrayListOf<String>("全部", "满减券", "代金券", "兑换券")
    override fun initView() {
        val fragmentList = arrayListOf<Fragment>()
        list.forEachIndexed { index, _ ->
            fragmentList.add(YhqItemFragment.newInstence(index))
        }
        val adapter = MineOrderFragmentAdapter(supportFragmentManager, fragmentList, list)
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = list.size
    }

    /**
     * 刷新tablayout 数量
     */
    fun setTabLayoutTx(data: List<Number>?) {
        if (data == null) return
        data.forEachIndexed { index, number ->
            tablayout.getTabAt(index)!!.text = "${list[index]}(${number.number})"
        }

    }
}
