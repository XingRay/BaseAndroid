package com.ray.lib.java.util.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author : leixing
 * @date : 2017-04-21
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TimeUtil {

    private TimeUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.</p>
     * 格式的意义如下： 日期和时间模式 <br>
     * <p>日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
     * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
     * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * </p>
     * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
     * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows pattern letters, date/time component,
     * presentation, and examples.">
     * <tr>
     * <th align="left">字母</th>
     * <th align="left">日期或时间元素</th>
     * <th align="left">表示</th>
     * <th align="left">示例</th>
     * </tr>
     * <tr>
     * <td><code>G</code></td>
     * <td>Era 标志符</td>
     * <td>Text</td>
     * <td><code>AD</code></td>
     * </tr>
     * <tr>
     * <td><code>y</code> </td>
     * <td>年 </td>
     * <td>Year </td>
     * <td><code>1996</code>; <code>96</code> </td>
     * </tr>
     * <tr>
     * <td><code>M</code> </td>
     * <td>年中的月份 </td>
     * <td>Month </td>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code> </td>
     * </tr>
     * <tr>
     * <td><code>w</code> </td>
     * <td>年中的周数 </td>
     * <td>Number </td>
     * <td><code>27</code> </td>
     * </tr>
     * <tr>
     * <td><code>W</code> </td>
     * <td>月份中的周数 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>D</code> </td>
     * <td>年中的天数 </td>
     * <td>Number </td>
     * <td><code>189</code> </td>
     * </tr>
     * <tr>
     * <td><code>d</code> </td>
     * <td>月份中的天数 </td>
     * <td>Number </td>
     * <td><code>10</code> </td>
     * </tr>
     * <tr>
     * <td><code>F</code> </td>
     * <td>月份中的星期 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>E</code> </td>
     * <td>星期中的天数 </td>
     * <td>Text </td>
     * <td><code>Tuesday</code>; <code>Tue</code> </td>
     * </tr>
     * <tr>
     * <td><code>a</code> </td>
     * <td>Am/pm 标记 </td>
     * <td>Text </td>
     * <td><code>PM</code> </td>
     * </tr>
     * <tr>
     * <td><code>H</code> </td>
     * <td>一天中的小时数（0-23） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>k</code> </td>
     * <td>一天中的小时数（1-24） </td>
     * <td>Number </td>
     * <td><code>24</code> </td>
     * </tr>
     * <tr>
     * <td><code>K</code> </td>
     * <td>am/pm 中的小时数（0-11） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>h</code> </td>
     * <td>am/pm 中的小时数（1-12） </td>
     * <td>Number </td>
     * <td><code>12</code> </td>
     * </tr>
     * <tr>
     * <td><code>m</code> </td>
     * <td>小时中的分钟数 </td>
     * <td>Number </td>
     * <td><code>30</code> </td>
     * </tr>
     * <tr>
     * <td><code>s</code> </td>
     * <td>分钟中的秒数 </td>
     * <td>Number </td>
     * <td><code>55</code> </td>
     * </tr>
     * <tr>
     * <td><code>S</code> </td>
     * <td>毫秒数 </td>
     * <td>Number </td>
     * <td><code>978</code> </td>
     * </tr>
     * <tr>
     * <td><code>z</code> </td>
     * <td>时区 </td>
     * <td>General time zone </td>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code> </td>
     * </tr>
     * <tr>
     * <td><code>Z</code> </td>
     * <td>时区 </td>
     * <td>RFC 822 time zone </td>
     * <td><code>-0800</code> </td>
     * </tr>
     * </table>
     * <pre>
     *                          HH:mm    15:44
     *                         h:mm a    3:44 下午
     *                        HH:mm z    15:44 CST
     *                        HH:mm Z    15:44 +0800
     *                     HH:mm zzzz    15:44 中国标准时间
     *                       HH:mm:ss    15:44:40
     *                     yyyy-MM-dd    2016-08-12
     *               yyyy-MM-dd HH:mm    2016-08-12 15:44
     *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                         K:mm a    3:44 下午
     *               EEE, MMM d, ''yy    星期五, 八月 12, '16
     *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                  yyMMddHHmmssZ    160812154440+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     */
    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param milliseconds 毫秒时间戳
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param time
     * @return
     */
    public static Date shortString2Date(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return string2Date(time, formatter);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     */
    private static long milliseconds2Unit(long milliseconds, TimeUnit unit) {
//        switch (unit) {
//            case DAY_MILLS:
//                return milliseconds / MSEC;
//            case SEC:
//                return milliseconds / SEC;
//            case MIN:
//                return milliseconds / MIN;
//            case HOUR:
//                return milliseconds / HOUR;
//            case DAY:
//                return milliseconds / DAY;
//        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time0 Date类型时间1
     * @param time1 Date类型时间2
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time0, Date time1, TimeUnit unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, TimeUnit unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, TimeUnit unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, TimeUnit unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static final long DAY_MILLS = 1000 * 60 * 60 * 24;

    public interface TimeUnit {
        int MILLIS = 0;
        int SECOND = 1;
        int MINUTE = 2;
        int HOUR = 3;
        int DAY = 4;
    }

    public interface TimeInterval {
        long MILLIS = 1;
        long SECOND = MILLIS * 1000;
        long MINUTE = SECOND * 60;
        long HOUR = MINUTE * 60;
        long DAY = HOUR * 24;
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
     * 格式化日期， 格式为 11.22
     *
     * @param timestamp 时间戳
     * @return 格式化日期
     */
    public static String getDateDotSytleMM_DD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM.dd", Locale.getDefault());
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

    public static String getDateYYYYMMDD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYY_MM_DD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateDotSytleYYYY_MM_DD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateYYYYDotMMDotDD(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateTimeMMDDHHMM(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateTimeYYYY_MM_DD_HH_MM(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateTimeYYYY_MM_DD_HH_MM_SS(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateTimeYYYY_MM_DD_WEEK_HH_MM(long timestamp) {
        return getFormattedTime(timestamp, "yyyy-MM-dd E HH:mm");
    }

    public static String getDateTimeYYYY_MM_DD_WEEK(long timestamp) {
        return getFormattedTime(timestamp, "yyyy-MM-dd E");
    }

    /**
     * 返回格式：2018-01-12-155909
     *
     * @param timestamp
     * @return
     */
    public static String getDateTimeYYYY_MM_DD_HHMMSS(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getTimeHH_MM_SS(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    public static String getDateMM_DD_WEEK(long timestamp) {
        return getFormattedTime(timestamp, "MM-dd E");
    }

    public static String getDateWEEK(long timestamp) {
        return getFormattedTime(timestamp, "E");
    }

    public static String getFormattedTime(long timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(new Date(timestamp));
    }

    /**
     * 获得当前的日期号码 返回格式:01
     */
    public static String getCurrentDay() {
        return String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获得当前的月份 返回格式:02
     */
    public static String getCurrentMonth() {
        return String.format("%02d", Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    /**
     * 获得当前的年份 返回格式:2017
     */
    public static String getCurrentYear() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 获得当前的时间是星期几 返回格式:中文——星期日,英文——Sun
     */
    public static String getCurrentWeek() {
        String mWeek = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
//        if ("1".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqiri);
//        } else if ("2".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqiyi);
//        } else if ("3".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqier);
//        } else if ("4".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqisan);
//        } else if ("5".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqisi);
//        } else if ("6".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqiwu);
//        } else if ("7".equals(mWeek)) {
//            mWeek = ResUtil.getStringRes(R.string.xingqiliu);
//        }
        return mWeek;
    }

    /**
     * 判断一个时刻值是否是今天
     */
//    public static boolean isToday(long time) {
//        return TextUtils.equals(getDateYYYY_MM_DD(time), getDateYYYY_MM_DD(getCurrentTimeStamp()));
//    }


    /**
     * 获取今天最后的时间，单位为毫秒
     *
     * @return 今天最后的时间
     */
    public static long getTodayEndMills() {
        Calendar calendar = Calendar.getInstance();
        return getEndMillsOfDay(calendar);
    }

    /**
     * 获取今天最开始时间，单位为毫秒
     *
     * @return 今天最后的时间
     */
    public static long getTodayStartMills() {
        Calendar calendar = Calendar.getInstance();
        return getStartMillsOfDay(calendar);
    }


    public static long getStartMillsOfDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    public static long getEndMillsOfDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTimeInMillis();
    }

    public static boolean isSameDay(long time1, long time2) {
        if (Math.abs(time1 - time2) >= DAY_MILLS) {
            return false;
        }

        int rawOffset = TimeZone.getDefault().getRawOffset();

        return ((time1 + rawOffset) / DAY_MILLS) == ((time2 + rawOffset) / DAY_MILLS);
    }

    public static boolean isSameWeek(long timeStamp1, long timeStamp2) {
        if (Math.abs(timeStamp1 - timeStamp2) >= 7 * DAY_MILLS) {
            return false;
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(timeStamp1);
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(timeStamp2);
        calendar2.setFirstDayOfWeek(Calendar.MONDAY);

        return calendar1.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR);
    }

    public static boolean isSameYear(long timeStamp1, long timeStamp2) {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(timeStamp1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(timeStamp2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 起止时间描述
     *
     * @param start
     * @param end
     * @return 起止时间都在本年时返回12.12-12.21 否则返回 2000.12.12-2000.12.21
     */
    public static String getTimeIntervalDesc(long start, long end) {
        if (isSameYear(System.currentTimeMillis(), end) && isSameYear(System.currentTimeMillis(), start)) {
            return getDateDotSytleMM_DD(start) + "-" + getDateDotSytleMM_DD(end);
        } else {
            return getDateDotSytleYYYY_MM_DD(start) + "-" + getDateDotSytleYYYY_MM_DD(end);
        }
    }

    /**
     */
    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
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
