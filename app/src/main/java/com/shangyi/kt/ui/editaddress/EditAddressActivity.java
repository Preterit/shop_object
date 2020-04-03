package com.shangyi.kt.ui.editaddress;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.shangyi.business.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EditAddressActivity extends AppCompatActivity implements View.OnClickListener {
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
    private TextView mAddressRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        // DeviceUtils.initData(this);
        mAddressRight = findViewById(R.id.img_address_right);
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
        }

        mAddressRight.setOnClickListener(this);

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
        JDCityPicker cityPicker = new JDCityPicker();
        JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

        jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
        cityPicker.init(this);
        cityPicker.setConfig(jdCityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                tvAdress.setText("城市选择结果：\n" + province.getName() + "(" + province.getId() + ")\n"
                        + city.getName() + "(" + city.getId() + ")\n"
                        + district.getName() + "(" + district.getId() + ")");
            }

            @Override
            public void onCancel() {
            }
        });
        cityPicker.showCityPicker();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_address_right:
                //省市区选择
                showPickerView();
                break;
        }

    }


}
