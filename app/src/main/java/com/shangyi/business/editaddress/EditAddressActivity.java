package com.shangyi.business.editaddress;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shangyi.business.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditAddressActivity extends AppCompatActivity {
    @BindView(R.id.et_name)
    EditText etName;//收货人
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_detail)
    EditText etDetail;
    @BindView(R.id.checkbox_default)
    CheckBox ckDefault;//s是否默认
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_address)
    TextView tvAdress;
    @BindView(R.id.tv_del)
    TextView tvDel;
    private Dialog del_search;

    //private ArrayList<AddressThreeEntry> options1Items = new ArrayList<>(); //省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区

    private int flag, uaid, isDefault;//0为添加地址 1为修改地址
    private String province, city, area, info, phone, name;//省市区  uaid 地址id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
       // DeviceUtils.initData(this);
        flag = getIntent().getIntExtra("flag", 0);
        if (flag == 1) {
            tvTitle.setText("修改地址");
            tvDel.setVisibility(View.VISIBLE);
            uaid = getIntent().getIntExtra("uaid", 0);
            province = getIntent().getStringExtra("province");
            city = getIntent().getStringExtra("city");
            area = getIntent().getStringExtra("area");
            info = getIntent().getStringExtra("info");
            phone = getIntent().getStringExtra("phone");
            name = getIntent().getStringExtra("name");
            isDefault = getIntent().getIntExtra("isDefault", 2);
            ckDefault.setChecked(isDefault == 1);
            etName.setText(name + "");
            etPhone.setText(phone + "");
            tvAdress.setText("" + province + " " + city + " " + area);
            etDetail.setText("" + info);

        } else {
            tvTitle.setText("添加新地址");
            tvDel.setVisibility(View.GONE);
        }
        initJsonData();
    }

    @OnClick({R.id.img_title_back, R.id.tv_save, R.id.tv_address, R.id.tv_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_title_back:
                //返回键
                //finish();
                break;
            case R.id.tv_save:
                //保存
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String detail = etDetail.getText().toString();
                //1默认 2非默认
                int isDefault = ckDefault.isChecked() ? 1 : 2;
                if (check(name, phone, detail)) {
                    if (flag == 1) {
                        //修改地址
                        //mPresenter.updateAddressInfo(province, city, area, detail, name, phone, isDefault);

                    } else {
                        //新增地址
                       // mPresenter.addAddressInfo(province, city, area, detail, name, phone, isDefault);
                    }
                }


                break;
            case R.id.tv_address:
                //省市区选择
                showPickerView();
                break;
            case R.id.tv_del:
                //showDelDialog();

                break;

        }
    }

    /**
     * 校验输入
     */
    private boolean check(String name, String phone, String detail) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this,"请输入收货人",Toast.LENGTH_SHORT);
            return false;
        } else if (TextUtils.isEmpty(phone)) {
            //showToast("请输入正确的手机号");
            Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT);
            return false;
        } else if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city) || TextUtils.isEmpty(area)) {
            //showToast("请选择省市区");
            Toast.makeText(this,"请选择省市区",Toast.LENGTH_SHORT);
            return false;
        } else if (TextUtils.isEmpty(detail)) {
            //showToast("请输入详细的收货地址");
            Toast.makeText(this,"请输入详细的收货地址",Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    private void showPickerView() {// 弹出选择器（省市区三级联动）
        /*OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                province = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(options2);
                area = options3Items.get(options1).get(options2).get(options3);
                tvAdress.setText(province + "  "
                        + city + "  "
                        + area);

            }
        })
                .setTitleText("城市选择")
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleBgColor(0xFFFFDD00)//标题背景颜色 Night mode
//                .setLabels("省", "市", "区")//设置选择的三级单位
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setDividerColor(Color.YELLOW)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        *//*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*//*
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();*/
    }


    private void initJsonData() {//解析数据 （省市区三级联动）
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //String JsonData = new GetJsonDataUtil().getJson(this, "cityData.json");//获取assets目录下的json文件数据

        ////ArrayList<AddressThreeEntry> jsonBean = parseData(JsonData);//用Gson 转成实体
        ////Log.e("------", jsonBean.toString());


        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
       // options1Items = jsonBean;

       /* for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三级）

            for (int j = 0; j < jsonBean.get(i).getRegionEntitys().size(); j++) {
                CityList.add(jsonBean.get(i).getRegionEntitys().get(j).getName());
                ArrayList<String> list = new ArrayList<>();
                for (int k = 0; k < jsonBean.get(i).getRegionEntitys().get(j).getRegionEntitys().size(); k++) {

                    list.add(jsonBean.get(i).getRegionEntitys().get(j).getRegionEntitys().get(k).getRegion());

                }
                Province_AreaList.add(list);
            }
*/

            /**
             * 添加城市数据
             */
            //options2Items.add(CityList);

            /**
             * 添加地区数据
             */
           // options3Items.add(Province_AreaList);
        //}
    }

    /*public ArrayList<AddressThreeEntry> parseData(String result) {//Gson 解析
        ArrayList<AddressThreeEntry> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                AddressThreeEntry entity = gson.fromJson(data.optJSONObject(i).toString(), AddressThreeEntry.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }*/

   /* @Override
    public void showResult(Results results) {
        if (results.isSuccess()) {
            ToastUtils.setToast(results.getMsg());
            finish();
        } else ToastUtils.setToast(results.getMsg());

    }*/

    /*public void showDelDialog() {
        if (del_search != null && del_search.isShowing())
            del_search.dismiss();

        del_search = new SelectDialog(this, R.style.dialog);

        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm_del, null);
        del_search.setContentView(contentView);
        AutoUtils.auto(contentView);
        contentView.findViewById(R.id.dialog_confirm_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认
                mPresenter.delAddressInfo("" + uaid);

            }
        });
        contentView.findViewById(R.id.dialog_confirm_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                del_search.dismiss();
            }
        });


        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        del_search.setContentView(contentView, layoutParams);
        del_search.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        del_search.show();
    }*/

}
