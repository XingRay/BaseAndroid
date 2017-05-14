package com.ray.lib.android.base.listview;

import android.content.Context;
import android.view.View;

/**
 * use for holder
 * Holder的基类
 */
public abstract class BaseHolder<Bean> {
    protected final Context context;
    protected View contentView;

    public BaseHolder(Context context) {
        this.context = context;
        contentView = View.inflate(context, getContentViewLayoutResId(), null);
        initView();
        contentView.setTag(this);
    }

    public View getContentView() {
        return contentView;
    }

    public View getChildView(int id) {
        if (contentView == null) {
            return null;
        }

        return contentView.findViewById(id);
    }

    public abstract int getContentViewLayoutResId();

    public abstract void bindData(Bean data);

    public abstract void initView();
}
