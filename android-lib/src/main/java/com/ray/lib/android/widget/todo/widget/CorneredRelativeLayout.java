package com.ray.lib.android.widget.todo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by zhanggaokai on 16/8/10.
 */
public class CorneredRelativeLayout extends RelativeLayout {

    private final static float CORNER_RADIUS = 8.0f;
    private float cornerRadius;

    public CorneredRelativeLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CorneredRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CorneredRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS, metrics);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.save();

        final Path path = new Path();
        path.addRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);

        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);
    }


}
