package com.shangyi.business.net;

import com.shangyi.business.bean.BaseResponse;
import com.shangyi.business.bean.LoginBean;
import com.shangyi.business.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * data: 2020/3/26 9:37
 * auther: Dz
 * function:
 */
public interface APIServer {


    public static final String NETWORK_SMS_CODE = Constom.BSAE_URL + "/?service=User.Smscode";

    /**
     * 登录
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(Constom.LOGIN_URL)
    Call<LoginBean> Login(@FieldMap Map<String,String> map);

    //注册的网络请求
    //http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST(Constom.REGISTER_URL)
    Observable<RegisterBean> getRegister(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(Constom.CODE_URL)
    Observable<BaseResponse> getCode(@Field("data") String map);
}
