package com.ray.lib.android.base.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : leixing
 * @date : 2017-04-20
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class BaseFragment extends Fragment implements LifeCycleProvider {

    @SuppressWarnings("FieldCanBeLocal")
    private View mRootView;

    private List<LifeCycleObserver> mLifeCycleObservers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onCreate();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initView(inflater, container);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onStart();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onPause();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onStop();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mLifeCycleObservers != null) {
            for (LifeCycleObserver observer : mLifeCycleObservers) {
                observer.onDestroy();
            }
        }
    }

    protected abstract void initVariables();

    protected abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void loadData();

    @Override
    public void addLifeCycleObserver(LifeCycleObserver observer) {
        if (mLifeCycleObservers == null) {
            mLifeCycleObservers = new CopyOnWriteArrayList<>();
        }
        mLifeCycleObservers.add(observer);
    }

    @Override
    public void removeLifeCycleObserver(LifeCycleObserver observer) {
        if (mLifeCycleObservers == null) {
            return;
        }
        mLifeCycleObservers.remove(observer);
    }
}
