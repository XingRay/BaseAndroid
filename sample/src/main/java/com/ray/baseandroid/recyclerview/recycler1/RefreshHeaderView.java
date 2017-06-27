package com.ray.baseandroid.recyclerview.recycler1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.aspsine.irecyclerview.RefreshTrigger;
import com.ray.baseandroid.R;

/**
 * Author      : leixing
 * Date        : 2017-06-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RefreshHeaderView extends FrameLayout implements RefreshTrigger {
    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_refresh_header_view, this, true);
    }

    @Override
    public void onStart(boolean b, int i, int i1) {

    }

    @Override
    public void onMove(boolean b, boolean b1, int i) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }
}
