package com.shangyi.kt.ui.home.bean;

import java.util.List;

/**
 * data: 2020/5/13 17:41
 * auther: Dz
 * function:
 */
public class GaoYongBean {

    /**
     * id : 1
     * name : iphoneXS max
     * price : 0.01
     * sale_price : 0.01
     * weight : 0
     * sale_count : 9
     * count : 188
     * intro : http://shop.xueli001.cn/goodsIntro/2/iphonexsmax.html
     * unit_id : 4
     * shop_id : 1
     * msales : 9
     * discountList : [{"id":6,"type":1,"shop_id":null,"price":100,"full_price":1000,"start_time":"2020-05-08 09:29:54","end_time":"2020-05-16 09:29:54"},{"id":7,"type":2,"shop_id":null,"price":300,"full_price":0,"start_time":"2020-05-08 09:31:01","end_time":"2020-05-17 09:31:01"},{"id":8,"type":3,"shop_id":null,"price":500,"full_price":0,"start_time":"2020-05-08 09:31:37","end_time":"2020-05-17 09:31:37"}]
     * goods_one_img : {"good_id":1,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3161910997,3512955785&fm=26&gp=0.jpg"}
     * dealer : {"gid":1,"cash_back":1,"normal_dealer":1}
     */

    private int id;
    private String name;
    private double price;
    private double sale_price;
    private int weight;
    private int sale_count;
    private int count;
    private String intro;
    private int unit_id;
    private int shop_id;
    private int msales;
    private GoodsOneImgBean goods_one_img;
    private DealerBean dealer;
    private List<DiscountListBean> discountList;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
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

    public List<DiscountListBean> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountListBean> discountList) {
        this.discountList = discountList;
    }

    public static class GoodsOneImgBean {
        /**
         * good_id : 1
         * url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3161910997,3512955785&fm=26&gp=0.jpg
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
         * gid : 1
         * cash_back : 1
         * normal_dealer : 1
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

    public static class DiscountListBean {
        /**
         * id : 6
         * type : 1
         * shop_id : null
         * price : 100
         * full_price : 1000
         * start_time : 2020-05-08 09:29:54
         * end_time : 2020-05-16 09:29:54
         */

        private int id;
        private int type;
        private Object shop_id;
        private int price;
        private int full_price;
        private String start_time;
        private String end_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getShop_id() {
            return shop_id;
        }

        public void setShop_id(Object shop_id) {
            this.shop_id = shop_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getFull_price() {
            return full_price;
        }

        public void setFull_price(int full_price) {
            this.full_price = full_price;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
