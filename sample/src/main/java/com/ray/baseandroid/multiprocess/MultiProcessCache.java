package com.ray.baseandroid.multiprocess;

import android.content.SharedPreferences;

import com.ray.baseandroid.common.SystemApplication;
import com.ray.lib.android.util.SPUtil;

/**
 * Author      : leixing
 * Date        : 2017-05-22
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiProcessCache {
    public static final String UID = "uid";
    public static final String NAME = "name";
    public static final String DATA = "data";
    private static MultiProcessCache INSTANCE;

    private String uid;
    private String name;
    private String data;
    private final SharedPreferences mSP;

    public static MultiProcessCache getInstance() {
        if (INSTANCE == null) {
            synchronized (MultiProcessCache.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MultiProcessCache();
                }
            }
        }

        return INSTANCE;
    }

    public MultiProcessCache() {
        mSP = SPUtil.getSharedPreferences(SystemApplication.getContext(), "multi_process_cache");
    }


    public String getUid() {
        return SPUtil.getString(mSP, UID);
    }

    public void setUid(String uid) {
        SPUtil.putString(mSP, UID, uid);
    }

    public String getName() {
        return SPUtil.getString(mSP, NAME);
    }

    public void setName(String name) {
        SPUtil.putString(mSP, NAME, name);
    }

    public String getData() {
        return SPUtil.getString(mSP, DATA);
    }

    public void setData(String data) {
        SPUtil.putString(mSP, DATA, data);
    }
}
