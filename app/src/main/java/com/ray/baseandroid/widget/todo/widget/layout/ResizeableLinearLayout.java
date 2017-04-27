package com.ray.baseandroid.widget.todo.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by leixing
 * on 2016-11-01.
 * Email : leixing@hecom.cn
 */

public class ResizeableLinearLayout extends LinearLayout {

    private ResizeListener resizeListener;

    public ResizeableLinearLayout(Context context) {
        this(context, null);
    }

    public ResizeableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ResizeableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setResizeListener(ResizeListener listener) {
        this.resizeListener = listener;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (this.resizeListener != null) {
            resizeListener.OnResize(w, h, oldw, oldh);
        }
    }

    public interface ResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }
}
