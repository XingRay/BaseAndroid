package com.ray.baseandroid.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Author      : leixing
 * Date        : 2017-06-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ExpandableLinearLayout extends LinearLayout {
    private static int COLLAPSED_HEIGHT = 410;
    private static int COLLAPSED_WIDTH = 410;
    private static int EXPAND_SPEED = 10;

    private Status status = Status.COLLAPSED;
    private int mCurrentHeight;
    private int mCurrentWidth;

    public ExpandableLinearLayout(Context context) {
        this(context, null);
    }

    public ExpandableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ExpandableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setExpand(boolean expand) {
        if (expand) {
            doExpand();
        } else {
            doCollapse();
        }
    }

    private void doExpand() {
        if (status == Status.EXPANDED || status == Status.EXPANDING) {
            return;
        }

        status = Status.EXPANDING;
        recordSize();
        requestLayout();
    }

    private void doCollapse() {
        if (status == Status.COLLAPSED || status == Status.COLLAPSING) {
            return;
        }

        status = Status.COLLAPSING;
        recordSize();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        switch (status) {
            case EXPANDED:
                onMeasureExpanded(widthMeasureSpec, heightMeasureSpec);
                break;
            case EXPANDING:
                onMeasureExpanding(widthMeasureSpec, heightMeasureSpec);
                break;
            case COLLAPSED:
                onMeasureCollapsed(widthMeasureSpec, heightMeasureSpec);
                break;
            case COLLAPSING:
                onMeasureCollapsing(widthMeasureSpec, heightMeasureSpec);
                break;
            default:
        }
    }

    private void onMeasureExpanded(int widthMeasureSpec, int heightMeasureSpec) {
        //do nothing
    }

    private void onMeasureCollapsing(int widthMeasureSpec, int heightMeasureSpec) {
        if (getOrientation() == VERTICAL) {
            int targetHeight = Math.min(getMeasuredHeight(), COLLAPSED_HEIGHT);
            if (mCurrentHeight > targetHeight) {
                mCurrentHeight -= Math.min(EXPAND_SPEED, mCurrentHeight - targetHeight);
                setMeasuredDimension(getMeasuredWidth(), mCurrentHeight);
            } else if (mCurrentHeight < targetHeight) {
                mCurrentHeight += Math.min(EXPAND_SPEED, targetHeight - mCurrentHeight);
                setMeasuredDimension(getMeasuredWidth(), mCurrentHeight);
            }

            if (mCurrentHeight == targetHeight) {
                status = Status.COLLAPSED;
            }
        } else {
            int targetWidth = Math.min(getMeasuredWidth(), COLLAPSED_WIDTH);
            if (mCurrentWidth > targetWidth) {
                mCurrentWidth -= Math.min(EXPAND_SPEED, mCurrentWidth - targetWidth);
                setMeasuredDimension(mCurrentWidth, getMeasuredHeight());
            } else if (mCurrentWidth < targetWidth) {
                mCurrentWidth += Math.min(EXPAND_SPEED, targetWidth - mCurrentWidth);
                setMeasuredDimension(mCurrentWidth, getMeasuredHeight());
            }

            if (mCurrentWidth == targetWidth) {
                status = Status.COLLAPSED;
            }
        }

        if (status == Status.COLLAPSING) {
            reLayout();
        }
    }

    private void reLayout() {
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }

    private void onMeasureCollapsed(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;

        if (getOrientation() == VERTICAL) {
            width = getMeasuredWidth();
            height = Math.min(COLLAPSED_HEIGHT, getMeasuredHeight());
        } else {
            width = Math.min(COLLAPSED_WIDTH, getMeasuredWidth());
            height = getMeasuredHeight();
        }

        setMeasuredDimension(width, height);
    }

    private void onMeasureExpanding(int widthMeasureSpec, int heightMeasureSpec) {
        if (getOrientation() == VERTICAL) {
            int targetHeight = getMeasuredHeight();
            if (mCurrentHeight > targetHeight) {
                mCurrentHeight -= Math.min(EXPAND_SPEED, mCurrentHeight - targetHeight);
                setMeasuredDimension(getMeasuredWidth(), mCurrentHeight);
            } else if (mCurrentHeight < targetHeight) {
                mCurrentHeight += Math.min(EXPAND_SPEED, targetHeight - mCurrentHeight);
                setMeasuredDimension(getMeasuredWidth(), mCurrentHeight);
            }

            if (mCurrentHeight == targetHeight) {
                status = Status.EXPANDED;
            }
        } else {
            int targetWidth = getMeasuredWidth();
            if (mCurrentWidth > targetWidth) {
                mCurrentWidth -= Math.min(EXPAND_SPEED, mCurrentWidth - targetWidth);
                setMeasuredDimension(mCurrentWidth, getMeasuredHeight());
            } else if (mCurrentWidth < targetWidth) {
                mCurrentWidth += Math.min(EXPAND_SPEED, targetWidth - mCurrentWidth);
                setMeasuredDimension(mCurrentWidth, getMeasuredHeight());
            }

            if (mCurrentWidth == targetWidth) {
                status = Status.EXPANDED;
            }
        }

        if (status == Status.EXPANDING) {
            reLayout();
        }
    }

    private void recordSize() {
        if (getOrientation() == VERTICAL) {
            mCurrentHeight = getHeight();
        } else {
            mCurrentWidth = getWidth();
        }
    }

    private enum Status {
        EXPANDED, COLLAPSING, COLLAPSED, EXPANDING
    }


}
