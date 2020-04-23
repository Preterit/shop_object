package com.shangyi.kt.fragment.car.entity;

import com.shangyi.kt.ui.goods.bean.GoodsImg;

import java.util.List;

/**
 * Created by zhangqie on 2016/11/26.
 * 购物车
 */
public class CartInfo {
    public int id;
    public String name;
    public Object shop_avatar;
    public List<ItemsBean> child;
    public boolean ischeck = false;

    public static class ItemsBean {
        public int goods_id;
        public int cid;
        public String name;
        public float price;
        public float sale_price;
        public GoodsUnit goods_unit;
        public int count;//库存量
        public int number;//购物车数量
        public List<GoodsImg> goods_img;

        public boolean ischeck = false;
    }

    public static class GoodsUnit {
        public int id;
        public String name;
    }

    public static class GoodsImg {
        public String url;
    }
}
