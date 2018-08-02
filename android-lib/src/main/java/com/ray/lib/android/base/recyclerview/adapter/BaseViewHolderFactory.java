package com.ray.lib.android.base.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View; /**
 * @author : leixing
 * email : leixing@baidu.com
 * @date : 2018/8/2 14:26
 * <p>
 * description : xxx
 */
public abstract class BaseViewHolderFactory {
    public abstract  <VH extends RecyclerView.ViewHolder> VH createViewHolder(View itemView, int viewType);
}
