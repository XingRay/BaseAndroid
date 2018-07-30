package com.ray.baseandroid.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.ray.baseandroid.IMultiProcessService;
import com.ray.lib.android.util.AppUtil;
import com.ray.lib.android.util.TraceUtil;

/**
 * Author      : leixing
 * Date        : 2017-05-22
 * Email       : leixing1012@qq.com
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
        String processName = AppUtil.getProcessName(this.getApplicationContext());
        TraceUtil.log(processName);
        TraceUtil.log();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        TraceUtil.log();
        return mSub;
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

    IMultiProcessService.Stub mSub = new IMultiProcessService.Stub() {
        @Override
        public void setName(String name) throws RemoteException {
            MultiProcessCache.getInstance().setName(name);
        }

        @Override
        public void setUid(String uid) throws RemoteException {
            MultiProcessCache.getInstance().setUid(uid);
        }

        @Override
        public void setData(String data) throws RemoteException {
            MultiProcessCache.getInstance().setData(data);
        }
    };
}
