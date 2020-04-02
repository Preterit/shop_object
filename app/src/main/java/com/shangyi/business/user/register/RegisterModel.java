package com.shangyi.business.user.register;

import com.shangyi.business.base.DefaultDisposable;
import com.shangyi.business.bean.BaseResponse;
import com.shangyi.business.bean.RegisterBean;
import com.shangyi.business.net.APIServer;
import com.shangyi.business.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * data: 2020/3/30 9:55
 * auther: Dz
 * function:
 */
public class RegisterModel {
    private Disposable mDisposable = new DefaultDisposable();


    public boolean  isDisposable(){
        return mDisposable.isDisposed();
    }

    public void disposable(){
        mDisposable.dispose();
    }
}
