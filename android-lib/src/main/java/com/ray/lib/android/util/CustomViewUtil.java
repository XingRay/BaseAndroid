package com.ray.lib.android.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-06-16
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CustomViewUtil {
    private CustomViewUtil() {
        throw new UnsupportedOperationException();
    }

    public static void drawCoordinate(Canvas canvas, Paint paint, int width, int height) {
        drawCoordinate(canvas, paint, 0, 0, width, height);
    }

    public static void drawCoordinate(Canvas canvas, Paint paint, int left, int top, int right, int bottom) {
        drawCoordinate(canvas, paint, new RectF(left, top, right, bottom));
    }

    /**
     * 在画布上画坐标系
     *
     * @param canvas 画布
     * @param paint  画笔
     * @param rect   坐标系所在的矩形
     */
    public static void drawCoordinate(Canvas canvas, Paint paint, RectF rect) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        float[] points = {rect.left, rect.top, rect.right, rect.top, rect.left, rect.top, rect.left, rect.bottom};
        canvas.drawLines(points, paint);

        List<Float> lines = new ArrayList<>();
        for (float x = 10; x < rect.right; x += 10) {
            if (x % 100 == 0) {
                continue;
            }

            lines.add(rect.left + x);
            lines.add(rect.top);
            lines.add(rect.left + x);
            lines.add(rect.bottom);
        }

        for (float y = 10; y < rect.bottom; y += 10) {
            if (y % 100 == 0) {
                continue;
            }

            lines.add(rect.left);
            lines.add(rect.top + y);
            lines.add(rect.right);
            lines.add(rect.top + y);
        }

        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor("#cccccc"));
        points = new float[lines.size()];
        for (int i = 0; i < points.length; i++) {
            float f = lines.get(i);
            points[i] = f;
        }
        canvas.drawLines(points, paint);

        lines = new ArrayList<>();
        for (float x = 100; x < rect.right; x += 100) {
            lines.add(rect.left + x);
            lines.add(rect.top);
            lines.add(rect.left + x);
            lines.add(rect.bottom);
        }

        for (float y = 100; y < rect.bottom; y += 100) {
            lines.add(rect.left);
            lines.add(rect.top + y);
            lines.add(rect.right);
            lines.add(rect.top + y);
        }

        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#aaaaaa"));
        points = new float[lines.size()];
        for (int i = 0; i < points.length; i++) {
            float f = lines.get(i);
            points[i] = f;
        }
        canvas.drawLines(points, paint);
    }
}
