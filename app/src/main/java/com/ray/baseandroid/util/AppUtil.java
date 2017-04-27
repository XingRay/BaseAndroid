package com.ray.baseandroid.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : App工具类
 */

public class AppUtil {
    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * APP是否正处于前台运行
     *
     * @param context
     * @return
     */
    public static boolean isAppOnForeground(Context context) {
        if (context == null) {
            return false;
        }

        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return false;
        }

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (CollectionUtil.isEmpty(appProcesses)) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess == null) {
                continue;
            }

            if (packageName.equals(appProcess.processName)) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }
}
