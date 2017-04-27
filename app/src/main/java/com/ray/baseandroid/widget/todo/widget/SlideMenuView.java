package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

public class SlideMenuView extends ViewGroup {
    private int leftMargin;
    private int gap;

    public SlideMenuView(Context context, View backView, View frontView) {
        super(context);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        addView(backView, lp);
        addView(frontView, lp);
    }

    public SlideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        checkChildCount();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            if (i == 1) {
                child.layout(leftMargin, 0, leftMargin + width, height);
            } else {
                child.layout(0, 0, width, height);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");
        }
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only can run at EXACTLY mode!");
        }

        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 使用之前先传入需要留下间隔的宽度。或者调用setGap(final View measureView)。
     *
     * @param gap 间隔宽度
     */
    public void setGap(int gap) {
        this.gap = gap;
        // invalidate();
    }

    /**
     * 使用之前先传入要留下的空间。或者调用setGap(int gap)
     *
     * @param measureView
     * @param resize
     */
    public void setGap(final View measureView, final int resize) {
        ViewTreeObserver vto = measureView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = measureView.getMeasuredWidth();
                gap = width;
                getChildAt(0).setPadding(gap + resize, 0, 0, 0);
                setGap(gap + resize);
            }
        });
    }

    private void checkChildCount() {
        if (2 != getChildCount()) {
            throw new IndexOutOfBoundsException("");
        }
    }

    private void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    private void ani(final int from, final int to, final int left) {
        final View v = getChildAt(1);
        TranslateAnimation translateAnimation = new TranslateAnimation(from, to, 0, 0);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(250);
        translateAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                setLeftMargin(left);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.clearAnimation();
                v.layout(left, 0, left + getWidth(), v.getHeight());
            }
        });
        v.startAnimation(translateAnimation);
    }

    public boolean isMenuOpen() {
        return getChildAt(1).getLeft() != 0;
    }

    public void sliding() {
        if (isMenuOpen()) {
            ani(0, getWidth() - gap, 0);
        } else {
            ani(0, gap - getWidth(), gap - getWidth());
        }
        isBack = !isBack;
    }

    boolean isBack = true;

    public void slidingLeft() {
        if (isBack) {
            ani(0, gap - getWidth(), gap - getWidth());
            isBack = !isBack;
        }
    }

    public void slidingRight() {
        if (!isBack) {
            ani(0, getWidth() - gap, 0);
            isBack = !isBack;
        }
    }
}
