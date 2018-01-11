package com.ray.switch_layout;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Author      : leixing
 * Date        : 2017-11-27
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

class SwitchViewHolder {
    private SparseArray<List<View>> mViewLists;
    private final LayoutInflater mInflater;
    private int mCurrentLayerId;

    SwitchViewHolder(Context context) {
        mInflater = LayoutInflater.from(context);
        mViewLists = new SparseArray<>();
    }

    void removeLayer(int layerId) {
        List<View> views = mViewLists.get(layerId);
        if (views == null) {
            return;
        }
        views.clear();
        mViewLists.remove(layerId);
    }

    void add(int layerId, View view) {
        if (containsView(layerId, view)) {
            return;
        }
        List<View> views = mViewLists.get(layerId);
        if (views == null) {
            views = new ArrayList<>();
            mViewLists.put(layerId, views);
        }

        ViewParent parent = view.getParent();
        if (parent != null) {
            throw new IllegalArgumentException("this view has parent");

        }

        views.add(view);
        view.setVisibility((mCurrentLayerId == layerId) ? VISIBLE : GONE);
    }

    private boolean containsView(int layerId, View view) {
        List<View> views = mViewLists.get(layerId);
        return views != null && views.contains(view);
    }

    void setCurrentLayerId(int layerId) {
        if (mCurrentLayerId == layerId) {
            return;
        }
        mCurrentLayerId = layerId;

        update();
    }

    private void update() {
        if (mViewLists == null) {
            return;
        }

        for (int i = 0, size = mViewLists.size(); i < size; i++) {
            int key = mViewLists.keyAt(i);
            List<View> views = mViewLists.get(key);
            if (views == null) {
                continue;
            }

            int visibility = key == mCurrentLayerId ? VISIBLE : GONE;
            for (View view : views) {
                view.setVisibility(visibility);
            }
        }
    }
}
