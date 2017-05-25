package com.ray.baseandroid.mvp;

import com.ray.lib.android.base.page.mvp.BasePresenter;

/**
 * Author      : leixing
 * Date        : 2017-05-25
 * Email       : leixing@hecom.cn
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
        super.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
