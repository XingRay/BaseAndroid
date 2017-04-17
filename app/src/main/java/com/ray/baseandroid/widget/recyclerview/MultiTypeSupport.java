package com.ray.baseandroid.widget.recyclerview;

/**
 * Author      : leixing
 * Date        : 2017-04-06
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : Recyclerview的多布局支持接口
 */

public interface MultiTypeSupport<T> {
    /**
     * 根据item或者position获得Item视图的类型
     *
     * @param item
     * @param position
     * @return
     */
    int getViewType(T item, int position);

    /**
     * 根据视图的类型获取布局的id
     *
     * @param viewType
     * @return
     */
    int getLayoutId(int viewType);
}
