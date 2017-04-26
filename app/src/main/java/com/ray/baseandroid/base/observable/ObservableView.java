package com.ray.baseandroid.base.observable;

/**
 * Author      : leixing
 * Date        : 2017-04-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface ObservableView {
    void subscribe(ViewObserver viewObserver);
    void unsubscribe(ViewObserver viewObserver);
}
