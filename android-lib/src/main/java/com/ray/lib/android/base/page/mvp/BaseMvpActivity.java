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

public abstract class BaseMvpActivity extends BaseActivity {
    protected BasePresenter<?> mPresenter;

    protected abstract BasePresenter<?> newPresenter();

    {
        mPresenter = newPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter.onDestroyView();
        mPresenter.unbindView();
        mPresenter = null;
    }
}
