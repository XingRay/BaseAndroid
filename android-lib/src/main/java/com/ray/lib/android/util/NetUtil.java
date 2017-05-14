package com.ray.lib.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by leixing on 2016/7/7.
 */
public class NetUtil {
    private NetUtil() {
    }

    /**
     * 获取当前数据网络(2G/3G/4G)是否处于已经链接的状态
     *
     * @param context
     * @return
     */
    public static boolean getMobileDataStatus(Context context) {
        if (context == null) {
            TraceUtil.log("context = null");
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        return state == NetworkInfo.State.CONNECTED;
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }
}
