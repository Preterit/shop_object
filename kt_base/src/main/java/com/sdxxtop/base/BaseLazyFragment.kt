package com.sdxxtop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.sdxxtop.base.dialog.LoadingDialog
import com.sdxxtop.base.lifecycle.FragmentLifecycleImpl
import com.sdxxtop.base.load.IPreLoad

/**
 *
 */
abstract class BaseLazyFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment(), IVMView<VM>,IPreLoad {

    companion object {
        const val TAG = "BaseLazyFragment"
    }

    lateinit var mBinding: DB
    lateinit var mLoadService: LoadService<Any>

    val mViewModel: VM by lazy {
        ViewModelProviders.of(this@BaseLazyFragment)[vmClazz()]
    }

    val mLoadingDialog by lazy {
        LoadingDialog(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<DB>(inflater, layoutId(), container, false)
        lifecycle.addObserver(FragmentLifecycleImpl(javaClass.simpleName))
        mBinding.lifecycleOwner = this
        rootView = mBinding.root
        val loadSirBindView = loadSirBindView()
        if (loadSirBindView == null) {
            mLoadService = LoadSir.getDefault().register(mBinding.root) {
                preLoad()
            }
            return mLoadService.loadLayout
        } else {
            //自定义的一个加载界面
            mLoadService = LoadSir.getDefault().register(loadSirBindView) {
                preLoad()
            }
            return mBinding.root
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
        //初始化完view
        mIsViewCreated = true

        initObserve()
        initEvent()

        // 本次分发主要时用于分发默认tab可见状态，这种状况下它的生命周期是：1 fragment setUserVisibleHint: true-》onAttach-》onCreate-》onCreateView-》onResume
        // 默认 Tab getUserVisibleHint() = true !isHidden() = true
        // 对于非默认 tab 或者非默认显示的 Fragment 在该生命周期中只做了 isViewCreated 标志位设置 可见状态将不会在这里分发
        // 本次分发主要时用于分发默认tab可见状态，这种状况下它的生命周期是：1 fragment setUserVisibleHint: true-》onAttach-》onCreate-》onCreateView-》onResume
        // 默认 Tab getUserVisibleHint() = true !isHidden() = true
        // 对于非默认 tab 或者非默认显示的 Fragment 在该生命周期中只做了 isViewCreated 标志位设置 可见状态将不会在这里分发
        if (!isHidden && userVisibleHint) {
            dispatchUserVisibleHint(true)
        }
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

    override fun bindVM() {

    }

    override fun initEvent() {
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    private var rootView: View? = null  // rootView
    private var mIsViewCreated = false  // view是否创建完成
    private var mIsFirstVisible = true  // 是否是第一次可见
    private var currentVisibleState = false  //

    /*****  当前fragment 第一次可见  *******/
    protected abstract fun onFragmentFirstVisible()

    /*****  当前fragment resume  *******/
    protected open fun onFragmentResume() {}

    /*****  当前fragment 不可交互  *******/
    protected open fun onFragmentPause() {}

    /**
     * 用FragmentTransaction来控制fragment的hide和show时，
     * 那么这个方法就会被调用。每当你对某个Fragment使用hide
     * 或者是show的时候，那么这个Fragment就会自动调用这个方法。
     * https://blog.csdn.net/u013278099/article/details/72869175
     *
     * 你会发现使用hide和show这时fragment的生命周期不再执行，
     * 不走任何的生命周期，
     * 这样在有的情况下，数据将无法通过生命周期方法进行刷新，
     * 所以你可以使用onHiddenChanged方法来解决这问题。
     * @param hidden
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            dispatchUserVisibleHint(false)
        } else {
            dispatchUserVisibleHint(true)
        }
    }

    //修改fragment的可见性
    //setUserVisibleHint 被调用有两种情况：
    // 1） 在切换tab的时候，会先于所有fragment的其他生命周期，先调用这个函数，可以看log，
    //     对于默认 tab 和 间隔 checked tab 需要等到 isViewCreated = true 后才可以通过此通知用户可见
    //2）对于之前已经调用过setUserVisibleHint 方法的fragment后，让fragment从可见到不可见之间状态的变化
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //  对于情况1）不予处理，用 isViewCreated 进行判断，如果isViewCreated false，说明它没有被创建
        if (mIsViewCreated) {
            //对于情况2）要分情况考虑：2.1）如果是不可见->可见是下面的情况 ；2.2）如果是可见->不可见是下面的情况
            //对于2.1）我们需要如何判断呢？首先必须是可见的（isVisibleToUser 为true）
            // 而且只有当可见状态进行改变的时候才需要切换（此时就添加了currentVisibleState来辅助判断），否则会出现反复调用的情况
            //从而导致事件分发带来的多次更新
            //对于2.2）如果是可见->不可见，判断条件恰好和 2.1）相反
            if (isVisibleToUser && !currentVisibleState) {
                dispatchUserVisibleHint(true)
            } else if (!isVisibleToUser && currentVisibleState) {
                dispatchUserVisibleHint(false)
            }
        }
    }

    /**
     * 统一处理用户可见信息分发
     * 分第一次可见，可见，不可见分发
     * @param isVisible
     */
    private fun dispatchUserVisibleHint(isVisible: Boolean) {
        //为了代码严谨
        if (currentVisibleState == isVisible) {
            return
        }
        currentVisibleState = isVisible
        if (isVisible) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false
                onFragmentFirstVisible()
            }
            onFragmentResume()
        } else {
            onFragmentPause()
        }
    }

    /**
     * 只有当当前页面由可见状态转变到不可见状态时才需要调用 dispatchUserVisibleHint
     * currentVisibleState && getUserVisibleHint() 能够限定是当前可见的 Fragment
     */
    override fun onPause() {
        super.onPause()
        if (currentVisibleState && userVisibleHint) {
            dispatchUserVisibleHint(false)
        }
    }

    /**
     * 当页面可以交互
     */
    override fun onResume() {
        super.onResume()
        //在滑动或者跳转的过程中，第一次创建fragment的时候均会调用onResume方法，类似于在tab1 滑到tab2，此时tab3会缓存，这个时候会调用tab3 fragment的
        //onResume，所以，此时是不需要去调用 dispatchUserVisibleHint(true)的，因而出现了下面的if
        if (!mIsFirstVisible) {
            //由于Activit1 中如果有多个fragment，然后从Activity1 跳转到Activity2，此时会有多个fragment会在activity1缓存，此时，如果再从activity2跳转回
            //activity1，这个时候会将所有的缓存的fragment进行onResume生命周期的重复，这个时候我们无需对所有缓存的fragnment 调用dispatchUserVisibleHint(true)
            //我们只需要对可见的fragment进行加载，因此就有下面的if
            if (!isHidden && !currentVisibleState && userVisibleHint) {
                dispatchUserVisibleHint(true)
            }
        }
    }

    /**
     * 页面销毁
     */
    override fun onDestroyView() {
        super.onDestroyView()
        mIsViewCreated = false
        mIsFirstVisible = false
    }

}