package com.ray.lib.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class SPUtil {

    private SPUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static SharedPreferences getSharedPreferences(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static void putBoolean(SharedPreferences sp, String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(SharedPreferences sp, String key) {
        return sp.getBoolean(key, false);
    }

    public static void putInt(SharedPreferences sp, String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(SharedPreferences sp, String key) {
        return sp.getInt(key, 0);
    }

    public static void putLong(SharedPreferences sp, String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(SharedPreferences sp, String key) {
        return sp.getLong(key, 0);
    }

    public static void putFloat(SharedPreferences sp, String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(SharedPreferences sp, String key) {
        return sp.getFloat(key, 0.0f);
    }

    public static void putString(SharedPreferences sp, String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static String getString(SharedPreferences sp, String key) {
        return sp.getString(key, null);
    }

    public static void putStringSet(SharedPreferences sp, String key, Set<String> value) {
        sp.edit().putStringSet(key, value).apply();
    }

    public static Set<String> getStringSet(SharedPreferences sp, String key) {
        return sp.getStringSet(key, null);
    }

    public static void remove(SharedPreferences sp, String key) {
        sp.edit().remove(key).apply();
    }

    public static void clear(SharedPreferences sp) {
        sp.edit().clear().apply();
    }
}
