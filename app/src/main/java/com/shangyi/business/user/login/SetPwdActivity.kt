package com.shangyi.business.user.login

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySetPwdBinding
import kotlinx.android.synthetic.main.activity_set_pwd.*

class SetPwdActivity : BaseKTActivity<ActivitySetPwdBinding, LoginModel>() {

    override fun vmClazz() = LoginModel::class.java
    override fun layoutId() = R.layout.activity_set_pwd

    private var registerphone = "" // 注册电话
    private var registerCode = ""  // 注册验证码
    private var password = ""  // 登陆密码

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.registerSuccess?.observe(this, Observer<Boolean> { aBoolean ->
            if (aBoolean) {
                UIUtils.showToast("注册成功")
                var intent = Intent(this, SetUserinfoActivity::class.java)
                intent.putExtra("registerphone", registerphone)
                intent.putExtra("password", password)
                startActivity(intent)
            }
        })
    }

    override fun initView() {
        registerphone = intent?.getStringExtra("registerphone") ?: ""
        registerCode = intent?.getStringExtra("registerCode") ?: ""
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegist -> {
                register()
                startActivity(Intent(this, SetUserinfoActivity::class.java))
            }
        }
    }

    /**
     * 注册
     */
    private fun register() {
        val etPwd = etPwd.text.toString().trim()
        password = etSecondPwd.text.toString().trim()

        if ((TextUtils.isEmpty(etPwd) || TextUtils.isEmpty(password)) && etPwd != password) {
            UIUtils.showToast("请确定密码的准确性")
            return
        }

        mBinding.vm?.register(registerphone, registerCode, etPwd, password)
    }

}
