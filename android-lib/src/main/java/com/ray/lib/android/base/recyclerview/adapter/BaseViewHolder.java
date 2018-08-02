package com.ray.lib.android.base.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * @author : leixing
 * email : leixing@baidu.com
 * @date : 2018/8/2 14:37
 * <p>
 * description : xxx
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onRefreshItemView(List<Object> payloads);

    public abstract <T> void onBindItemView(T t, int position);
}
