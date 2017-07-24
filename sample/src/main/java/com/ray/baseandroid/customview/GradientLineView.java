package com.ray.baseandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-07-21
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class GradientLineView extends View {

    private Paint mPaint;

    public GradientLineView(Context context) {
        this(context, null);
    }

    public GradientLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int y = height >> 1;
        int[] colors = new int[]{0x00ffffff,0xbbe6e8ea, 0xffe6e8ea, 0xbbe6e8ea, 0x00ffffff};
        float[] positions = new float[]{0f, 0.3f, 0.5f, 0.7f, 1.0f};
        LinearGradient gradient = new LinearGradient(0, y, width, y, colors, positions, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        canvas.drawRect(new RectF(0, 0, width, height), mPaint);
    }
}
