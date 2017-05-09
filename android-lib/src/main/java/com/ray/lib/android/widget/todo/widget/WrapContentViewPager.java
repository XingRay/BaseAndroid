package com.ray.lib.android.widget.todo.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;


/**
 * 高度自适应的ViewPager
 * Created by kevin.bai on 16/11/25.
 */

public class WrapContentViewPager extends ViewPager {
    private static final String TAG = WrapContentViewPager.class.getSimpleName();
    private int height = 0;
    private int decorHeight = 0;
    private int widthMeasuredSpec;

    private boolean animateHeight;
    private int rightHeight;
    private int leftHeight;
    private int scrollingPosition = -1;

    public WrapContentViewPager(Context context) {
        super(context);
        init();
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addOnPageChangeListener(new SimpleOnPageChangeListener() {

            private int state;

            @Override
            public void onPageSelected(int position) {
                if (state == SCROLL_STATE_IDLE) {
                    height = 0; // measure the selected page in-case it's a change without scrolling
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                this.state = state;
            }
        });
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!(adapter instanceof ObjectAtPositionInterface)) {
            throw new IllegalArgumentException("WrapContentViewPage requires that PagerAdapter will implement ObjectAtPositionInterface");
        }
        height = 0; // so we measure the new content in onMeasure
        super.setAdapter(adapter);
    }

    /**
     * Allows to redraw the view size to wrap the content of the bigger child.
     *
     * @param widthMeasureSpec  with measured
     * @param heightMeasureSpec height measured
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        widthMeasuredSpec = widthMeasureSpec;
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
            if (height == 0) {
                // measure vertical decor (i.e. PagerTitleStrip) based on ViewPager implementation
                decorHeight = 0;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (lp != null && lp.isDecor) {
                        int vgrav = lp.gravity & Gravity.VERTICAL_GRAVITY_MASK;
                        boolean consumeVertical = vgrav == Gravity.TOP || vgrav == Gravity.BOTTOM;
                        if (consumeVertical) {
                            decorHeight += child.getMeasuredHeight();
                        }
                    }
                }

                // make sure that we have an height (not sure if this is necessary because it seems that onPageScrolled is called right after
                int position = getCurrentItem();
                View child = getViewAtPosition(position);
                if (child != null) {
                    height = measureViewHeight(child);
                }

            } else if (!animateHeight) {
                int position = getCurrentItem();
                View child = getViewAtPosition(position);
                if (child != null) {
                    height = measureViewHeight(child);
                }
            }
            int totalHeight = height + decorHeight + getPaddingBottom() + getPaddingTop();
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
        super.onPageScrolled(position, offset, positionOffsetPixels);
        // cache scrolled view heights
        if (scrollingPosition != position) {
            scrollingPosition = position;
            // scrolled position is always the left scrolled page
            View leftView = getViewAtPosition(position);
            View rightView = getViewAtPosition(position + 1);
            if (leftView != null && rightView != null) {
                leftHeight = measureViewHeight(leftView);
                rightHeight = measureViewHeight(rightView);
                animateHeight = true;
            } else {
                animateHeight = false;
            }
        }
        if (animateHeight) {
            int newHeight = (int) (leftHeight * (1 - offset) + rightHeight * (offset));
            if (height != newHeight) {
                height = newHeight;
                requestLayout();
            }
            if (offset == 0) {
                animateHeight = false;
                scrollingPosition = -1;
            }
        }
    }


    private int measureViewHeight(View view) {
        view.measure(getChildMeasureSpec(widthMeasuredSpec, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        return view.getMeasuredHeight();
    }

    protected View getViewAtPosition(int position) {
        if (getAdapter() != null) {
            Object objectAtPosition = ((ObjectAtPositionInterface) getAdapter()).getObjectAtPosition(position);
            if (objectAtPosition != null) {
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child != null && getAdapter().isViewFromObject(child, objectAtPosition)) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
}
