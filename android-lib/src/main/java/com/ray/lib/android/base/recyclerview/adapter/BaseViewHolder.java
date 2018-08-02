package com.ray.lib.android.base.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/2 14:37
 * <p>
 * description : xxx
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected void onRefreshItemView(List<Object> payloads) {

    }

    protected abstract void onBindItemView(T t, int position);
}
