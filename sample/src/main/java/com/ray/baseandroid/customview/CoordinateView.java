package com.ray.baseandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @@author      : leixing
 * @@date        : 2017/6/19 21:49
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CoordinateView extends View {

    private Paint mPaint;
    private RectF mRect;

    public CoordinateView(Context context) {
        this(context, null);
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mRect = new RectF(100, 100, 300, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        for(int i=0; i< 10; i++){
            canvas.drawRect(mRect, mPaint);
            canvas.translate(10, 10);
        }

        canvas.restore();

        canvas.translate(400, 0);

        canvas.save();
        for(int i=0; i<10; i++){
            canvas.drawRect(mRect, mPaint);
            canvas.scale(0.9f, 0.9f, 200, 200);
        }
        canvas.restore();

        canvas.translate(-400, 400);

        canvas.save();
        canvas.drawCircle(200, 200, 200, mPaint);
        for(int i=0; i<60; i++){
            canvas.drawLine(200, 0, 200, 20, mPaint);
            canvas.rotate(6, 200, 200);
        }
        canvas.restore();
    }
}
