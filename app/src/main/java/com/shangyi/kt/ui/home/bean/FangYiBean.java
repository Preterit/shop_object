package com.shangyi.kt.ui.home.bean;

import java.util.List;

/**
 * data: 2020/5/14 14:37
 * auther: Dz
 * function:
 */
public class FangYiBean {

    /**
     * id : 4
     * name : Apple AirPods 配充电盒 Apple蓝牙耳机
     * price : 1246
     * sale_price : 1246
     * weight : 186
     * sale_count : 0
     * count : 1998
     * intro : http://shop.xueli001.cn/goodsIntro/4/erji.html
     * unit_id : 1
     * shop_id : 3
     * msales : 0
     * discountList : []
     * goods_one_img : {"good_id":4,"url":"http://shopvideo.xueli001.cn/goods_img/04/70e84d1d269937ed.jpg"}
     * dealer : null
     */

    private int id;
    private String name;
    private int price;
    private int sale_price;
    private int weight;
    private int sale_count;
    private int count;
    private String intro;
    private int unit_id;
    private int shop_id;
    private int msales;
    private GoodsOneImgBean goods_one_img;
    private Object dealer;
    private List<?> discountList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSale_count() {
        return sale_count;
    }

    public void setSale_count(int sale_count) {
        this.sale_count = sale_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getMsales() {
        return msales;
    }

    public void setMsales(int msales) {
        this.msales = msales;
    }

    public GoodsOneImgBean getGoods_one_img() {
        return goods_one_img;
    }

    public void setGoods_one_img(GoodsOneImgBean goods_one_img) {
        this.goods_one_img = goods_one_img;
    }

    public Object getDealer() {
        return dealer;
    }

    public void setDealer(Object dealer) {
        this.dealer = dealer;
    }

    public List<?> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<?> discountList) {
        this.discountList = discountList;
    }

    public static class GoodsOneImgBean {
        /**
         * good_id : 4
         * url : http://shopvideo.xueli001.cn/goods_img/04/70e84d1d269937ed.jpg
         */

        private int good_id;
        private String url;

        public int getGood_id() {
            return good_id;
        }

        public void setGood_id(int good_id) {
            this.good_id = good_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
