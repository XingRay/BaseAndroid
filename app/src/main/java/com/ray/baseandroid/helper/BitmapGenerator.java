package com.ray.baseandroid.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

import com.ray.baseandroid.util.ViewUtil;

/**
 * Author      : leixing
 * Date        : 2017-01-18
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class BitmapGenerator {
    private BitmapGenerator() {
        throw new UnsupportedOperationException();
    }

    /**
     * 生成一张地图上的分布点的图标
     *
     * @param num
     * @param areaName
     * @return
     */
    public static Bitmap buildDistributionBitmap(Context context, String num, String areaName) {
        int radius = (int) ViewUtil.dp2px(context, 45);
        Bitmap circleBitmap = buildCircleBitmap(radius, Color.parseColor("#cce15151"));
        drawNumInCircleBitmap(circleBitmap, num);
        Bitmap rectBitmap = buildRectWithText(areaName, Color.parseColor("#cc836f6f"));
        float width = Math.max(circleBitmap.getWidth(), rectBitmap.getWidth());
        float gap = 1f;
        float height = circleBitmap.getHeight() + gap + rectBitmap.getHeight() + ViewUtil.dp2px(context, 2f);
        Bitmap bm = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();

        float circleLeft = (width - circleBitmap.getWidth()) / 2.0f;
        float circleTop = 0f;
        canvas.drawBitmap(circleBitmap, circleLeft, circleTop, paint);

        float rectLeft = (width - rectBitmap.getWidth()) / 2.0f;
        float rectTop = circleTop + circleBitmap.getHeight() + gap;
        canvas.drawBitmap(rectBitmap, rectLeft, rectTop, paint);
        circleBitmap.recycle();
        rectBitmap.recycle();
        return bm;
    }

    /**
     * 构建一个圆的位图
     *
     * @param radius 圆的半径
     * @param color  颜色
     * @return
     */
    public static Bitmap buildCircleBitmap(int radius, int color) {
        Bitmap bm = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawCircle(radius / 2.0f, radius / 2.0f, radius / 2.0f, paint);
        return bm;
    }

    public static void drawNumInCircleBitmap(Bitmap circleBitmap, String num) {
        Canvas canvas = new Canvas(circleBitmap);
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setTextSize(38);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        int baseX = canvas.getWidth() / 2;
        int baseY = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.drawText(String.valueOf(num), baseX, baseY, paint);
    }

    public static Bitmap buildRectWithText(String text, int color) {
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        float textWidth = textPaint.measureText(text);
        float width = textWidth + 2 * 10;
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        float height = fm.bottom - fm.top;

        Bitmap bm = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(color);
        canvas.drawRect(0, 0, width, height, bgPaint);

        int baseX = (int) (width / 2);
        int baseY = (int) ((height / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        canvas.drawText(text, baseX, baseY, textPaint);
        return bm;
    }
}
