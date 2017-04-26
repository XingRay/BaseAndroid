package com.ray.baseandroid.base.observable;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.ray.baseandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class ObservableFragment extends BaseFragment implements ObservableView {
    private List<ViewObserver> mViewObservers;

    {
        mViewObservers = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onCreate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (ViewObserver viewObserver : mViewObservers) {
            if (viewObserver == null) {
                continue;
            }
            viewObserver.onPause();
        }
    }

    @Override
    public void onDestroy() {
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
