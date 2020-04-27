package com.shangyi.kt.fragment.car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.kt.fragment.car.entity.CartInfo;
import com.shangyi.kt.fragment.car.entity.CommitOrderBean;
import com.shangyi.kt.fragment.car.entity.GoodsInfoBean;
import com.study.glidemodel.GlideImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangqie on 2016/11/26.
 */

public class CartExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CartInfo> list;
    private OnAdapterClickListener mListener;

    public CartExpandAdapter(Context context, List<CartInfo> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public CartInfo.ItemsBean getChild(int arg0, int arg1) {
        return list.get(arg0).child.get(arg1);
    }

    @Override
    public long getChildId(int arg0, int arg1) {
        return 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int position,
                             boolean arg2, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_list_child_item, null);
            childViewHolder = new ChildViewHolder(convertView, groupPosition, position);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        CartInfo.ItemsBean item = getChild(groupPosition, position);
        childViewHolder.checkBox.setEnabled(item.ischeck);
        childViewHolder.glideImageView.loadImage(item.goods_img.get(0).url, R.color.placeholder_color);
        childViewHolder.tvGoodsName.setText(item.name);
        childViewHolder.tvGoodsPrice.setText(String.valueOf(item.sale_price));
        childViewHolder.tvNumber.setText(String.valueOf(item.number));
        childViewHolder.tvFanPrice.setText("下单返￥" + item.sale_price);

        childViewHolder.checkboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childCheckClick(childViewHolder.checkBox.isEnabled(), groupPosition, position);
            }
        });

        return convertView;
    }

    public void refreshData(List<CartInfo> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    class ChildViewHolder implements View.OnClickListener {
        private int groupPosition;
        private int position;
        private ImageView checkBox;
        private GlideImageView glideImageView;
        private TextView tvGoodsName;
        private TextView tvGoodsPrice;
        private TextView tvFanPrice;
        private TextView tvNumber;
        private LinearLayout fanLayout;
        private LinearLayout checkboxLayout;

        public ChildViewHolder(View view, int groupPosition, int position) {
            this.groupPosition = groupPosition;
            this.position = position;
            checkBox = view.findViewById(R.id.checkbox);
            glideImageView = view.findViewById(R.id.glideImageView);
            tvGoodsName = view.findViewById(R.id.tvGoodsName);
            tvGoodsPrice = view.findViewById(R.id.tvGoodsPrice);
            tvFanPrice = view.findViewById(R.id.tvFanPrice);
            tvNumber = view.findViewById(R.id.tvNumber);
            fanLayout = view.findViewById(R.id.fanLayout);
            checkboxLayout = view.findViewById(R.id.checkboxLayout);

            view.findViewById(R.id.ivCut).setOnClickListener(this);
            view.findViewById(R.id.ivAdd).setOnClickListener(this);
            view.findViewById(R.id.btnDelete).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ivAdd:
                    childAddCutClick(1, groupPosition, position, Integer.valueOf(tvNumber.getText().toString()));
                    break;
                case R.id.ivCut:
                    childAddCutClick(2, groupPosition, position, Integer.valueOf(tvNumber.getText().toString()));
                    break;
                case R.id.btnDelete:
                    if (mListener != null) {
                        int[] cid = {list.get(groupPosition).child.get(position).cid};
                        list.get(groupPosition).child.remove(position);
                        if (list.get(groupPosition).child.size() == 0) {
                            list.remove(groupPosition);
                        }
                        mListener.delectClick(list.size() == 0, cid);
                        notifyDataSetChanged();
                        refreshMoney();
                    }
                    break;
            }
        }
    }


    @Override
    public int getChildrenCount(int arg0) {
        return (list != null && list.size() > 0) ? list.get(arg0).child.size() : 0;
    }

    @Override
    public Object getGroup(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getGroupCount() {
        return (list != null && list.size() > 0) ? list.size() : 0;
    }

    @Override
    public long getGroupId(int arg0) {
        return 0;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_list_group_item, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        CartInfo dataBean = (CartInfo) getGroup(groupPosition);
        groupViewHolder.tvShopName.setText(dataBean.name + "  >");
        groupViewHolder.checkBox.setEnabled(dataBean.ischeck);
        groupViewHolder.checkboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListener.groupCheckBoxClick(groupViewHolder.checkBox.isEnabled(), groupPosition);
                groupCheckClick(groupViewHolder.checkBox.isEnabled(), groupPosition);
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return false;
    }

    class GroupViewHolder {
        ImageView checkBox;
        TextView tvShopName;
        TextView tvLingquan;
        LinearLayout checkboxLayout;

        public GroupViewHolder(View view) {
            tvShopName = view.findViewById(R.id.tvShopName);
            checkBox = view.findViewById(R.id.checkbox);
            tvLingquan = view.findViewById(R.id.tvLingquan);
            checkboxLayout = view.findViewById(R.id.checkboxLayout);
        }
    }

    interface OnAdapterClickListener {

        void childAddCutClick(int type, int groupPosition, int childPosition, int count);

        void delectClick(boolean isEmpty, int[] cid);

        void moneyRefresh(float money);
    }

    public void setAdapterClickListener(OnAdapterClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 点击child checkbox 状态显示
     *
     * @param isCheck
     * @param groupPosition
     * @param position
     */
    private void childCheckClick(boolean isCheck, int groupPosition, int position) {
        CartInfo.ItemsBean childBean = list.get(groupPosition).child.get(position);
        childBean.ischeck = !isCheck;

        CartInfo dataBean = list.get(groupPosition);
        dataBean.ischeck = isListAllTrue(list.get(groupPosition).child);
        notifyDataSetChanged();
        refreshMoney();
    }

    /**
     * 点击child checkbox 状态显示
     *
     * @param isCheck
     * @param groupPosition
     */
    private void groupCheckClick(boolean isCheck, int groupPosition) {
        CartInfo groupData = list.get(groupPosition);
        groupData.ischeck = !isCheck;

        for (CartInfo.ItemsBean item : groupData.child) {
            item.ischeck = !isCheck;
        }
        notifyDataSetChanged();
        refreshMoney();
    }


    private void childAddCutClick(int type, int groupPosition, int childPosition, int count) {
        CartInfo.ItemsBean item = list.get(groupPosition).child.get(childPosition);
        if (item.spec.stock == 0) {
            UIUtils.showToast("该商品暂时无货。。");
            return;
        }
        if (type == 1) {
            if (count >= item.spec.stock) {
                UIUtils.showToast("库存没有那么多");
                return;
            }
            item.number = count + 1;
        } else {
            if (count <= 0) {
                UIUtils.showToast("至少购买一件");
                return;
            }
            item.number = count - 1;
        }
        notifyDataSetChanged();
        refreshMoney();
        if (mListener != null) {
            mListener.childAddCutClick(1, groupPosition, childPosition, item.number);
        }
    }

    /**
     * 选中商品价格显示更新
     */
    public void refreshMoney() {
        float money = 0;
        for (CartInfo cartInfo : list) {
            for (CartInfo.ItemsBean item : cartInfo.child) {
                if (item.ischeck) {
                    money += item.sale_price * item.number;
                }
            }
        }
        if (mListener != null) {
            mListener.moneyRefresh(money);
        }
    }

    /**
     * 判断列表里的数据是否都是选中
     *
     * @param childBeans
     * @return
     */
    public boolean isListAllTrue(List<CartInfo.ItemsBean> childBeans) {
        for (CartInfo.ItemsBean item : childBeans) {
            if (!item.ischeck) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取选中多个要删除的ID
     *
     * @return
     */
    public List<Integer> getSelectId() {
        List result = new ArrayList<Integer>();
        for (CartInfo cartInfo : list) {
            for (CartInfo.ItemsBean itemsBean : cartInfo.child) {
                if (itemsBean.ischeck) {
                    result.add(itemsBean.cid);
                }
            }
        }
        return result;
    }

    /**
     * 全选
     *
     * @param enabled
     */
    public void selectAll(boolean enabled) {
        for (CartInfo cartInfo : list) {
            cartInfo.ischeck = enabled;
            for (CartInfo.ItemsBean itemsBean : cartInfo.child) {
                itemsBean.ischeck = enabled;
            }
        }
        notifyDataSetChanged();
        refreshMoney();
    }

    /**
     * 清除选中状态
     */
    public void clearSelect() {
        for (CartInfo cartInfo : list) {
            cartInfo.ischeck = false;
            for (CartInfo.ItemsBean itemsBean : cartInfo.child) {
                itemsBean.ischeck = false;
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 获取选中的商品进行订单提交
     */
    public ArrayList<CommitOrderBean> getSelectGoods() {
        ArrayList result = new ArrayList<CommitOrderBean>();
        for (CartInfo cartInfo : list) {
            List<GoodsInfoBean> productBean = new ArrayList();
            float fanPrice = 0f;
            float totalPrice = 0f;
            for (CartInfo.ItemsBean itemsBean : cartInfo.child) {
                if (itemsBean.ischeck) {
                    GoodsInfoBean goodsInfoBean = new GoodsInfoBean(
                            itemsBean.goods_id,
                            itemsBean.number,
                            itemsBean.sale_price,
                            itemsBean.name,
                            itemsBean.spec.value,
                            itemsBean.goods_img.get(0).url,
                            itemsBean.spec.id
                    );
                    productBean.add(goodsInfoBean);
                    totalPrice += itemsBean.sale_price * itemsBean.number;
                }
            }
            if (productBean.size() != 0) {
                CommitOrderBean goodsInfo = new CommitOrderBean(cartInfo.id, "https://img.alicdn.com/tps/i4/TB1O6rGx1H2gK0jSZFESuwqMpXa.jpg_400x400q90.jpg", cartInfo.name, productBean, fanPrice, "", totalPrice);
                result.add(goodsInfo);
            }
        }
        return result;
    }
}
