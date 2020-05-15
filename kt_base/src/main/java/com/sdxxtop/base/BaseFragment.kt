package com.sdxxtop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.sdxxtop.base.dialog.LoadingDialog
import com.sdxxtop.base.lifecycle.FragmentLifecycleImpl
import com.sdxxtop.base.load.IPreLoad

/**
 * Date:2020/5/14
 * author:lwb
 * Desc:
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment(), IVMView<VM>, IPreLoad {

    companion object {
        const val TAG = "BaseLazyFragment"
    }

    lateinit var mBinding: DB
    lateinit var mLoadService: LoadService<Any>

    val mViewModel: VM by lazy {
        ViewModelProviders.of(this@BaseFragment)[vmClazz()]
    }

    val mLoadingDialog by lazy {
        LoadingDialog(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<DB>(inflater, layoutId(), container, false)
        lifecycle.addObserver(FragmentLifecycleImpl(javaClass.simpleName))
        mBinding.lifecycleOwner = this
        val loadSirBindView = loadSirBindView()

        return if (loadSirBindView == null) {
            mLoadService = LoadSir.getDefault().register(mBinding.root) {
                preLoad()
            }
            mLoadService.loadLayout
        } else {
            //自定义的一个加载界面
            mLoadService = LoadSir.getDefault().register(loadSirBindView) {
                preLoad()
            }
            mBinding.root
        }
    }

    open fun loadSirBindView(): View? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindVM()
        mBinding.executePendingBindings()

        initView()
        initSelfObserve()
        initObserve()
        initEvent()
    }

    private fun initSelfObserve() {
        mViewModel.mIsLoadingShow.observe(viewLifecycleOwner, Observer {
            if (it) {
                mLoadingDialog.show()
            } else {
                mLoadingDialog.dismiss()
            }
        })
    }

    override fun preLoad() {
    }

    override fun initEvent() {
    }

    override fun loadData() {
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }
}