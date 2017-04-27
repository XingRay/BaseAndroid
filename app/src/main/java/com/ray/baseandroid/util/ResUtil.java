package com.ray.baseandroid.util;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by libo on 16/8/22.
 */
public class ResUtil {
    /**
     * 获取字符串资源
     *
     * @param id
     * @return
     */
    public static String getStringRes(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取字符串资源
     *
     * @param id
     * @return
     */
    public static String getStringRes(Context context, int id, Object... formatArgs) {
        return context.getResources().getString(id, formatArgs);
    }

    /**
     * 获取格式化后的
     *
     * @return
     */
    public static String getFormatStringRes(Context context, int srcid, int replaceid) {
        return String.format(getStringRes(context, srcid), getStringRes(context, replaceid));
    }

    /**
     * 获取拼接后的
     *
     * @return
     */
    public static String getAppendStringRes(Context context, int... resids) {
        if (resids == null || resids.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = resids.length;
        for (int i = 0; i < len; i++) {
            sb.append(getStringRes(context, resids[i]));
        }
        return sb.toString();
    }

    /**
     * getColor() 6.0之后过时/弃用问题
     *
     * @param resId
     * @return
     */
    public static final int getColor(Context context, int resId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, resId);
        } else {
            return context.getResources().getColor(resId);
        }
    }
}
