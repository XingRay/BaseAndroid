package com.ray.lib.android.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
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
     * @param context 上下文
     * @return 应用是否在前台
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
        if (appProcesses == null || appProcesses.isEmpty()) {
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

    /**
     * 根据包名判断app是否安装了
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return 该应用是否安装
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : installedApplications) {
            if (info == null) {
                continue;
            }

            if (!info.packageName.equals(packageName)) {
                continue;
            }

            return true;
        }

        return false;
    }

    /**
     * 获取应用的版本
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用的包名，无法获取到该应用信息则返回{@code null}
     */
    public static String getVersionName(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (packageInfo == null) {
            return null;
        }

        return packageInfo.versionName;
    }

    /**
     * 获取应用的版本号
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用的版本号，无法获取应用信息则返回-1
     */
    public static int getVersionCode(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (packageInfo == null) {
            return -1;
        }

        return packageInfo.versionCode;
    }

    /**
     * 获取应用的信息
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用信息
     */
    public static PackageInfo getPackageInfo(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageInfo;
    }

    /**
     * get name of current process
     *
     * @param context runtime context
     * @return name of current process
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processes) {
            if (process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }
}
