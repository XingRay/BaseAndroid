package com.ray.lib.android.base.recyclerview.adapter;

import android.view.View;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/2 14:26
 * <p>
 * description : xxx
 */
public abstract class BaseViewHolderFactory<VH> {
    public abstract VH createViewHolder(View itemView, int viewType);
}
