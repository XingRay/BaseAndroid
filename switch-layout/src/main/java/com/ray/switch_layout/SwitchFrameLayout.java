package com.ray.switch_layout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Author      : leixing
 * Date        : 2017-07-09
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SwitchFrameLayout extends FrameLayout {

    private LayoutInflater mInflater;
    private SwitchViewHolder mSwitchViewHolder;

    public SwitchFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwitchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        mSwitchViewHolder = new SwitchViewHolder(context);
    }

    public void add(int layerId, @LayoutRes int layoutId) {
        View view = mInflater.inflate(layoutId, this, false);
        add(layerId, view);
    }

    public void add(int layerId, View view) {
        addView(view);
        mSwitchViewHolder.add(layerId, view);
    }

    public void setCurrentLayerId(int layerId) {
        mSwitchViewHolder.setCurrentLayerId(layerId);
    }
}
