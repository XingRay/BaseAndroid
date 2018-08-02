package com.ray.baseandroid.multiprocess;

import android.content.SharedPreferences;

import com.ray.baseandroid.common.SystemApplication;
import com.ray.lib.android.util.SPUtil;

/**
 * @@author      : leixing
 * @@date        : 2017-05-22
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiProcessCache {
    public static final String UID = "uid";
    public static final String NAME = "name";
    public static final String DATA = "data";
    public static final String APP_ID = "001";
    private static MultiProcessCache INSTANCE;

    private String uid;
    private String name;
    private String data;

    private final SharedPreferences mSP;

    public static MultiProcessCache getInstance() {
        if (INSTANCE == null || !INSTANCE.isValid()) {
            synchronized (MultiProcessCache.class) {
                if (INSTANCE == null) {
                    MultiProcessCache cache = new MultiProcessCache();
                    cache.loadCache();
                    INSTANCE = cache;
                }
            }
        }

        return INSTANCE;
    }

    private void loadCache() {
        uid = SPUtil.getString(mSP, UID);
        name = SPUtil.getString(mSP, NAME);
        data = SPUtil.getString(mSP, DATA);
    }

    private boolean isValid() {
        return APP_ID.equals(uid);
    }

    public MultiProcessCache() {
        mSP = SPUtil.getSharedPreferences(SystemApplication.getContext(), "multi_process_cache");
        SPUtil.putString(mSP, UID, "001");
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        SPUtil.putString(mSP, UID, uid);
        this.uid = uid;
    }

    public String getName() {
        return SPUtil.getString(mSP, NAME);
    }

    public void setName(String name) {
        SPUtil.putString(mSP, NAME, name);
        this.name = name;
    }

    public String getData() {
        return SPUtil.getString(mSP, DATA);
    }

    public void setData(String data) {
        SPUtil.putString(mSP, DATA, data);
        this.data = data;
    }
}
