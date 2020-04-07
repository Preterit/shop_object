package com.shangyi.kt.ui

import androidx.fragment.app.Fragment
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityMainBinding
import com.shangyi.kt.fragment.CarFragment
import com.shangyi.kt.fragment.CategroyFragment
import com.shangyi.kt.fragment.HomeFragment
import com.shangyi.kt.fragment.MineFragment
import com.shangyi.kt.ui.userlogin.model.MainModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseKTActivity<ActivityMainBinding, MainModel>() {

    override fun layoutId() = R.layout.activity_main
    override fun vmClazz() = MainModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {

    }

    private var currentFragment: Fragment? = null
    private var newFragment: Fragment? = null
    private var homeFragment: Fragment? = null
    private var categroyFragment: Fragment? = null
    private var carFragment: Fragment? = null
    private var mineFragment: Fragment? = null

    override fun initView() {
        initFirstFragment()
        cusomBottomTab.setOnMenuClickListener {
           switchFragment(it)
        }
    }

    /**
     * 初始化第一个Fragment
     */
    private fun initFirstFragment() {
        homeFragment = HomeFragment()
        currentFragment = homeFragment
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, currentFragment!!).commitAllowingStateLoss()
    }

    /**
     * 切换Fragment
     *
     * @param position
     */
    private fun switchFragment(position: Int) {
        when (position) {
            0 -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance()
                }
                newFragment = homeFragment
            }
            1 -> {
                if (categroyFragment == null) {
                    categroyFragment = CategroyFragment.newInstance()
                }
                newFragment = categroyFragment
            }
            2 -> {
                if (carFragment == null) {
                    carFragment = CarFragment.newInstance()
                }
                newFragment = carFragment
            }
            3 -> {
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance()
                }
                newFragment = mineFragment
            }
        }
        setCurrentFragment()
    }

    //设置底部按钮被选中
    private fun setCurrentFragment() {
        if (newFragment !== currentFragment) {
            val transaction = supportFragmentManager.beginTransaction()
            if (newFragment!!.isAdded) {
                transaction.show(newFragment!!)
                newFragment!!.onResume()
            } else {
                transaction.add(R.id.frameLayout, newFragment!!)
            }
            transaction.hide(currentFragment!!).commit()
            currentFragment = newFragment
        }
    }

}
