package com.ray.baseandroid.commonlistview.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author      : leixing
 * @date        : 2017-04-06
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : 线条分割线
 */

public class LineDividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private int mHeight;
    private int mColor;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mHeaderSize;
    private int mFooterSize;

    public LineDividerItemDecoration() {
        this(2, 0xffe6e8ea);
    }

    public LineDividerItemDecoration(int height, int color) {
        super();
        mHeight = height;
        mColor = color;

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);

        mPaddingLeft = 0;
        mPaddingRight = 0;

        mHeaderSize = 0;
    }

    public LineDividerItemDecoration paddingLeft(int paddingLeft) {
        mPaddingLeft = paddingLeft;
        return this;
    }

    public LineDividerItemDecoration paddingRight(int paddingRight) {
        mPaddingRight = paddingRight;
        return this;
    }

    public LineDividerItemDecoration headerSize(int headerSize) {
        mHeaderSize = headerSize;
        return this;
    }

    public LineDividerItemDecoration footerSize(int footerSize) {
        mFooterSize = footerSize;
        return this;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setColor(int color) {
        this.mColor = color;
        mPaint.setColor(mColor);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft() + mPaddingLeft;
        rect.right = parent.getWidth() - parent.getPaddingRight() - mPaddingRight;

        for (int i = mHeaderSize; i < childCount - 1 - mFooterSize; i++) {
            View child = parent.getChildAt(i);
            rect.top = child.getBottom();
            rect.bottom = rect.top + mHeight;
            c.drawRect(rect, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom += mHeight;
    }
}
