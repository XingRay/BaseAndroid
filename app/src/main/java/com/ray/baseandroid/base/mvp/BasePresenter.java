package com.ray.baseandroid.base.mvp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


import com.ray.baseandroid.base.observable.ObservableView;
import com.ray.baseandroid.base.observable.ViewObserver;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 安全的 Presenter 基类，如果页面被銷毀，则getView()返回的VIEW 方法调用不执行
 * Created by laotian on 16-9-17.
 */
public abstract class BasePresenter<VIEW extends ObservableView> implements ViewObserver {

    private VIEW proxyView;
    private boolean destroyed = false;
    private boolean isViewSet = false;
    private ProxyViewHandler proxyViewHandler;

    private Activity activity;

    public BasePresenter(VIEW view) {
        setView(view);
    }

    /**
     * 供子类继承是否在页面关闭时清除VIEW引用，
     * 如果这种情况下,getActivity()在页面关闭后会返回null
     * 如果页面关闭后，presenter继续执行长任务，会造成页面内存泄露
     *
     * @return
     */
    protected boolean keepActivityAlways() {
        return false;
    }

    private Activity checkActivity(VIEW target) {
        Activity activity = null;
        if (target != null) {
            if (target instanceof Activity) {
                activity = (Activity) target;
            } else if (target instanceof Fragment) {
                activity = ((Fragment) target).getActivity();
            } else if (target instanceof android.app.Fragment) {
                activity = ((android.app.Fragment) target).getActivity();
            }
        }
        return activity;
    }

    public Activity getActivity() {
        if (activity == null && isViewSet) {
            VIEW target = proxyViewHandler.getView();
            return checkActivity(target);
        }
        return activity;
    }

    /**
     * 仅当VIEW有效时在主线程运行命令
     *
     * @param command
     */
    protected void runOnUiThread(Runnable command) {
        runOnUiThread(command, true);
    }

    /**
     * 在主线程运行命令
     */
    protected void runOnUiThread(Runnable command, boolean justRunWhenViewValid) {
        if (justRunWhenViewValid && !isViewValid()) return;
        new Handler(Looper.getMainLooper()).post(command);
    }

    /**
     * 设置VIEW,
     * 注意：当页面关闭时会自动清除该VIEW的引用,
     * 如果没有设置  {@link #keepActivityAlways()} 为true, 会造成getActivity()==null
     *
     * @param targetView
     */
    @SuppressWarnings("unchecked")
    private void setView(@NonNull VIEW targetView) {
        isViewSet = true;
        if (keepActivityAlways()) {
            activity = checkActivity(targetView);
        }
        proxyViewHandler = new ProxyViewHandler(targetView);
        proxyView = (VIEW) Proxy.newProxyInstance(this.getClass().getClassLoader(), targetView.getClass().getInterfaces(), proxyViewHandler);

        targetView.subscribe(this);
    }

    /**
     *
     */
    private class ProxyViewHandler implements InvocationHandler {
        final WeakReference<VIEW> target;

        private boolean isViewExists() {
            return target != null && target.get() != null;
        }

        private VIEW getView() {
            return target.get();
        }


        public ProxyViewHandler(VIEW target) {
            this.target = new WeakReference<VIEW>(target);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isViewValid()) {
                VIEW targetView = target.get();
                if (targetView != null) {
                    return method.invoke(targetView, args);
                }
            }

            System.out.println("ignore method call:" + method.getName());
            return null;
        }
    }

    /**
     * 判断VIEW是否有效，尽量不要重写此方法
     *
     * @return
     */
    public boolean isViewValid() {
        return isViewSet && !destroyed && (proxyViewHandler != null) && proxyViewHandler.isViewExists();
    }

    public VIEW getView() {
        System.out.println(this.proxyView);
        return this.proxyView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        destroyed = true;
        if (!keepActivityAlways()) {
            activity = null;
        }
    }
}
