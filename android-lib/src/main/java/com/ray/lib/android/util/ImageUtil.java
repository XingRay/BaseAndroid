package com.ray.lib.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ImageUtil {

    public static final int MAXCLIP = 256;

    /**
     * 根据制定宽高切割图片
     *
     * @param sourceBm 图片源
     * @param maxW     切割宽度
     * @param maxH     切割高度
     * @return
     */
    public static Bitmap[] clipBitMap(Bitmap sourceBm, int maxW, int maxH) {
        int w = sourceBm.getWidth();
        int h = sourceBm.getHeight();
        // 按宽度需要切割的份数
        int numW = w / maxW;
        numW = (w % maxW == 0) ? numW : numW + 1;
        // 按高度需要切割的份数
        int numH = h / maxH;
        numH = (h % maxH == 0) ? numH : numH + 1;
        // 最终图片的个数
        int num = numW * numH;
        // 宽度的跨度
        int spanW = w / numW;
        spanW = (w % numW == 0) ? spanW : spanW + 1;
        // 宽度的跨度
        int spanH = h / numH;
        spanH = (h % numH == 0) ? spanH : spanH + 1;
        Bitmap[] bm = new Bitmap[num];
        for (int j = 0; j < numH; j++) {
            for (int i = 0; i < numW; i++) {
                int x = i * spanW;
                int y = j * spanH;
                int disW = spanW;
                int disH = spanH;
                if (i == numW - 1) {
                    disW = w - i * spanW;
                }
                if (j == numH - 1) {
                    disH = h - j * spanH;
                }
                System.out.println("x = " + x + "," + "y = " + y + "w = " + disW + "h = " + disH);
                bm[(j * numW) + i] = Bitmap.createBitmap(sourceBm, x, y, disW, disH);
            }
        }
        return bm;
    }

    @SuppressLint("NewApi")
    public static void blur(Bitmap bkg, ImageView view, Context context) {
        if (bkg == null) {
            return;
        }
        long startMs = System.currentTimeMillis();
        float scaleFactor = 0.5f;// 图片缩放比例；
        float radius = 30;// 模糊程度

        Matrix matrix = new Matrix();
        matrix.postScale(scaleFactor, scaleFactor); // 长和宽放大缩小的比例
        Bitmap overlay = Bitmap.createBitmap(bkg, 0, 0, bkg.getWidth(), bkg.getHeight(), matrix,
                true);

        overlay = doBlur(overlay, (int) radius, true);
        // view.setBackgroundDrawable(new BitmapDrawable(context.getResources(),
        // overlay));
        view.setImageBitmap(overlay);
        // 高斯模糊处理时间,如果对模糊完图片要求不高， 可将scaleFactor设置大一些。
        System.out.println("blur time:" + (System.currentTimeMillis() - startMs));
    }

    public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    /**
     * @param startColor
     * @param endColor
     * @param per        变化百分比（0-100）
     * @return 当前颜色值
     */
    public static int getGradientColor(int startColor, int endColor, int per) {
        int a_start = (startColor >> 24) & 0xff;
        int r_start = (startColor >> 16) & 0xff;
        int g_start = (startColor >> 8) & 0xff;
        int b_start = (startColor) & 0xff;

        int a_end = (endColor >> 24) & 0xff;
        int r_end = (endColor >> 16) & 0xff;
        int g_end = (endColor >> 8) & 0xff;
        int b_end = (endColor) & 0xff;

        int a_step = (a_end - a_start) * per / 100;
        int r_step = (r_end - r_start) * per / 100;
        int g_step = (g_end - g_start) * per / 100;
        int b_step = (b_end - b_start) * per / 100;

        int a_current = a_start + a_step;
        int r_current = r_start + r_step;
        int g_current = g_start + g_step;
        int b_current = b_start + b_step;
        return (a_current << 24) | (r_current << 16) | (g_current << 8) | b_current;
    }

}
