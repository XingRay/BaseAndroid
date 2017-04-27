package com.ray.baseandroid.util;


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
 * Description : 时间相关的工具类
 */

public class TimeUtil {

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


    private TimeUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取当前的时间戳 单位是毫秒
     *
     * @return
     */
    public static long getCurrentTimeStamp() {
        return new Date().getTime();
    }

    /**
     * 获取两个时间戳之间的差
     *
     * @param time1 时间1
     * @param time2 时间2
     * @param unit  返回值的单位
     * @return 先计算两个时间戳之间的差值再转换为相应的单位的值
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
     * 获取时间戳对应的天数
     *
     * @param timeStamp
     * @return
     */
    public static long getDays(long timeStamp) {
        return timeStamp / TimeInterval.DAY;
    }

    /**
     * 获取时间戳对应的月数, 从GMT1970-01-01 00:00:00开始
     *
     * @param timeStamp
     * @return
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
     * 获取格式化时间， 格式为：22:15
     *
     * @param timestamp 时间戳
     * @return 格式化时间
     */
    public static String getTimeHHmm(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    /**
     * 格式化日期， 格式为 11月22日
     *
     * @param timestamp 时间戳
     * @return 格式化日期
     */
    public static String getDateMMDD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    /**
     * 格式化日期， 格式为 11-22
     *
     * @param timestamp 时间戳
     * @return 格式化日期
     */
    public static String getDateMM_DD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYYMM(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月", Locale.getDefault());
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

}
