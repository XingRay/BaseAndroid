package com.ray.lib.android.base.page;

/**
 * @@author      : leixing
 * @@date        : 2017-05-31
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface LifeCycleProvider {
    void addLifeCycleObserver(LifeCycleObserver observer);
    void removeLifeCycleObserver(LifeCycleObserver observer);
}
