package com.ray.lib.android.base.page;

/**
 * @author : leixing
 * @date : 2017-05-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : interface of view life cycle
 */

public interface LifeCycleObserver {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
