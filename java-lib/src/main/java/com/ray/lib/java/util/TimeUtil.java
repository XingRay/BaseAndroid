package com.ray.lib.java.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Author      : leixing
 * Date        : 2017-04-21
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TimeUtil {

    private TimeUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     */
    public static long getCurrentTimeStamp() {
        return new Date().getTime();
    }

    /**
     *
     */
    public static long timeInterval(long time1, long time2, int unit) {
        if (!isValidTimeUnit(unit)) {
            throw new IllegalArgumentException("illegal time unit");
        }

        long unitTimeInterval = 1;

        switch (unit) {
            case TimeUnit.MILLIS:
                unitTimeInterval = TimeInterval.MILLIS;
                break;
            case TimeUnit.SECOND:
                unitTimeInterval = TimeInterval.SECOND;
                break;
            case TimeUnit.MINUTE:
                unitTimeInterval = TimeInterval.MINUTE;
                break;
            case TimeUnit.HOUR:
                unitTimeInterval = TimeInterval.HOUR;
                break;
            case TimeUnit.DAY:
                unitTimeInterval = TimeInterval.DAY;
                break;
            default:
        }

        return (time1 - time2) / unitTimeInterval;
    }

    private static boolean isValidTimeUnit(int unit) {
        return (unit == TimeUnit.MILLIS
                || unit == TimeUnit.SECOND
                || unit == TimeUnit.MINUTE
                || unit == TimeUnit.HOUR
                || unit == TimeUnit.DAY);
    }

    /**
     */
    public static long getDays(long timeStamp) {
        return timeStamp / TimeInterval.DAY;
    }

    /**
     */
    public static int getRawMonth(long timeStamp) {
        Calendar now = Calendar.getInstance(Locale.getDefault());
        now.setTimeInMillis(timeStamp);
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);

        Calendar from = Calendar.getInstance(Locale.getDefault());
        from.setTimeInMillis(0);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);

        return (nowYear - fromYear) * 12 + (nowMonth - fromMonth);
    }

    /**
     *
     */
    public static String getTimeHHmm(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    /**
     */
    public static String getDateMMDD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    /**
     */
    public static String getDateMM_DD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYYMM(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYYMMDD(Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYY_MM_DD(Long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public interface TimeUnit {
        int MILLIS = 0;
        int SECOND = 1;
        int MINUTE = 2;
        int HOUR = 3;
        int DAY = 4;
    }

    interface TimeInterval {
        long MILLIS = 1;
        long SECOND = MILLIS * 1000;
        long MINUTE = SECOND * 60;
        long HOUR = MINUTE * 60;
        long DAY = HOUR * 24;
    }

}
