package com.shangyi.kt.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityMainBinding
import com.shangyi.business.network.Constants
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.fragment.car.CarFragment
import com.shangyi.kt.fragment.categroy.CategroyFragment
import com.shangyi.kt.fragment.home.HomeFragment
import com.shangyi.kt.fragment.mine.MineFragment
import com.shangyi.kt.ui.userlogin.LoginActivity
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
                setCurrentFragment()
            }
            1 -> {
                if (categroyFragment == null) {
                    categroyFragment = CategroyFragment.newInstance()
                }
                newFragment = categroyFragment
                setCurrentFragment()
            }
            2 -> {
                if (carFragment == null) {
                    carFragment = CarFragment.newInstance()
                }
                newFragment = carFragment
                if (isLogin()) {
                    setCurrentFragment()
                    cusomBottomTab.setViewEnable()
                }
            }
            3 -> {
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance()
                }
                newFragment = mineFragment
                if (isLogin()) {
                    setCurrentFragment()
                    cusomBottomTab.setViewEnable()
                }
            }
        }

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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // 登陆成功 进行状态切换
        if (SpUtil.getInt(Constants.USER_ID, -1) != -1) {
            setCurrentFragment()
            cusomBottomTab.setViewEnable()
        }
    }

    /**
     * 判断是否登陆过。
     *
     * @return
     */
    fun isLogin(): Boolean {
        var isLogin = false
        val userID = SpUtil.getInt(Constants.USER_ID, -1)
        if (userID != -1) {
            isLogin = true
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        return isLogin
    }
}
