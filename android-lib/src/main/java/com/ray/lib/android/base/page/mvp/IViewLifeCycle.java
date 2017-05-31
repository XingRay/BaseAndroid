package com.ray.lib.android.base.page.mvp;

/**
 * Author      : leixing
 * Date        : 2017-05-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : interface of view life cycle
 */

public interface IViewLifeCycle {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
