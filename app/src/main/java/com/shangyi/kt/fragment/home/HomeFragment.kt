package com.shangyi.kt.fragment.home

import android.os.Bundle
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.home.model.HomeModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class HomeFragment : BaseKTFragment<FragmentHomeBinding, HomeModel>() {

    override fun vmClazz() = HomeModel::class.java
    override fun layoutId() = R.layout.fragment_home
    private var mCategroyId = 0  // 分类ID

    /**
     * 适配器
     */
    /*private var adapter = CategroyRightAdapter()
    private var homebanner: Banner<BannerDataBean, CategroyRightBanner>? = null  // 轮播图banner
    private var bannerAdapter = CategroyRightBanner()   // 轮播图适配器
*/
    /**
     * banner数据
     */
   // private val list = arrayListOf<BannerDataBean>()


    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
       /* mBinding.vm?.categoryRightData?.observe(this, Observer {
            if (it?.child_list != null) {
                adapter.replaceData(it.child_list)
                if (it.child_list.isEmpty()) mLoadService.showCallback(EmptyCallback::class.java) else mLoadService.showSuccess()

                list.clear()
                it.category_img?.forEach { item ->
                    list.add(BannerDataBean(item))
                }
                if (list.isEmpty()) {
                    homebanner?.visibility = View.GONE
                } else {
                    homebanner?.visibility = View.VISIBLE
                }
                bannerAdapter?.setDatas(list)
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })*/
    }



    override fun initView() {
        mLoadService.showSuccess()

        //homebanner = mBinding.root.findViewById(R.id.homebanner)
        /*if (homebanner != null && bannerAdapter != null) {
            bannerAdapter?.setDatas(list)
            homebanner!!.setAdapter(bannerAdapter!!).setIndicator(CircleIndicator(context)).start()
        }*/

    }

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        mBinding.vm?.getRightCategory(mCategroyId)
    }



    /**
     * 重试
     */
    override fun preLoad() {
        mBinding.vm?.getRightCategory(mCategroyId)
    }


    override fun onDestroy() {
        super.onDestroy()
        homebanner?.stop()
       // homebanner = null
    }
}

