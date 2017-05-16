package com.ray.lib.java.util;

/**
 * use for math
 */
public class MathUtil {
    public static int evaluateInt(float fraction, int startValue, int endValue) {

        int start3 = (startValue >> 24) & 0xff;
        int start2 = (startValue >> 16) & 0xff;
        int start1 = (startValue >> 8) & 0xff;
        int start0 = startValue & 0xff;


        int end3 = (endValue >> 24) & 0xff;
        int end2 = (endValue >> 16) & 0xff;
        int end1 = (endValue >> 8) & 0xff;
        int end0 = endValue & 0xff;

        return ((start3 + (int) (fraction * (end3 - start3))) << 24)
                | ((start2 + (int) (fraction * (end2 - start2))) << 16)
                | ((start1 + (int) (fraction * (end1 - start1))) << 8)
                | (start0 + (int) (fraction * (end0 - start0)));
    }
}
