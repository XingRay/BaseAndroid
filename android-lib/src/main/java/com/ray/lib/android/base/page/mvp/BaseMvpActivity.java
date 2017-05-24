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
    protected BasePresenter mPresenter;

    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onDestroy();
        }

        mViewObservers.clear();
    }

    @Override
    public void subscribe(ViewObserver viewObserver) {
        mViewObservers.add(viewObserver);
    }

    @Override
    public void unsubscribe(ViewObserver viewObserver) {
        mViewObservers.remove(viewObserver);
    }
}
