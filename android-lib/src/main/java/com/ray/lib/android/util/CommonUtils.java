package com.ray.lib.android.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CommonUtils {

    /**
     * 检测网络是否可用
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    /**
     * 检测Sdcard是否存在
     *
     */
    public static boolean isExitsSdcard() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    static String getStrng(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    /**
     * 压缩字符串
     *
     * @param str 需要压缩的字符串
     * @return 压缩结果
     */
    public static byte[] compress(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        GZIPOutputStream gzipStream = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            gzipStream = new GZIPOutputStream(out);
            gzipStream.write(str.getBytes());
            gzipStream.flush();
            gzipStream.finish();
            return out.toByteArray();
        } catch (IOException e) {
            return null;
        } finally {
            if (gzipStream != null) {
                try {
                    gzipStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 客户端版本号作为数据库版本号
     */
    public static int getLocalVersions(Context context) {
        PackageManager manager = context.getPackageManager();
        int version = 1;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取客户端versionName
     */
    public static String getLocalVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}