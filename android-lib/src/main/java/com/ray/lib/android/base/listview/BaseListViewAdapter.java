package com.ray.lib.android.base.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ray.collection.CollectionUtil;

import java.util.List;

/**
 * use for listview
 * ListView适配器的基类
 *
 * @param <Bean>
 * @param <Holder>
 */
public abstract class BaseListViewAdapter<Bean, Holder extends BaseHolder<Bean>> extends BaseAdapter {
    protected final Context context;
    protected final List<Bean> list;

    public BaseListViewAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }

        return list.size();
    }

    @Override
    public Bean getItem(int position) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = createHolder(context);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return holder.getContentView();
    }

    protected abstract Holder createHolder(Context context);
}
