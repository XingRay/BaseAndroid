package com.ray.lib.android.util;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Author      : leixing
 * Date        : 2017-04-28
 * Email       : leixing@hecom.cn
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
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, resId);
        } else {
            return context.getResources().getColor(resId);
        }
    }
}
