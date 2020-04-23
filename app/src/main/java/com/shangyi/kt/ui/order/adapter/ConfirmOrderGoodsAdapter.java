package com.shangyi.kt.ui.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shangyi.business.R;
import com.shangyi.kt.ui.order.bean.GoodsOrderBean;
import com.shangyi.kt.ui.pingjia.OrderDataBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * data: 2020/4/22 9:30
 * auther: Dz
 * function:
 */
public class ConfirmOrderGoodsAdapter extends RecyclerView.Adapter {

    private final int TYPE_ITEM_ONE=0;
    private final int TYPE_ITEM_TWO=1;
    private View mConfirmOrderGoodsItem;
    private View mConfirmOrderGoodsItems;

    private Context mContext;


    private ArrayList<GoodsOrderBean> mDataBeans;

    public ConfirmOrderGoodsAdapter(Context context) {
        mContext = context;
        mDataBeans = new ArrayList<GoodsOrderBean>();
    }


    public void setData(ArrayList<GoodsOrderBean> orderDataBeans) {
        this.mDataBeans.clear();
        if (orderDataBeans != null){
            this.mDataBeans.addAll(orderDataBeans);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ITEM_ONE){
            return 0;
        }else if (position == TYPE_ITEM_TWO){
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            //确认订单  单商品
            mConfirmOrderGoodsItem = LayoutInflater.from(mContext).inflate(R.layout.confirm_order_goods_item, parent,false);
            TypeOneViewHolder typeOneViewHolder = new TypeOneViewHolder(mConfirmOrderGoodsItem);
            return typeOneViewHolder;
        }else if (viewType == 1){
            //确认订单  多商品
            mConfirmOrderGoodsItems = LayoutInflater.from(mContext).inflate(R.layout.confirm_order_goods_items, parent,false);
            TypeTwoViewHolder typeTwoViewHolder = new TypeTwoViewHolder(mConfirmOrderGoodsItems);
            return typeTwoViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_ITEM_ONE://单商品
                TypeOneViewHolder typeOneViewHolder = (TypeOneViewHolder) holder;
                typeOneViewHolder.mDianpuName.setText("淘宝旗舰店");
                Glide.with(mContext).load(R.drawable.success_img).into(typeOneViewHolder.mConfirmGoodsImg);
                typeOneViewHolder.mConfirmGoodsName.setText("上医医疗器械");
                typeOneViewHolder.mGoodsColor.setText("颜色：黑色");
                typeOneViewHolder.mGoodsSize.setText("尺寸：XL");
                typeOneViewHolder.mGoodsPrice.setText("￥88");
                typeOneViewHolder.mRtOrderBeizhu.setText("随意快递");
                typeOneViewHolder.mGoodsFuPrice.setText("88");
                typeOneViewHolder.mGoodsFanPrice.setText("￥3.45");
                break;
            case TYPE_ITEM_TWO://多商品
                TypeTwoViewHolder typeTwoViewHolder = (TypeTwoViewHolder) holder;

                TypeTwoViewAdapter typeTwoViewAdapter = new TypeTwoViewAdapter(mContext);
                typeTwoViewHolder.mGoodsImgRcy.setAdapter(typeTwoViewAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
                typeTwoViewHolder.mGoodsImgRcy.setLayoutManager(linearLayoutManager);
                typeTwoViewHolder.mOrderDianpuNames.setText("上医旗舰店");
                typeTwoViewHolder.mOrderBeizhus.setText("请输入备注信息");
                typeTwoViewHolder.mGoodsFuPrices.setText("88");
                typeTwoViewHolder.mGoodsFanPrices.setText("￥4.34");
                break;
            default:
                //nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    /**
     * 单商品
     */
    public class TypeOneViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDianpuName;//店铺名称
        private final ImageView mConfirmGoodsImg;//商品图片
        private final TextView mConfirmGoodsName;//商品名称
        private final TextView mGoodsColor;//商品颜色
        private final TextView mGoodsSize;//商品尺寸
        private final TextView mGoodsPrice;//商品价格
        private final EditText mRtOrderBeizhu;//订单备注
        private final TextView mGoodsFuPrice;//商品实付价格
        private final TextView mGoodsFanPrice;//商品返利金额

        public TypeOneViewHolder(@NonNull View itemView) {
            super(itemView);
            mDianpuName = itemView.findViewById(R.id.confirm_order_dianpu_name);
            mConfirmGoodsImg = itemView.findViewById(R.id.confirm_goods_img);
            mConfirmGoodsName = itemView.findViewById(R.id.confirm_goods_name);
            mGoodsColor = itemView.findViewById(R.id.confirm_goods_color);
            mGoodsSize = itemView.findViewById(R.id.confirm_goods_size);
            mGoodsPrice = itemView.findViewById(R.id.confirm_goods_price);
            mRtOrderBeizhu = itemView.findViewById(R.id.et_confirm_order_beizhu);
            mGoodsFuPrice = itemView.findViewById(R.id.confirm_goods_fu_price);
            mGoodsFanPrice = itemView.findViewById(R.id.confirm_goods_fan_price);


        }
    }

    /**
     * 多商品
     */
    public class TypeTwoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RecyclerView mGoodsImgRcy;
        private final TextView mOrderDianpuNames;//店铺名称
        private final EditText mOrderBeizhus;//备注信息
        private final TextView mGoodsFuPrices;//实付价格
        private final TextView mGoodsFanPrices;//返利金额
        private final TextView mGoodsNums;

        public TypeTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderDianpuNames = itemView.findViewById(R.id.confirm_order_dianpu_names);
            mGoodsImgRcy = itemView.findViewById(R.id.confirm_goods_img_rcy);
            mOrderBeizhus = itemView.findViewById(R.id.et_confirm_order_beizhus);
            mGoodsFuPrices = itemView.findViewById(R.id.confirm_goods_fu_prices);
            mGoodsFanPrices = itemView.findViewById(R.id.confirm_goods_fan_prices);
            mGoodsNums = itemView.findViewById(R.id.confirm_goods_nums);

            mGoodsNums.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.confirm_goods_nums://展示具体商品信息

                    break;
                default:
                    //nothing
                    break;
            }
        }
    }

    public interface OnClickListener {
        public void setSelectedPrice(int price);
    }
}
