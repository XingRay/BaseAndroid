package com.ray.lib.android.base.recyclerview.adapter;

import android.view.ViewGroup;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/2 14:19
 * <p>
 * description : xxx
 */
public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup parent, int position, T t);
}
