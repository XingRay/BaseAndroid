package com.ray.lib.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.TypedValue;
import android.view.View;

/**
 * 资源工具类
 *
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/7/30 20:09
 * <p>
 */

@SuppressWarnings("unused")
public class ResUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static TypedValue sTempValue;
    private static final Object LOCK = new Object();

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getColor(resId);
        } else {
            // noinspection deprecation
            return mContext.getResources().getColor(resId);
        }
    }

    public static ColorStateList getColorStateList(@ColorRes int resId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.M) {
            return mContext.getResources().getColorStateList(resId, null);
        } else {
            // noinspection deprecation
            return mContext.getResources().getColorStateList(resId);
        }
    }


    /**
     * getDrawable() 5.0之后过时/弃用问题
     */
    public static Drawable getDrawable(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mContext.getDrawable(resId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // noinspection deprecation
            return mContext.getResources().getDrawable(resId);
        } else {
            // Prior to JELLY_BEAN, Resources.getDrawable() would not correctly
            // retrieve the final configuration density when the resource ID
            // is a reference another Drawable resource. As a workaround, try
            // to resolve the drawable reference manually.
            final int resolvedId;
            synchronized (LOCK) {
                if (sTempValue == null) {
                    sTempValue = new TypedValue();
                }
                mContext.getResources().getValue(resId, sTempValue, true);
                resolvedId = sTempValue.resourceId;
            }
            // noinspection deprecation
            return mContext.getResources().getDrawable(resolvedId);
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            // noinspection AliDeprecation
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int[] getArray(int resId) {
        TypedArray typedArray = mContext.getResources().obtainTypedArray(resId);
        int len = typedArray.length();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return array;
    }
}
