package com.shangyi.kt.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.sdxxtop.base.BaseNormalActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGuideBinding
import com.shangyi.business.network.Constants
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.MainActivity
import kotlinx.android.synthetic.main.activity_guide.*


class GuideActivity : BaseNormalActivity<ActivityGuideBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
    }

    override fun layoutId() = R.layout.activity_guide

    override fun initView() {
        //监听ViewPager滑动
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    tvBtnBg.visibility = View.VISIBLE
                } else {
                    tvBtnBg.visibility = View.GONE
                }
            }
        })

        val list = arrayListOf<View>(
                View.inflate(this, R.layout.guide_1_view, null),
                View.inflate(this, R.layout.guide_2_view, null),
                View.inflate(this, R.layout.guide_3_view, null)
        )
        mViewPager.adapter = GuideAdapter(list)

        tvBtnBg.setOnClickListener {
            SpUtil.putBoolean(Constants.FIRST_OPEN, true)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
