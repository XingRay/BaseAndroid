package com.ray.lib.android.util;

import android.content.Context;


/**
 * use for get information of phone
 * 用于获取电话的信息
 */
public class PhoneInfoUtil {
    private PhoneInfoUtil() {
    }

    public static String getPhoneNumber(Context context) {
        return SystemServiceUtil.getTelephonyManager(context).getLine1Number();
    }

    public static String getSimSerialNumber(Context context) {
        return SystemServiceUtil.getTelephonyManager(context).getSimSerialNumber();
    }
}
