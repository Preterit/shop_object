package com.shangyi.business.base;

import io.reactivex.disposables.Disposable;

/**
 * data: 2020/3/30 9:58
 * auther: Dz
 * function:
 */
public class DefaultDisposable implements Disposable {
    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return true;
    }
}
