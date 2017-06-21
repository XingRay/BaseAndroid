package com.ray.baseandroid.customview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ray.baseandroid.R;


/**
 * Author      : leixing
 * Date        : 2017-06-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : CollapsibleLinearLayout
 */

public class CollapsibleLinearLayout extends LinearLayout {
    public static final int DEFAULT_ANIMATION_DURATION = 300;
    public static final int DEFAULT_COLLAPSED_HEIGHT = 400;
    public static final int DEFAULT_COLLAPSED_WIDTH = 400;

    private int mAnimationDuration = DEFAULT_ANIMATION_DURATION;
    private int mCollapsedHeight = DEFAULT_COLLAPSED_HEIGHT;
    private int mCollapsedWidth = DEFAULT_COLLAPSED_WIDTH;

    private int mCurrentHeight;
    private int mCurrentWidth;
    private int mMeasuredHeight;
    private int mMeasuredWidth;

    private Status mStatus = Status.COLLAPSED;
    private ValueAnimator mExpandAnimator;
    private ValueAnimator mCollapseAnimator;
    private ActionListener mExpandListener;
    private ActionListener mCollapseListener;

    public CollapsibleLinearLayout(Context context) {
        this(context, null);
    }

    public CollapsibleLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CollapsibleLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        applyAttributes(context, attrs);
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CollapsibleLinearLayout);
        if (typedArray == null) {
            return;
        }

        mAnimationDuration = typedArray.getInt(R.styleable.CollapsibleLinearLayout_animation_duration, DEFAULT_ANIMATION_DURATION);
        mCollapsedWidth = typedArray.getInt(R.styleable.CollapsibleLinearLayout_collapsed_width, DEFAULT_COLLAPSED_WIDTH);
        mCollapsedHeight = typedArray.getInt(R.styleable.CollapsibleLinearLayout_collapsed_height, DEFAULT_COLLAPSED_HEIGHT);

        boolean isCollapsed = typedArray.getBoolean(R.styleable.CollapsibleLinearLayout_is_collapsed, true);
        mStatus = isCollapsed ? Status.COLLAPSED : Status.EXPANDED;

        typedArray.recycle();
    }

    public void setExpandListener(ActionListener listener) {
        mExpandListener = listener;
    }

    public void setCollapseListener(ActionListener listener) {
        mCollapseListener = listener;
    }

    public void setExpand(boolean expand) {
        if (expand) {
            doExpand();
        } else {
            doCollapse();
        }
    }

    private void doExpand() {
        if (mStatus != Status.COLLAPSED) {
            return;
        }

        int start = 0;
        int end = 0;
        if (getOrientation() == VERTICAL) {
            start = getHeight();
            end = mMeasuredHeight;
        } else {
            start = getWidth();
            end = mMeasuredWidth;
        }

        if (start == end) {
            mStatus = Status.EXPANDED;
            return;
        }

        if (mExpandAnimator == null) {
            mExpandAnimator = ValueAnimator.ofInt(start, end).setDuration(mAnimationDuration);
            mExpandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    if (getOrientation() == VERTICAL) {
                        mCurrentHeight = value;
                    } else {
                        mCurrentWidth = value;
                    }
                    if (mExpandListener != null) {
                        mExpandListener.onProgress(value);
                    }
                    requestLayout();
                }
            });
            mExpandAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mStatus = Status.EXPANDING;
                    if (mExpandListener != null) {
                        mExpandListener.onStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = Status.EXPANDED;
                    if (mExpandListener != null) {
                        mExpandListener.onComplete();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        mExpandAnimator.start();
    }

    private void doCollapse() {
        if (mStatus != Status.EXPANDED) {
            return;
        }

        int start = 0;
        int end = 0;
        if (getOrientation() == VERTICAL) {
            start = getHeight();
            end = Math.min(mMeasuredHeight, mCollapsedHeight);
        } else {
            start = getWidth();
            end = Math.min(mMeasuredWidth, mCollapsedWidth);
        }

        if (start == end) {
            mStatus = Status.COLLAPSED;
            return;
        }

        if (mCollapseAnimator == null) {
            mCollapseAnimator = ValueAnimator.ofInt(start, end).setDuration(mAnimationDuration);
            mCollapseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    if (getOrientation() == VERTICAL) {
                        mCurrentHeight = value;
                    } else {
                        mCurrentWidth = value;
                    }
                    if (mCollapseListener != null) {
                        mCollapseListener.onProgress(value);
                    }
                    requestLayout();
                }
            });
            mCollapseAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mStatus = Status.COLLAPSING;
                    if (mCollapseListener != null) {
                        mCollapseListener.onStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = Status.COLLAPSED;
                    if (mCollapseListener != null) {
                        mCollapseListener.onComplete();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        mCollapseAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();

        switch (mStatus) {
            case EXPANDED:
                onMeasureExpanded();
                break;
            case COLLAPSED:
                onMeasureCollapsed();
                break;
            case EXPANDING:
            case COLLAPSING:
                onAnimationRunning();
                break;
            default:
        }
    }

    private void onMeasureExpanded() {
        //do nothing
    }

    private void onAnimationRunning() {
        if (getOrientation() == VERTICAL) {
            setMeasuredDimension(getMeasuredWidth(), mCurrentHeight);
        } else {
            setMeasuredDimension(mCurrentWidth, getMeasuredHeight());
        }
    }

    private void onMeasureCollapsed() {
        int width = 0;
        int height = 0;

        if (getOrientation() == VERTICAL) {
            width = getMeasuredWidth();
            height = Math.min(mCollapsedHeight, getMeasuredHeight());
        } else {
            width = Math.min(mCollapsedWidth, getMeasuredWidth());
            height = getMeasuredHeight();
        }

        setMeasuredDimension(width, height);
    }

    private enum Status {
        EXPANDED, COLLAPSING, COLLAPSED, EXPANDING
    }

    public interface ActionListener {
        void onStart();

        void onProgress(int value);

        void onComplete();
    }
}
