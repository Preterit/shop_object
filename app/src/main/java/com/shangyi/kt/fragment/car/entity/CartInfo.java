package com.shangyi.kt.fragment.car.entity;

import com.shangyi.kt.ui.goods.bean.DealerBean;

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

    @Override
    public String toString() {
        return "CartInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", child=" + child +
                ", ischeck=" + ischeck +
                '}';
    }

    public static class ItemsBean {
        public int goods_id;
        public int cid;
        public String name;
        public float price;
        public float sale_price;
        public DealerBean dealer;
        public GoodsUnit goods_unit;
        public int count;//库存量
        public int number;//购物车数量
        public List<GoodsImg> goods_img;
        public SpecBean spec;

        public boolean ischeck = false;

        @Override
        public String toString() {
            return "ItemsBean{" +
                    "goods_id=" + goods_id +
                    ", cid=" + cid +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", sale_price=" + sale_price +
                    '}';
        }
    }

    public static class SpecBean {
        public String value;
        public float price;
        public float sale_price;
        public String image;
        public int stock;
        public int id;
    }

    public static class GoodsUnit {
        public int id;
        public String name;
    }

    public static class GoodsImg {
        public String url;
    }
}
