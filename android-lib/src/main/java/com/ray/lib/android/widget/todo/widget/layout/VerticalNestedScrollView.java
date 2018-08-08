package com.ray.lib.android.widget.todo.widget.layout;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * @author      : leixing
 * @date        : 2017-04-05
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class VerticalNestedScrollView extends NestedScrollView {

    private int mTouchSlop;
    private int downY;

    public VerticalNestedScrollView(Context context) {
        this(context, null);
    }

    public VerticalNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VerticalNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
