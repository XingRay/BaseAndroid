package com.ray.baseandroid.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */


@SuppressLint("NewApi")
public class DeviceInfo {
    private static final String SP_TRAFFIC = "Traffic";
    private static final String SP_TRAFFIC_NUM = "Traffic_num";
    private static final String SP_TRAFFIC_NUM_ADD = "Traffic_num_add";
    private static final String SP_TRAFFIC_BOOL_ADD = "Traffic_bool_add";
    /**
     * 存储设备的唯一ID，例如IMEI或是MAC，可能为空，注意判断
     */
    private static String sDeviceId = "";

    /**
     * 获取设备的唯一ID，例如IMEI或是MAC
     *
     * @param context
     * @return
     */
    public static String getImeiOrMac(Context context) {
        if (!isValidDeviceId(sDeviceId)) {
            TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            sDeviceId = phoneMgr.getDeviceId();
            if (!isValidDeviceId(sDeviceId)) {
                try {
                    sDeviceId = getLocalMacAddress(context).replace(":", "");
                } catch (Exception e) {
                }
            }
            sDeviceId = "m" + sDeviceId;
        }
        return sDeviceId;
    }

    public static boolean isValidDeviceId(String deviceId) {
        final String pattern = "^0+$";
        return !TextUtils.isEmpty(deviceId) && !deviceId.matches(pattern);
    }

    public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        }
        return "";
    }

    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static int getWidthPixels(Activity act) {
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getHeightPixels(Activity act) {
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println("Configs.statusBarHeight = " + statusBarHeight);
        return statusBarHeight;
    }

    /**
     * 保存本应用流量
     *
     * @param mContext
     */
    public static long getTraffic(Context mContext) {
        SharedPreferences _sp = mContext.getSharedPreferences(SP_TRAFFIC, 0);
        SharedPreferences.Editor editor = _sp.edit();
        int uid = mContext.getApplicationInfo().uid;
        // 发送字节
        // long send = getCurrentNetFlow("/proc/uid_stat/" + uid + "/tcp_snd");
        long send = TrafficStats.getUidTxBytes(uid);
        // 接收字节
        long receivce = TrafficStats.getUidRxBytes(uid);
        // long receivce = getCurrentNetFlow("/proc/uid_stat/" + uid +
        // "/tcp_rcv");

        boolean isAdd = _sp.getBoolean(SP_TRAFFIC_BOOL_ADD, false);
        // 读取已保存流量
        long traffic_num = _sp.getLong(SP_TRAFFIC_NUM, 0);
        long totalTraffic = 0;
        if (isAdd) {// 如重启过，那么计算流量方式为累加，清空重启状态
            if (send == -1 || receivce == -1) {
                send = 0;
                receivce = 0;
            }
            long traffic_num_add = _sp.getLong(SP_TRAFFIC_NUM_ADD, 0);
            totalTraffic = traffic_num_add + (send + receivce);
        } else {
            if (_sp.getLong(SP_TRAFFIC_NUM, 0) <= send + receivce) {
                totalTraffic = send + receivce;
            } else {
                totalTraffic = _sp.getLong(SP_TRAFFIC_NUM, 0) + (send + receivce);

                editor.putBoolean(SP_TRAFFIC_BOOL_ADD, true);
                editor.putLong(SP_TRAFFIC_NUM_ADD, _sp.getLong(SP_TRAFFIC_NUM, 0));
            }
        }
        editor.putLong(SP_TRAFFIC_NUM, totalTraffic);
        System.out.println("totalTraffic = " + totalTraffic);
        System.out.println("traffic_num = " + traffic_num);
        System.out.println("isAdd = " + isAdd);
        editor.commit();
        return totalTraffic;
    }

    /**
     * 读取当前进程的流量DEV文件值
     *
     * @param fileName 保存进程流量的文件
     * @return 文件保存的流量值
     */
    public static Long getCurrentNetFlow(String fileName) {
        Long currentNetFlow = 0L;
        String netFlowValue = null;
        FileReader fstream = null;
        try {
            fstream = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fstream);

            netFlowValue = in.readLine();

            if (netFlowValue != null) {
                currentNetFlow = Long.parseLong(netFlowValue);
            }
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentNetFlow;
    }

    // /**
    // * 获取本应用流量
    // *
    // * @param context
    // * @return
    // */
    // public static long getTraffic(Context context) {
    // SharedPreferences _sp = context.getSharedPreferences(SP_TRAFFIC, 0);
    // return _sp.getLong(SP_TRAFFIC_NUM, 0);
    // }

    public static long getTrafficBase(Context context) {
        int uid = context.getApplicationInfo().uid;
        // 发送字节
        long send = getCurrentNetFlow("/proc/uid_stat/" + uid + "/tcp_snd");
        // 接收字节
        long receivce = getCurrentNetFlow("/proc/uid_stat/" + uid + "/tcp_rcv");
        if (send == -1 || receivce == -1) {
            send = 0;
            receivce = 0;
        }
        return (send + receivce);
    }

    /**
     * 网络状态
     *
     * @return 网络状态
     * {@link ConnectivityManager#TYPE_MOBILE},
     * {@link ConnectivityManager#TYPE_WIFI},
     * {@link ConnectivityManager#TYPE_WIMAX},
     * {@link ConnectivityManager#TYPE_ETHERNET},
     * {@link ConnectivityManager#TYPE_BLUETOOTH},
     * or other types defined by {@link ConnectivityManager}
     */
    public static int getNetState(Context context) {
        ConnectivityManager mConnMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = mConnMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return 0;
        }
        return networkInfo.getType();
    }

    /**
     * GPS状态
     *
     * @param context
     * @return
     */
    public static boolean isGPSOpen(Context context) {
        String gps = Settings.System.getString(context.getContentResolver(), Settings.System.LOCATION_PROVIDERS_ALLOWED);
        if (gps != null) {
            String[] providers = gps.split(",");
            if (providers != null && providers.length > 0) {
                for (int i = 0; i < providers.length; i++) {
                    if (providers[i].equalsIgnoreCase("gps")) {
                        // bGpsStart = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 手机网络是否处于连接状态
     *
     * @param context
     * @return
     */
    public static boolean isConnected(@NonNull Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * wifi网络是否处于连接状态
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 获取移动数据网络的状态是否是已经链接状态
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean isConnected(@NonNull Context context, int type) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(type);
            return networkInfo != null && networkInfo.isConnected();
        } else {
            return isConnected(connMgr, type);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static boolean isConnected(@NonNull ConnectivityManager connMgr, int type) {
        Network[] networks = connMgr.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connMgr.getNetworkInfo(mNetwork);
            if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数据开关
     *
     * @param context
     * @return
     */
    @Deprecated
    public static boolean _getMobileDataStatus(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Class<?> conMgrClass = null; // ConnectivityManager类
        Field iConMgrField = null; // ConnectivityManager类中的字段
        Object iConMgr = null; // IConnectivityManager类的引用
        Class<?> iConMgrClass = null; // IConnectivityManager类
        Method getMobileDataEnabledMethod = null; // setMobileDataEnabled方法

        try {
            // 取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            // 取得ConnectivityManager类中的对象mService
            iConMgrField = conMgrClass.getDeclaredField("mService");
            // 设置mService可访问
            iConMgrField.setAccessible(true);
            // 取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            // 取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());
            // 取得IConnectivityManager类中的getMobileDataEnabled(boolean)方法
            getMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("getMobileDataEnabled");
            // 设置getMobileDataEnabled方法可访问
            getMobileDataEnabledMethod.setAccessible(true);
            // 调用getMobileDataEnabled方法
            return (Boolean) getMobileDataEnabledMethod.invoke(iConMgr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取sd卡剩余可用空间大小
     *
     * @return M单位
     */
    public static double getSdCardFreeSize() {
        try {
            boolean hasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (hasSDCard) {

                File path = Environment.getExternalStorageDirectory();
                StatFs statfs = new StatFs(path.getPath());

                double result = statfs.getAvailableBlocks() * statfs.getBlockSize();
                return result / 1024 / 1024;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取sd卡剩余可用空间大小
     *
     * @return KB单位
     */
    public static double getSdCardFreeSizeKB() {
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {

            File path = Environment.getExternalStorageDirectory();
            StatFs statfs = new StatFs(path.getPath());

            double result = statfs.getAvailableBlocks() * statfs.getBlockSize();
            return result / 1024;
        }
        return -1;
    }

    /**
     * 是否存在SdCard
     *
     * @return
     */
    public static boolean isExistSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取电量值，0-100的某个数值
     *
     * @return
     */
    public static int getBatteryInfo(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int batteryNum = (int) ((level * 100f) / scale);
        return batteryNum;
    }

    private static final String PACKNAME_QIHOO360 = "com.qihoo360.mobilesafe";
    private static final String PACKNAME_BAIDU = "com.baidu.mobileguardian";
    private static final String PACKNAME_TECENT = "com.tencent.qqpimsecure";
    private static final String PACKNAME_JINSHAN = "com.ijinshan.duba";


    public static boolean isWifiOn(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return mWifiManager.isWifiEnabled();
    }

    public static String getSimOperatorInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String oper = telephonyManager.getSimOperator();

        if (oper == null) {
            return "";
        }

        if (oper.startsWith("46000") || oper.startsWith("46002")
                || oper.startsWith("46004") || oper.startsWith("46007")) {
            //中国移动
            return "中国移动";
        } else if (oper.startsWith("46001") || oper.startsWith("46006")) {
            //中国联通
            return "中国联通";
        } else if (oper.startsWith("46003") || oper.startsWith("46005")) {
            //中国电信
            return "中国电信";
        }

        //error
        return "";
    }

    public static String getPhoneLocale(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage();
    }
}
