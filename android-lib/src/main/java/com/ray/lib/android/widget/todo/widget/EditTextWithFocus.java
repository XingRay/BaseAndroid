package com.ray.lib.android.widget.todo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class EditTextWithFocus extends AppCompatEditText {
    private int colorFocus = 0xFFCC0000;
    private int colorLoseFocus = 0xFF999999;

    private Paint linePaint;
    private int paperColor;

    public EditTextWithFocus(Context context) {
        super(context);
        init();
    }

    public EditTextWithFocus(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithFocus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.linePaint = new Paint();
        linePaint.setStrokeWidth(1);
        linePaint.setAntiAlias(false);
        linePaint.setColor(Color.RED);//设置下划线颜色
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        linePaint.setColor(isFocused() ? colorFocus : colorLoseFocus);

        // 画底线
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
                this.getHeight() - 1, linePaint);

    }
}
