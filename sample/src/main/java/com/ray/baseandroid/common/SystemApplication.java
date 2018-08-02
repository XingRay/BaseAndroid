package com.ray.baseandroid.common;

import android.app.Application;
import android.content.Context;

import com.ray.lib.android.util.TraceUtil;

/**
 * @@author      : leixing
 * @@date        : 2017-05-22
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SystemApplication extends Application {

    private static SystemApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static Context getContext() {
        return INSTANCE.getApplicationContext();
    }
}
