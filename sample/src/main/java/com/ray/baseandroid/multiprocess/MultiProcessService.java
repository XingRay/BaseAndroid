package com.ray.baseandroid.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ray.lib.android.util.TraceUtil;

/**
 * Author      : leixing
 * Date        : 2017-05-22
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiProcessService extends Service {
    public MultiProcessService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        TraceUtil.log();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        TraceUtil.log();
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TraceUtil.log();
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
