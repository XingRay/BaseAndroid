package com.ray.lib.android.base.recyclerview.adapter;

import java.util.List; /**
 * @author : leixing
 * email : leixing@baidu.com
 * @date : 2018/8/2 14:26
 * <p>
 * description : xxx
 */
public abstract class BaseMultiTypeSupport {
    public abstract <T> int getItemViewType(List<T> items, int position);

    public abstract int getLayoutId(int viewType);
}
