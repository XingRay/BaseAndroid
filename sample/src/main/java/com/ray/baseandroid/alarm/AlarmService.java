package com.ray.baseandroid.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ray.lib.android.util.TraceUtil;

/**
 * Author      : leixing
 * Date        : 2017-06-05
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class AlarmService extends Service {
    public AlarmService() {
        TraceUtil.printStackTrace();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TraceUtil.log();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String notification = intent.getStringExtra("notification");
        TraceUtil.log(notification);
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String notification = intent.getStringExtra("notification");
        TraceUtil.log(notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        TraceUtil.log();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TraceUtil.log();
    }
}
