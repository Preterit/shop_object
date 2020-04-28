package com.shangyi.kt.fragment.categroy

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCategroyRightBinding
import com.shangyi.kt.fragment.categroy.adapter.BannerDataBean
import com.shangyi.kt.fragment.categroy.adapter.CategroyRightAdapter
import com.shangyi.kt.fragment.categroy.adapter.CategroyRightBanner
import com.shangyi.kt.fragment.categroy.model.CategroyModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_categroy_right.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightFragment : BaseKTFragment<FragmentCategroyRightBinding, CategroyModel>() {
    override fun layoutId() = R.layout.fragment_categroy_right
    override fun vmClazz() = CategroyModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    /**
     * 适配器
     */
    private var adapter = CategroyRightAdapter()
    private var mCategroyId = 0  // 分类ID
    private var banner: Banner<BannerDataBean, CategroyRightBanner>? = null  // 轮播图banner
    private var bannerAdapter = CategroyRightBanner()   // 轮播图适配器

    /**
     * banner数据
     */
    private val list = arrayListOf<BannerDataBean>()

    override fun initObserve() {
        mBinding.vm?.categoryRightData?.observe(this, Observer {
            if (it?.child_list != null) {
                adapter.replaceData(it.child_list)
                if (it.child_list.isEmpty()) mLoadService.showCallback(EmptyCallback::class.java) else mLoadService.showSuccess()

                list.clear()
                it.category_img?.forEach { item ->
                    list.add(BannerDataBean(item))
                }
                if (list.isEmpty()) {
                    banner?.visibility = View.GONE
                } else {
                    banner?.visibility = View.VISIBLE
                }
                bannerAdapter?.setDatas(list)
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    companion object {
        fun newInstance(): CategroyRightFragment {
            val args = Bundle()
            val fragment = CategroyRightFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        mLoadService.showSuccess()
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter


        banner = mBinding.root.findViewById(R.id.banner)
        if (banner != null && bannerAdapter != null) {
            bannerAdapter?.setDatas(list)
            banner!!.setAdapter(bannerAdapter!!).setIndicator(CircleIndicator(context)).start()
        }

    }

    /**
     * 点击条目刷新数据
     */
    fun loadCategroyRightData(categroyId: Int) {
        mCategroyId = categroyId
        mBinding.vm?.getRightCategory(categroyId)
    }


    /**
     * 重试
     */
    override fun preLoad() {
        mBinding.vm?.getRightCategory(mCategroyId)
    }

    /**
     * 页面销毁释放资源
     */
    override fun onDestroy() {
        super.onDestroy()
        banner?.stop()
        banner = null
    }
}