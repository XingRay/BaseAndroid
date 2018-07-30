package com.ray.lib.android.base.page.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ray.lib.android.base.page.BaseFragment;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : MVP架构Fragment基类
 */

public abstract class BaseMvpFragment extends BaseFragment {
    protected BasePresenter<? extends BaseFragment> mPresenter;

    protected abstract BasePresenter<? extends BaseFragment> newPresenter();

    {
        mPresenter = newPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter.onDestroyView();
        mPresenter.unbindView();
        mPresenter = null;
    }
}
