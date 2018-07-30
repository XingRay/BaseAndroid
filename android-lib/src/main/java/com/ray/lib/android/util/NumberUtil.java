package com.ray.lib.android.util;

import android.graphics.Color;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class NumberUtil {
    private static final String TAG = NumberUtil.class.getSimpleName();
    private static final float[] color1 = {10f, 0.70f, 0.99f};
    private static final float[] color2 = {235f, 0.31f, 0.85f};
    private static final float[] color3 = {196f, 0.70f, 1.0f};
    private static final float[] color4 = {47f, 1.0f, 0.97f};
    private static final float[] color5 = {204f, 0.76f, 0.86f};
    private static final float[] color6 = {100f, 0.60f, 0.78f};

    /**
     * 相减
     */
    public static String getSubtraction(String str1, String str2) {
        int a1 = getStringToInt(str1);
        int a2 = getStringToInt(str2);
        return String.valueOf(a1 - a2);
    }

    /**
     * 相除,保留1位小数
     */
    public static String getDivision1(int i1, int i2) {
        if (i1 == 0 || i2 == 0) {
            return "0";
        } else {
            float f = i1 * 1.0f / i2 * 1.0f;
            DecimalFormat df = new DecimalFormat("0.0");
            return df.format(f);
        }
    }

    /**
     * 相除,保留2位小数
     */
    public static String getDivision2(int i1, int i2) {
        if (i1 == 0 || i2 == 0) {
            return "0";
        } else {
            float f = i1 * 1.0f / i2 * 1.0f;
            NumberFormat ddf1 = NumberFormat.getNumberInstance();
            ddf1.setMaximumFractionDigits(2);
            return ddf1.format(f);
        }
    }

    /**
     * 相除,保留两位小数
     */
    public static String getDivision1(String str1, String str2) {
        int a1 = getStringToInt(str1);
        int a2 = getStringToInt(str2);
        if (a2 == 0 || a1 == 0) {
            return "0";
        } else {
            float f = a1 * 1.0f / a2 * 1.0f;
            NumberFormat ddf1 = NumberFormat.getNumberInstance();
            ddf1.setMaximumFractionDigits(2);
            return ddf1.format(f);
        }
    }

    /**
     * 相除,保留两位小数
     */
    public static String getDivision1(String str1, String str2, String str3) {
        int a1 = getStringToInt(str1);
        int a2 = getStringToInt(str2);
        int a3 = getStringToInt(str3);
        if (a2 == 0 || a1 == 0 || a3 == 0) {
            return "0";
        } else {
            float f = a1 * 1.0f / (a2 * 1.0f * a3 * 1.0f);
            NumberFormat ddf1 = NumberFormat.getNumberInstance();
            ddf1.setMaximumFractionDigits(2);
            return ddf1.format(f);
        }
    }

    public static String getActiveDivisor(String str) {
        float f = getStringToFloat(str);
        if (f > 1f) {
            f = 1f;
        }
        return String.valueOf(f);
    }

    /**
     * float转百分值
     */
    public static String getPersentValue(float f) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(f);
    }

    /**
     * String转合法int
     */
    public static int getStringToInt(String str) {
        int a;
        try {
            a = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            a = 0;
        }
        return a;
    }

    /**
     * String转合法float
     */
    public static float getStringToFloat(String str) {
        float i;
        try {
            i = Float.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = 0f;
        }
        return i;
    }

    /**
     * String转合法long
     */
    public static long getStringToLong(String str) {
        long i;
        try {
            i = Long.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = 0L;
        }
        return i;
    }

    /**
     * String转合法double
     */
    public static double getStringToDouble(String str) {
        double i = 0.0d;
        try {
            i = Double.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = 0.0d;
        }
        return i;
    }

    public static Double getStringToDoubleObj(String str) {
        String temp = str;
        if (temp.contains(",")) {
            temp = temp.replace(",", "");
        }
        try {
            return Double.valueOf(temp);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 获取百分值
     */
    public static int getPersentValue(float f, float max) {
        return (int) (f / max * 100f);
    }

    /**
     * 报表饼状图颜色处理方法
     */
    public static int[] parseColors(int size) {
        float[][] colorsum = {color1, color2, color3, color4, color5, color6};
        int[] colors = new int[size];
        for (int i = 0; i < size; i++) {
            if (i < 6) {
                colors[i] = Color.HSVToColor(colorsum[i]);
            } else if (i < 12) {
                float[] colorf = Arrays.copyOf(colorsum[i - 6], colorsum[i - 6].length);
                colorf[2] -= 0.20f;
                colors[i] = Color.HSVToColor(colorf);
            } else if (i < 18) {
                float[] colorf = Arrays.copyOf(colorsum[i - 12], colorsum[i - 12].length);
                colorf[1] -= 0.20f;
                colors[i] = Color.HSVToColor(colorf);
            } else {
                colors[i] = Color.HSVToColor(colorsum[i % colorsum.length]);
            }
        }
        return colors;
    }

    /**
     * 相除获取合法String
     */
    public static String getLegalDivision(String visitPoint, String allDayString) {
        float allVisit = 0;
        try {
            if (visitPoint != null)
                allVisit = Float.parseFloat(visitPoint);
            float allDay = 0;
            if (allDayString != null)
                allDay = Float.parseFloat(allDayString);
            float averageVisit = 0;
            if (allDay != 0)
                averageVisit = Math.round(allVisit / allDay * 10) / 10.0f;
            return String.valueOf(averageVisit);
        } catch (NumberFormatException numberFormatException) {
            return "0.0";
        }
    }
}
