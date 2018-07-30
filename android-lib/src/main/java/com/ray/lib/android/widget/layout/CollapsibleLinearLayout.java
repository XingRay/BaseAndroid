package com.ray.lib.android.widget.layout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ray.lib.android.R;


/**
 * @author      : leixing
 * @date        : 2017-06-20
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : CollapsibleLinearLayout
 */
// TODO: 2017-06-22 add setFraction(float fraction), allow layout can collaspe at any time with given fraction
// TODO: 2017-06-22 add touch support, allow layout collapse by user move finger when touching layout
// TODO: 2017-06-22 add method to get status of this layout
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
    private ActionListener mActionListener;

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

    public void setActionListener(ActionListener listener) {
        mActionListener = listener;
    }

    public void setCollapse(boolean collapse) {
        mStatus = collapse ? Status.COLLAPSED : Status.EXPANDED;
        requestLayout();
    }

    public boolean isCollapsed() {
        return mStatus == Status.COLLAPSED;
    }

    public void expand() {
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
                    float fraction = animation.getAnimatedFraction();

                    if (getOrientation() == VERTICAL) {
                        mCurrentHeight = value;
                    } else {
                        mCurrentWidth = value;
                    }
                    if (mActionListener != null) {
                        mActionListener.onProgress(fraction, value);
                    }
                    requestLayout();
                }
            });
            mExpandAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mStatus = Status.EXPANDING;
                    if (mActionListener != null) {
                        mActionListener.onStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = Status.EXPANDED;
                    if (mActionListener != null) {
                        mActionListener.onComplete(false);
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

    public void collapse() {
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
                    float fraction = animation.getAnimatedFraction();

                    if (getOrientation() == VERTICAL) {
                        mCurrentHeight = value;
                    } else {
                        mCurrentWidth = value;
                    }
                    if (mActionListener != null) {
                        mActionListener.onProgress(fraction, value);
                    }
                    requestLayout();
                }
            });
            mCollapseAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mStatus = Status.COLLAPSING;
                    if (mActionListener != null) {
                        mActionListener.onStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mStatus = Status.COLLAPSED;
                    if (mActionListener != null) {
                        mActionListener.onComplete(true);
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

    /**
     * status of this {@link CollapsibleLinearLayout}
     */
    private enum Status {
        /**
         * expanded, it's height(vertical) or width(horizontal)
         */
        EXPANDED, COLLAPSING, COLLAPSED, EXPANDING
    }

    /**
     * listener for {@link CollapsibleLinearLayout} action,
     * when expand or collapse the layout, methods of {@link ActionListener} will
     * called.
     * <p>
     * Note: if the height or width is less than {@link CollapsibleLinearLayout#mCollapsedHeight} or
     * {@link CollapsibleLinearLayout#mCollapsedWidth},  any method of {@link ActionListener}
     * will <b>NOT<b/> called after invoke {@link CollapsibleLinearLayout#setCollapse(boolean)}
     * and {@link CollapsibleLinearLayout#setActionListener(ActionListener)}
     */
    public interface ActionListener {
        /**
         * called when start to expand or collapse {@link CollapsibleLinearLayout}
         */
        void onStart();

        /**
         * called when expanding or collapsing {@link CollapsibleLinearLayout}
         *
         * @param fraction fraction of animation when expanding or collapsing.
         * @param value    the height or width of {@link CollapsibleLinearLayout} when expanding or collapsing.
         */
        void onProgress(float fraction, int value);

        /**
         * called when finish expanding or collapsing.
         *
         * @param isCollapsed {@code true} - when finish collapsing
         *                    {@code false} - when finish expanding
         */
        void onComplete(boolean isCollapsed);
    }
}
