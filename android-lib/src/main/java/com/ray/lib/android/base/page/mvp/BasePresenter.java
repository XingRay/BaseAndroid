package com.ray.lib.android.base.page.mvp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : MVP架构的Presenter的基类，使用JDK的动态代理，代理视图对象的方法调用
 */

public abstract class BasePresenter<VIEW extends ObservableView> implements ViewObserver {

    private VIEW proxyView;
    private boolean destroyed = false;
    private boolean isViewSet = false;
    private ProxyViewHandler proxyViewHandler;

    private Activity activity;
    private Handler mUIHandler;

    public BasePresenter(VIEW view) {
        setView(view);
        mUIHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 供子类继承是否在页面关闭时清除VIEW引用，
     * 如果这种情况下,getActivity()在页面关闭后会返回null
     * 如果页面关闭后，presenter继续执行长任务，会造成页面内存泄露
     *
     * @return 是否要一直持有activity的引用
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
     * @param command 命令
     */
    protected void runOnUiThread(Runnable command) {
        runOnUiThread(command, true);
    }

    /**
     * 在主线程运行命令
     *
     * @param command              在主线程执行的命令
     * @param justRunWhenViewValid 是否仅在视图有效时运行
     *                             为{@code true}时，当视图销毁后（activity、fragment调用onDestroy），命令将被丢弃
     *                             为{@code false}时，不论视图是否销毁，都将在UI线程运行命令
     */
    protected void runOnUiThread(Runnable command, boolean justRunWhenViewValid) {
        if (justRunWhenViewValid && !isViewValid()) return;
        mUIHandler.post(command);
    }

    /**
     * 判断VIEW是否有效
     *
     * @return 视图是否有效
     */
    private boolean isViewValid() {
        return isViewSet && !destroyed && (proxyViewHandler != null) && proxyViewHandler.isViewExists();
    }

    /**
     * 获取视图对象
     *
     * @return 代理的视图对象
     */
    public VIEW getView() {
        return this.proxyView;
    }

    /**
     * 设置被代理的视图对象
     * 注意：当页面关闭时会自动清除该VIEW的引用,
     * 如果没有设置  {@link #keepActivityAlways()} 为true, 会造成getActivity()==null
     *
     * @param targetView 被代理的视图对象
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

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        destroyed = true;
        if (!keepActivityAlways()) {
            activity = null;
        }
    }

    /**
     * 代理视图对象的方法调用处理器
     */
    private class ProxyViewHandler implements InvocationHandler {
        final WeakReference<VIEW> target;

        ProxyViewHandler(VIEW target) {
            this.target = new WeakReference<>(target);
        }

        private boolean isViewExists() {
            return target.get() != null;
        }

        private VIEW getView() {
            return target.get();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isViewValid()) {
                VIEW targetView = target.get();
                if (targetView != null) {
                    return method.invoke(targetView, args);
                }
            }
            return null;
        }
    }
}
