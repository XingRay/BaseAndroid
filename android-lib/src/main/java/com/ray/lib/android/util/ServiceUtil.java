package com.ray.lib.android.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

import java.util.List;

/**
 * Created by leixing on 2016/7/7.
 */
public class ServiceUtil {
    private ServiceUtil() {
    }

    /**
     * @param context 上下文
     * @param service 服务
     * @return 服务是否正在运行
     */
    public static boolean isServiceRunning(Context context, Class<? extends Service> service) {
        List<ActivityManager.RunningServiceInfo> runningServices = SystemServiceUtil.getActivityManager(context).getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : runningServices) {
            if (info.service.getClassName().equals(service.getName())) {
                return true;
            }
        }
        return false;
    }
}
