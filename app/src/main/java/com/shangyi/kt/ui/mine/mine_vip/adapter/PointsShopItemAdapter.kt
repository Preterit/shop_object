package com.shangyi.kt.ui.mine.mine_vip.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_points_shop.view.*

/**
 * Date:2020/5/21
 * author:lwb
 * Desc:
 */
class PointsShopItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_points_shop) {

    init {
        setList(arrayListOf("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580155&di=01cc0dd30423594d60dc617a0abb7c6b&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580155&di=3d0c15676076345a47f2b19577588696&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2247852322%2C986532796%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580155&di=66ccd7c5dff61a276deb39660218ad04&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D3204887199%2C3790688592%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D4610%26h%3D2968",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580155&di=e1593f817bf664760c9e9e10a36ac176&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D3363001160%2C1163944807%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D830",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580155&di=3d68971604717b82d0775fe710462025&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D583874135%2C70653437%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D3607%26h%3D2408",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580154&di=2cbeb3e92d7520d93b9eed1f4c986b6f&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D2268908537%2C2815455140%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D719",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043580153&di=b9f6f41a5053bb53b3c30d6c00789609&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D3225540498%2C2642373837%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1162%26h%3D1800",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043655725&di=3bae271410064445bbff09d4b2f8bd2c&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D1179872664%2C290201490%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D854",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590043657102&di=81353f47f29329c7c72b53d8e270c037&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D3421853287%2C2856753836%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D2048%26h%3D1152"))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.glideImageView.loadImage(item)
    }
}