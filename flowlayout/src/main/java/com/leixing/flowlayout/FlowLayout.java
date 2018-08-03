package com.leixing.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.text.TextUtilsCompat;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/7/31 18:06
 * <p>
 * description : xxx
 */
public class FlowLayout extends ViewGroup {
    private static final int LEFT = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;
    private static final int DEFAULT_LINE_SPACING = 0;

    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();
    private List<Integer> mLineWidth = new ArrayList<>();
    private List<View> lineViews = new ArrayList<>();

    private int mGravity;
    private int mLineSpacing = 0;
    private int mViewSpacing = 0;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyAttributes(context, attrs);
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        if (typedArray == null) {
            return;
        }

        mLineSpacing = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_line_spacing, DEFAULT_LINE_SPACING);
        mViewSpacing = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_view_spacing, 0);
        mGravity = typedArray.getInt(R.styleable.FlowLayout_tag_gravity, LEFT);

        int layoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
        if (layoutDirection == LayoutDirection.RTL && mGravity != CENTER) {
            mGravity = mGravity == LEFT ? RIGHT : LEFT;
        }

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // wrap_content
        int contentWidth = 0;
        int contentHeight = 0;

        int usedLineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        int innerWidth = sizeWidth - getPaddingLeft() - getPaddingRight();

        boolean isFirstViewOfLine = true;
        boolean isFirstLine = true;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                if (i == childCount - 1) {
                    contentWidth = Math.max(usedLineWidth, contentWidth);
                    contentHeight += lineHeight;
                }
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + (isFirstViewOfLine ? 0 : mViewSpacing);
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + (isFirstLine ? 0 : mLineSpacing);

            int requireWidth = usedLineWidth + childWidth;

            if (requireWidth <= innerWidth) {
                //不足一行
                isFirstViewOfLine = false;
                usedLineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            } else {
                //超过一行
                isFirstViewOfLine = true;
                isFirstLine = false;
                contentWidth = Math.max(contentWidth, usedLineWidth);
                usedLineWidth = childWidth;
                contentHeight += lineHeight;
                lineHeight = childHeight;
            }

            if (i == childCount - 1) {
                contentWidth = Math.max(usedLineWidth, contentWidth);
                contentHeight += lineHeight;
            }
        }

        // TODO: 2018/7/31 是否要结合上面的情况计算？提前判断
        int measuredWidth = modeWidth == MeasureSpec.EXACTLY ? sizeWidth : contentWidth + getPaddingLeft() + getPaddingRight();
        int measuredHeight = modeHeight == MeasureSpec.EXACTLY ? sizeHeight : contentHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();
        mLineWidth.clear();
        lineViews.clear();

        int usedLineWidth = 0;
        int lineHeight = 0;
        int width = getWidth();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        boolean isFirstViewOfLine = true;
        boolean isFirstLine = true;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth() + (isFirstViewOfLine ? 0 : mViewSpacing);
            int childHeight = child.getMeasuredHeight() + (isFirstLine ? 0 : mLineSpacing);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            if (childWidth + usedLineWidth + lp.leftMargin + lp.rightMargin + mViewSpacing <= width - paddingLeft - paddingRight) {
                //不足一行
                isFirstViewOfLine = false;
                usedLineWidth += childWidth + lp.leftMargin + lp.rightMargin;
                lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
                lineViews.add(child);
            } else {
                //超过一行
                isFirstLine = false;
                isFirstViewOfLine = true;
                mLineHeight.add(lineHeight);
                mLineWidth.add(usedLineWidth);
                mAllViews.add(lineViews);
//                lineWidth = childWidth + lp.leftMargin + lp.rightMargin;
                usedLineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                lineViews = new ArrayList<View>();
//                lineViews.add(child);
            }
        }

        mLineHeight.add(lineHeight);
        mLineWidth.add(usedLineWidth);
        mAllViews.add(lineViews);

        int lineNum = mAllViews.size();

        for (int i = 0; i < lineNum; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            int lineSpacing = i == 0 ? 0 : mLineSpacing;

            // set gravity
            int currentLineWidth = this.mLineWidth.get(i);
            switch (this.mGravity) {
                case LEFT:
                    paddingLeft = getPaddingLeft();
                    break;
                case CENTER:
                    paddingLeft = (width - currentLineWidth) / 2 + getPaddingLeft();
                    break;
                case RIGHT:
                    //  适配了rtl，需要补偿一个padding值
                    paddingLeft = width - (currentLineWidth + getPaddingLeft()) - getPaddingRight();
                    //  适配了rtl，需要把lineViews里面的数组倒序排
                    Collections.reverse(lineViews);
                    break;
                default:
            }

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int viewSpacing = j == 0 ? 0 : mViewSpacing;
                int left = paddingLeft + lp.leftMargin + viewSpacing;
                int top = paddingTop + lp.topMargin + lineSpacing;
                int right = left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();

                child.layout(left, top, right, bottom);

                paddingLeft += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + viewSpacing;
            }
            paddingTop += lineHeight;
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
