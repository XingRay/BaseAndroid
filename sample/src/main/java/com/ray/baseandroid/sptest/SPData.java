package com.ray.baseandroid.sptest;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.ray.baseandroid.common.SystemApplication;
import com.ray.lib.android.util.SPUtil;
import com.ray.lib.android.util.TraceUtil;
import com.ray.lib.java.util.RandomUtil;

/**
 * @@author      : leixing
 * @@date        : 2017-06-02
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SPData {
    public static final int ENTRY_NUMBER = 1000;
    private static volatile SPData INSTANCE = null;

    private final SharedPreferences mSP;

    public static SPData getInstance() {
        if (INSTANCE == null) {
            synchronized (SPData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SPData();
                }
            }
        }

        return INSTANCE;
    }

    private SPData() {
        mSP = SPUtil.getSharedPreferences(SystemApplication.getContext(), "sp_data");
    }

    public void saveData(String prefix) {
        for (int i = 0; i < ENTRY_NUMBER; i++) {
            String value = prefix + "_" + i + "\n" + RandomUtil.getRandomString(300);
            SPUtil.putString(mSP, getKey(i), value);
        }
    }

    public String loadData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            String value = SPUtil.getString(mSP, getKey(i));
            stringBuilder.append(value).append("\n\n");
        }

        return stringBuilder.toString();
    }

    @NonNull
    private String getKey(int i) {
        return "key" + i;
    }
}
