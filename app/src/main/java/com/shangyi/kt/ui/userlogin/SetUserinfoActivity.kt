package com.shangyi.kt.ui.userlogin

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySetUserinfoBinding
import com.shangyi.kt.ui.MainActivity
import com.shangyi.kt.ui.userlogin.model.LoginModel
import kotlinx.android.synthetic.main.activity_set_userinfo.*
import java.text.SimpleDateFormat
import java.util.*


class SetUserinfoActivity : BaseKTActivity<ActivitySetUserinfoBinding, LoginModel>() {

    private var registerphone = "" // 手机号
    private var password = "" // 密码
    private var birthday = "" // 生日
    private var sex = 2 // 性别

    /**
     * 性别的选择框
     */
    private val sexSelectDialog: Dialog by lazy {
        var checkDialog = Dialog(this, R.style.TranslucentNoTitleDialog)
        checkDialog.setContentView(View.inflate(this, R.layout.dialog_sex_select, null))
        val checkWindow = checkDialog.window
        checkWindow?.setGravity(Gravity.BOTTOM)
        checkWindow?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        checkDialog.findViewById<TextView>(R.id.tvNv).setOnClickListener {
            sex = 0
            tvSex.text = "女"
            checkDialog.dismiss()
        }
        checkDialog.findViewById<TextView>(R.id.tvMan).setOnClickListener {
            sex = 1
            tvSex.text = "男"
            checkDialog.dismiss()
        }
        checkDialog.findViewById<TextView>(R.id.tvBaomi).setOnClickListener {
            sex = 2
            tvSex.text = "保密"
            checkDialog.dismiss()
        }
        checkDialog.findViewById<TextView>(R.id.tvCancel).setOnClickListener { checkDialog.dismiss() }
        checkDialog
    }

    /**
     * 时间选择框
     */
    private val timeSelect: TimePickerView by lazy {
        val selectedDate: Calendar = Calendar.getInstance()
        val startDate: Calendar = Calendar.getInstance()
        val endDate: Calendar = Calendar.getInstance()
        //正确设置方式 原因：注意事项有说明
        startDate.set(1899, 0, 1)
        endDate.set(2020, 11, 31)

        val pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, v -> //选中事件回调
            val format = SimpleDateFormat("yyyy-MM-dd")
            try {
                birthday = format.format(date)
                tvBirthday.text = birthday
            } finally {
                birthday = ""
            }

        })
                .setType(booleanArrayOf(true, true, true, false, false, false)) // 默认全部显示
                .setCancelText("取消") //取消按钮文字
                .setSubmitText("确认") //确认按钮文字
                .setContentTextSize(18) //滚轮文字大小
                .setTitleSize(20) //标题文字大小
                .setTitleText(" ") //标题文字
                .setOutSideCancelable(false) //点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true) //是否循环滚动
                .setTitleColor(Color.WHITE) //标题文字颜色
                .setSubmitColor(Color.parseColor("#333333")) //确定按钮文字颜色
                .setCancelColor(Color.parseColor("#999999")) //取消按钮文字颜色
                .setTitleBgColor(Color.WHITE) //标题背景颜色 Night mode
                .setBgColor(Color.WHITE) //滚轮背景颜色 Night mode
                .setDate(selectedDate) // 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate) //起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒") //默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false) //是否显示为对话框样式
                .build()
        pvTime
    }

    override fun vmClazz() = LoginModel::class.java
    override fun layoutId() = R.layout.activity_set_userinfo

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.commitInfoSuccess?.observe(this, androidx.lifecycle.Observer {
            if (it) {
                UIUtils.showToast("提交成功")
                login()
            }
        })
        mBinding.vm?.loginSuccess?.observe(this, androidx.lifecycle.Observer {
            if (it) {
                startActivity(Intent(this@SetUserinfoActivity, MainActivity::class.java))
                finish()
            }
        })
    }


    override fun initView() {
        registerphone = intent?.getStringExtra("registerphone") ?: ""
        password = intent?.getStringExtra("password") ?: ""
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivNext -> {
                // 提交信息
                commitInfo()
            }
            R.id.skip -> {
                // 跳过
                login()
            }
            R.id.tvSex -> {
                // 性别弹框
                sexSelectDialog?.show()
            }

            R.id.tvBirthday -> {
                // 时间弹框
                timeSelect.show()
            }
        }
    }

    /**
     * 提交信息
     * 0、女 1、男 2、未知
     */
    private fun commitInfo() {
        val nickname = etNiCheng.text.toString().trim()
        val qianMing = etQianMing.text.toString().trim()

        mBinding.vm?.commitInfo(registerphone, nickname, sex, birthday, qianMing)
    }

    /**
     * 登陆
     */
    fun login() {
        mBinding.vm?.login(registerphone, password, "", 1)
    }


}
