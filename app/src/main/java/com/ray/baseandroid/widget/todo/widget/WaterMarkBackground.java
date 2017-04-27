package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

/**
 * 水印背景
 * Created by tianlupan on 16/1/29.
 */
public class WaterMarkBackground extends Drawable {

    private static final String TAG = WaterMarkBackground.class.getSimpleName();

    private Paint paint;

    private String line1;
    private String line2;
    private final int rotation = -12;
    private static final int DEFAULT_FONT_COLOR = 0xfff0f0f0;
    private static final int DEFAULT_BG_COLOR = Color.TRANSPARENT;
    private static final int MARGIN = 120;//dp
    private static final int TEXT_SIZE = 13;//dp
    private static final String DEFAULT_TEXT = "水印默认文字";

    private int solidColor;
    private Paint backgroundColorPaint;

    /**
     * 用户控制是否显示背景色,参考 {@link #setEnable(boolean, boolean)}
     */
    private boolean mShowBackground = true;

    /**
     * 用户控制是否显示水印,参考{@link #setEnable(boolean, boolean)}
     */
    private boolean mShowWatermark = true;

    /**
     * 水印背景,底色透明
     */
    public WaterMarkBackground() {
        this(DEFAULT_BG_COLOR);
    }

    /**
     * 水印背景,可设底色,如果不希望设置底色, solidColor设为Color.Transparent
     *
     * @param solidColor
     */
    public WaterMarkBackground(int solidColor) {

        line1 = DEFAULT_TEXT;
        line2 = DEFAULT_TEXT;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(TEXT_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);
        setFontColor(DEFAULT_FONT_COLOR);
        setBackgroundColor(solidColor);
    }

    private static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 设置背景色,如果不需要背景,设置为Color.Transaprent
     * 下次VIEW刷新会有改变; 如果希望设置立即刷新背景,调用 {@link View#invalidate()}
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        this.solidColor = color;
        if (solidColor != Color.TRANSPARENT) {
            backgroundColorPaint = new Paint();
            backgroundColorPaint.setColor(this.solidColor);
        } else {
            backgroundColorPaint = null;
        }
    }

    /**
     * 设置字体颜色,默认为0xffececec
     *
     * @param fontColor
     */
    public void setFontColor(int fontColor) {
        paint.setColor(fontColor);
    }

    /**
     * 设置文本一内容:默认 红圈通
     *
     * @param text
     */
    public void setLine1(String text) {
        this.line1 = text;
    }

    /**
     * 设置文本二内容:默认 姓名+帐号后四位
     *
     * @param text
     */
    public void setLine2(String text) {
        this.line2 = text;
    }

    /**
     * 控制是否显示背景色/水印
     *
     * @param showBackgroundColor 显示背景色,默认是透明色
     * @param showWaterMark       显示水印
     */
    public void setEnable(boolean showBackgroundColor, boolean showWaterMark) {
        mShowBackground = showBackgroundColor;
        mShowWatermark = showWaterMark;
    }

    @Override
    public void draw(Canvas canvas) {

        if (!isVisible()) return;

        if (solidColor != Color.TRANSPARENT && mShowBackground) {
            canvas.drawRect(getBounds(), backgroundColorPaint);
        }

        //if (!ConfigConstant.isWatermark() || !mShowWatermark) return;

        if (TextUtils.isEmpty(line1) && TextUtils.isEmpty(line2)) return;

        int marginLeft = 0;
        int row = 0;

        boolean firstLineAtFirst = true;
        canvas.save();
        canvas.rotate(rotation, getBounds().centerX(), getBounds().centerY());

        for (int y = -MARGIN; y < getBounds().height() + MARGIN; y += MARGIN / 2.5) {

            if (row > 0 && row % 4 == 0) {
                firstLineAtFirst = !firstLineAtFirst;
                marginLeft = 0;
            }

            for (int x = -MARGIN; x <= getBounds().width() + MARGIN; x += MARGIN * 2) {
                int realX = marginLeft + x;
                int realY = y;
                String text1, text2;
                if (firstLineAtFirst) {
                    text1 = line1;
                    text2 = line2;
                } else {
                    text1 = line2;
                    text2 = line1;
                }
                if (!TextUtils.isEmpty(text1)) {
                    canvas.drawText(text1, realX, realY, paint);
                }
                if (!TextUtils.isEmpty(text2)) {
                    canvas.drawText(text2, realX + MARGIN, realY, paint);
                }

            }

            row++;
            marginLeft += (MARGIN / 4);
        }

        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
