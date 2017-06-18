package com.ray.baseandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017/6/18 22:54
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MoveBallView extends View {

    private static final int DIRECT_STAY = 0;
    private static final int DIRECT_FORWARD = 1;
    private static final int DIRECT_BACKWARD = -1;
    private static final int SPEED = 15;

    private int radius = 50;
    private int x;
    private int y;

    int direct;

    private Paint paint;

    public MoveBallView(Context context) {
        this(context, null);
    }

    public MoveBallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MoveBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        x = radius;
        y = 100;
        direct = DIRECT_FORWARD;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
        int width = getMeasuredWidth();
        if (width < 2 * radius) {
            direct = DIRECT_STAY;
        } else if (x < radius) {
            direct = DIRECT_FORWARD;
        } else if (x > width - radius) {
            direct = DIRECT_BACKWARD;
        }

        x += SPEED * direct;

    }
}
