package com.ray.baseandroid.base.observable;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface ViewObserver {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
