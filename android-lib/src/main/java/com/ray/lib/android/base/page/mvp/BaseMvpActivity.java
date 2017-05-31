package com.ray.lib.android.base.page.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ray.lib.android.base.page.BaseActivity;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : MVP架构Activity基类
 */

public abstract class BaseMvpActivity<P> extends BaseActivity implements IViewDelegatable {
    protected P mPresenter;
    private IViewLifeCycle mViewDelegate;

    protected abstract P newPresenter();

    {
        mPresenter = newPresenter();
    }

    @Override
    public void setViewDelegate(IViewLifeCycle viewLifeCycle) {
        mViewDelegate = viewLifeCycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDelegate.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewDelegate.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewDelegate.onDestroy();
        mViewDelegate = null;
        mPresenter = null;
    }
}
