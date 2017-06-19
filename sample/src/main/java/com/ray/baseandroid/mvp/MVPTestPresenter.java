package com.ray.baseandroid.mvp;

import com.ray.lib.android.base.page.mvp.BasePresenter;
import com.ray.lib.android.manager.ThreadPool;
import com.ray.lib.android.util.TraceUtil;

/**
 * Author      : leixing
 * Date        : 2017-05-25
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MVPTestPresenter extends BasePresenter<MVPTestContract.View> implements MVPTestContract.Presenter {
    public MVPTestPresenter(MVPTestContract.View view) {
        super(view);
    }

    @Override
    protected void onCreate() {
        TraceUtil.log();
    }

    @Override
    protected void onStart() {
        TraceUtil.log();
    }

    @Override
    protected void onResume() {
        TraceUtil.log();
    }

    @Override
    protected void onPause() {
        TraceUtil.log();
    }

    @Override
    protected void onStop() {
        TraceUtil.log();
    }

    @Override
    protected void onDestroy() {
        TraceUtil.log();
    }

    @Override
    public void loadData() {
        TraceUtil.log();
        ThreadPool.getDefault().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String content = "hello mvp";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().setContent(content);
                    }
                });
            }
        });
    }
}
