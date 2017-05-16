package com.ray.lib.android.util;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;

import java.io.File;

/**
 * use for get information of storage
 * 用于获取存储器中的信息
 */
public class StorageInfoUtil {
    private StorageInfoUtil() {
    }

    public static String getTotalSpace(Context context, File file) {
        long totalSpace = file.getTotalSpace();
        return Formatter.formatFileSize(context, totalSpace);
    }

    public static String getFreeSpace(Context context, File file) {
        long freeSpace = file.getFreeSpace();
        return Formatter.formatFileSize(context, freeSpace);
    }

    public static String getUsableSpace(Context context, File file) {
        long usableSpace = file.getUsableSpace();
        return Formatter.formatFileSize(context, usableSpace);
    }

    public static String getUsedSpace(Context context, File file) {
        long totalSpace = file.getTotalSpace();
        long freeSpace = file.getFreeSpace();
        return Formatter.formatFileSize(context, totalSpace - freeSpace);
    }

    public static String getSDCardTotalSpace(Context context) {
        return getTotalSpace(context, Environment.getExternalStorageDirectory());
    }

    public static String getSDCardFreeSpace(Context context) {
        return getFreeSpace(context, Environment.getExternalStorageDirectory());
    }

    public static String getSDCardUsableSpace(Context context) {
        return getUsableSpace(context, Environment.getExternalStorageDirectory());
    }

    public static String getSDCardUsedSpace(Context context) {
        return getUsedSpace(context, Environment.getExternalStorageDirectory());
    }

    public static String getInnerTotalSpace(Context context) {
        return getTotalSpace(context, Environment.getDataDirectory());
    }

    public static String getInnerFreeSpace(Context context) {
        return getFreeSpace(context, Environment.getDataDirectory());
    }

    public static String getInnerUsableSpace(Context context) {
        return getUsableSpace(context, Environment.getDataDirectory());
    }

    public static String getInnerUsedSpace(Context context) {
        return getUsedSpace(context, Environment.getDataDirectory());
    }

}
