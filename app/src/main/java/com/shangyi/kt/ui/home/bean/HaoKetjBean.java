package com.shangyi.kt.ui.home.bean;

import java.util.List;

/**
 * data: 2020/5/14 14:37
 * auther: Dz
 * function:
 */
public class HaoKetjBean {

    /**
     * id : 16
     * name : 鱼跃（YUWELL）水银血压计 水银台式 家用血压计（不含听诊器）
     * price : 60
     * sale_price : 99
     * weight : 0
     * sale_count : 0
     * count : 29
     * intro : http://shop.xueli001.cn/goodsIntro/16/16.html
     * unit_id : 4
     * shop_id : 1
     * msales : 0
     * discountList : []
     * goods_one_img : {"good_id":16,"url":" http://shopvideo.xueli001.cn/4picture2020051315112792"}
     * dealer : {"gid":16,"cash_back":0,"normal_dealer":5}
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
    private DealerBean dealer;
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

    public DealerBean getDealer() {
        return dealer;
    }

    public void setDealer(DealerBean dealer) {
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
         * good_id : 16
         * url :  http://shopvideo.xueli001.cn/4picture2020051315112792
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

    public static class DealerBean {
        /**
         * gid : 16
         * cash_back : 0
         * normal_dealer : 5
         */

        private int gid;
        private int cash_back;
        private int normal_dealer;

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getCash_back() {
            return cash_back;
        }

        public void setCash_back(int cash_back) {
            this.cash_back = cash_back;
        }

        public int getNormal_dealer() {
            return normal_dealer;
        }

        public void setNormal_dealer(int normal_dealer) {
            this.normal_dealer = normal_dealer;
        }
    }
}
