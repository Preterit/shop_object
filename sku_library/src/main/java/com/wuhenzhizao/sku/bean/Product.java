package com.wuhenzhizao.sku.bean;

import java.util.List;

/**
 * Created by liufei on 2017/11/30.
 */

public class Product {
    private String id;
    private String name;
    private String status;
    private String mainImage;
    private long sellingPrice;
    private long originPrice;
    private String currencyUnit;   // "¥"
    private String measurementUnit;  // "件"
    private int saleQuantity;
    private int stockQuantity;
    private List<String> medias;
    private List<Sku> skus;

//    public static Product get(Context context) {
//        String json = context.getString(R.string.product);
//        return new Gson().fromJson(json, new TypeToken<Product>() {
//        }.getType());
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(long originPrice) {
        this.originPrice = originPrice;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<String> getMedias() {
        return medias;
    }

    public void setMedias(List<String> medias) {
        this.medias = medias;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", originPrice=" + originPrice +
                ", currencyUnit='" + currencyUnit + '\'' +
                ", measurementUnit='" + measurementUnit + '\'' +
                ", saleQuantity=" + saleQuantity +
                ", stockQuantity=" + stockQuantity +
                ", medias=" + medias +
                ", skus=" + skus +
                '}';
    }
}
