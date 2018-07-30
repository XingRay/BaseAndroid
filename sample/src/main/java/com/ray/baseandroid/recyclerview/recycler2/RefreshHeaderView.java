package com.ray.baseandroid.recyclerview.recycler2;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.ray.baseandroid.R;

/**
 * @author      : leixing
 * @date        : 2017-06-26
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RefreshHeaderView extends FrameLayout implements SwipeLoadMoreTrigger, SwipeTrigger {
    public RefreshHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public RefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.layout_recyclerview_refresh_header, this);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {

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
