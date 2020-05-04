package com.shangyi.kt.ui.mine.collect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shangyi.business.R
import com.study.glidemodel.GlideImageView

class CollectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)

        val ivimg1 = findViewById<ImageView>(R.id.ivImg1)
        val ivimg2 = findViewById<GlideImageView>(R.id.GlideImageView)
        Glide.with(this).load("http://shopvideo.xueli001.cn/1picture2020042413362158").into(ivimg1)
        ivimg2.loadImage("http://shopvideo.xueli001.cn/1picture2020042413362158")
    }
}
