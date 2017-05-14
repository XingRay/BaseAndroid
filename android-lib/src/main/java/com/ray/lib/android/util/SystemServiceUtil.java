package com.ray.lib.android.util;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * use for get system service
 * 用于获取系统的服务
 */
public class SystemServiceUtil {
    private static WindowManager windowManager = null;
    private static TelephonyManager telephonyManager = null;
    private static DevicePolicyManager devicePolicyManager = null;
    private static ActivityManager activityManager = null;
    private static LayoutInflater layoutInflater = null;
    private static PowerManager powerManager = null;
    private static AlarmManager alarmManager = null;
    private static NotificationManager notificationManager = null;
    private static KeyguardManager keyguardManager = null;
    private static LocationManager locationManager = null;
    private static SearchManager searchManager = null;
    private static SensorManager sensorManager = null;
    private static StorageManager storageManager = null;
    private static Vibrator vibrator = null;
    private static ConnectivityManager connectivityManager = null;
    private static WifiManager wifiManager = null;
    private static AudioManager audioManager = null;
    private static MediaRouter mediaRouter = null;
    private static SubscriptionManager subscriptionManager = null;
    private static CarrierConfigManager carrierConfigManager = null;
    private static InputMethodManager inputMethodManager = null;
    private static UiModeManager uiModeManager = null;
    private static DownloadManager downloadManager = null;
    private static BatteryManager batteryManager = null;
    private static JobScheduler jobScheduler = null;
    private static NetworkStatsManager networkStatsManager = null;


    private SystemServiceUtil() {
    }

    /**
     * @param context
     * @return
     */
    public static synchronized WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        return windowManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized LayoutInflater getLayoutInflater(Context context) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        return layoutInflater;
    }

    /**
     * @param context 上下文
     * @return ActivityManager
     */
    public static synchronized ActivityManager getActivityManager(Context context) {
        if (activityManager == null) {
            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }

        return activityManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized PowerManager getPowerManager(Context context) {
        if (powerManager == null) {
            powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        }

        return powerManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized AlarmManager getAlarmManager(Context context) {
        if (alarmManager == null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }

        return alarmManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized NotificationManager getNotificationManager(Context context) {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized KeyguardManager getKeyguardManager(Context context) {
        if (keyguardManager == null) {
            keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        }

        return keyguardManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized LocationManager getLocationManager(Context context) {
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        return locationManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized SearchManager getSearchManager(Context context) {
        if (searchManager == null) {
            searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        }

        return searchManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized SensorManager getSensorManager(Context context) {
        if (sensorManager == null) {
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        }

        return sensorManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized StorageManager getStorageManager(Context context) {
        if (storageManager == null) {
            storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        }

        return storageManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized Vibrator getVibrator(Context context) {
        if (vibrator == null) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }

        return vibrator;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized ConnectivityManager getConnectivityManager(Context context) {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        return connectivityManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized WifiManager getWifiManager(Context context) {
        if (wifiManager == null) {
            wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }

        return wifiManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized AudioManager getAudioManager(Context context) {
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }

        return audioManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized MediaRouter getMediaRouter(Context context) {
        if (mediaRouter == null) {
            mediaRouter = (MediaRouter) context.getSystemService(Context.MEDIA_ROUTER_SERVICE);
        }

        return mediaRouter;
    }

    /**
     * @param context 上下文
     * @return TelephonyManager
     */
    public static synchronized TelephonyManager getTelephonyManager(Context context) {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }

        return telephonyManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized SubscriptionManager getSubscriptionManager(Context context) {
        if (subscriptionManager == null) {
            subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        }

        return subscriptionManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized CarrierConfigManager getCarrierConfigManager(Context context) {
        if (carrierConfigManager == null) {
            carrierConfigManager = (CarrierConfigManager) context.getSystemService(Context.CARRIER_CONFIG_SERVICE);
        }

        return carrierConfigManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized InputMethodManager getInputMethodManager(Context context) {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        }

        return inputMethodManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized UiModeManager getUiModeManager(Context context) {
        if (uiModeManager == null) {
            uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
        }

        return uiModeManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized DownloadManager getDownloadManager(Context context) {
        if (downloadManager == null) {
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        }

        return downloadManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized BatteryManager getBatteryManager(Context context) {
        if (batteryManager == null) {
            batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        }

        return batteryManager;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized JobScheduler getJobScheduler(Context context) {
        if (jobScheduler == null) {
            jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        }

        return jobScheduler;
    }

    /**
     * @param context
     * @return
     */
    public static synchronized NetworkStatsManager getNetworkStatsManager(Context context) {
        if (networkStatsManager == null) {
            networkStatsManager = (NetworkStatsManager) context.getSystemService(Context.NETWORK_STATS_SERVICE);
        }

        return networkStatsManager;
    }

    /**
     * @param context 上下文
     * @return devicePolicyManager
     */
    public static synchronized DevicePolicyManager getDevicePolicyManager(Context context) {
        if (devicePolicyManager == null) {
            devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        }
        return devicePolicyManager;
    }
}
