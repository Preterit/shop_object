package com.shangyi.kt.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCategroyRightBinding
import com.shangyi.kt.fragment.adapter.BannerDataBean
import com.shangyi.kt.fragment.adapter.CategroyRightAdapter
import com.shangyi.kt.fragment.adapter.CategroyRightBanner
import com.shangyi.kt.fragment.model.CategroyModel
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

    val list = arrayListOf<BannerDataBean>(
            BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586409537520&di=822a382c65d0f55959b7f04270bdca45&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F78%2F52%2F01200000123847134434529793168.jpg"),
            BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586409537520&di=b1787552e950717ce509b7c17f20e350&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg")

    )

    override fun initObserve() {
        mBinding.vm?.categoryRightData?.observe(this, Observer {
            if (it?.child_list != null) {
                adapter.replaceData(it.child_list)
                if (it.child_list.isEmpty()) mLoadService.showCallback(EmptyCallback::class.java) else mLoadService.showSuccess()

                list.add(BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586409537519&di=cd122fc5be60e833babf1f18c1d53e7d&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D888206991%2C1760071208%26fm%3D191%26app%3D48%26wm%3D1%2C13%2C90%2C45%2C0%2C7%26wmo%3D10%2C10%26n%3D0%26g%3D0n%26f%3DJPEG%3Fsec%3D1853310920%26t%3D5737bdb8b0a6dccf99f9e8ae009bb4fc"))
                bannerAdapter.setDatas(list)
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
        if (banner != null) {
            bannerAdapter.setDatas(list)
            banner!!.setAdapter(bannerAdapter).setIndicator(CircleIndicator(context)).start()
        }

    }

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