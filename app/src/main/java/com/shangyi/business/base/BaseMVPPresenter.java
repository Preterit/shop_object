package com.shangyi.business.base;

/**
 * data: 2020/3/17 18:28
 * auther: Dz
 * function:
 */
public class BaseMVPPresenter<V> {

    public V view;

    public void detach(){
        if (view != null){
            view = null;
        }
    }

    public void attch(V v){
        this.view = v;
    }
}
