package com.ray.lib.android.base.mvp;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 可观察视图
 */

public interface ObservableView {
    void subscribe(ViewObserver viewObserver);

    void unsubscribe(ViewObserver viewObserver);
}
