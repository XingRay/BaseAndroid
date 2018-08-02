package com.ray.lib.android.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.Locale;

/**
 * 资源工具类
 */
public class ResUtil {

    private static Context mContext;

    private ResUtil() {
        throw new UnsupportedOperationException();
    }

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 获取字符串资源
     */
    public static String getString(int id) {
        return mContext.getResources().getString(id);
    }

    /**
     * 获取字符串资源
     */
    public static String getString(int id, Object... formatArgs) {
        return mContext.getString(id, formatArgs);
    }

    /**
     * 获取格式化后的
     */
    public static String getFormatString(int srcid, int replaceid) {
        return String.format(getString(srcid), getString(replaceid));
    }

    /**
     * 获取拼接后的
     */
    public static String getAppendStringRes(int... resids) {
        if (resids == null || resids.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = resids.length;
        for (int resId : resids) {
            sb.append(getString(resId));
        }
        return sb.toString();
    }

    /**
     * getColor() 6.0之后过时/弃用问题
     */
    public static int getColor(int resId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(mContext, resId);
        } else {
            return mContext.getColor(resId);
        }
    }


    /**
     * getDrawable() 5.0之后过时/弃用问题
     */
    public static final Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    public static String getStringByLocale(Locale locale, int resId) {
        Resources resources = mContext.getResources();
        Locale mCurLocale = resources.getConfiguration().locale;
        Configuration conf = new Configuration(resources.getConfiguration());
        conf.locale = locale;
        String result = new Resources(resources.getAssets(), resources.getDisplayMetrics(), conf).getString(resId);
        conf.locale = mCurLocale;
        new Resources(resources.getAssets(), resources.getDisplayMetrics(), conf);
        return result;
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
