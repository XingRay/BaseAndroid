package com.ray.baseandroid.commonlistview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @@author      : leixing
 * @@date        : 2017-04-06
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : RecyclerView的ViewHolder
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T findView(int viewId) {
        T view = (T) mViews.get(viewId);
        if (view == null) {
            view = (T) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return view;
    }

    public ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = findView(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setImageRes(int viewId, int imageResId) {
        ImageView imageView = findView(viewId);
        imageView.setImageResource(imageResId);
        return this;
    }

    public ViewHolder setVisibility(int viewId, int visibility) {
        View view = findView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public ViewHolder setOnIntemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnIntemLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
        return this;
    }
}
