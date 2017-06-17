package com.ray.lib.android.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Author      : leixing
 * Date        : 2017-04-28
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ResUtil {
    /**
     * 获取字符串资源
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取字符串资源
     */
    public static String getString(Context context, int id, Object... formatArgs) {
        return context.getResources().getString(id, formatArgs);
    }

    /**
     * 获取格式化后的
     */
    public static String getFormatedString(Context context, int srcId, int replaceId) {
        return String.format(getString(context, srcId), getString(context, replaceId));
    }

    /**
     * 获取拼接后的
     */
    public static String getAppendString(Context context, int... resId) {
        if (resId == null || resId.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : resId) {
            sb.append(getString(context, i));
        }
        return sb.toString();
    }

    /**
     * getColor() 6.0之后过时/弃用问题
     */
    public static int getColor(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(resId);
        } else {
            return context.getResources().getColor(resId);
        }
    }

    /**
     * @param resId 资源ID
     * @return Drawable资源
     */
    public Drawable getDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getDrawable(resId, null);
        } else {
            return context.getResources().getDrawable(resId);
        }
    }

    /**
     * @param resId 资源ID
     * @return Dimension值
     */
    public float getDimension(Context context, int resId) {
        return context.getResources().getDimension(resId);
    }

    /**
     * @param resId 资源ID
     * @return 像素尺寸
     */
    public int getDimensionPixelSize(Context context, int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    /**
     * @param resId 资源ID
     * @return 字符资源数组
     */
    public String[] getStringArray(Context context, int resId) {
        return context.getResources().getStringArray(resId);
    }
}
