package com.ray.baseandroid.util;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leixing
 * on 2016-11-10.
 * Email : leixing@hecom.cn
 */

public class StringUtil {
    private StringUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 字符串解析成int，格式错误则返回默认值
     *
     * @param s            待解析字符串
     * @param defaultValue 格式错误的返回默认值
     * @return
     */
    public static int toInt(String s, int defaultValue) {
        int value = defaultValue;
        if (TextUtils.isEmpty(s)) {
            return value;
        }
        String number = s.trim();

        try {
            value = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            try {
                value = (int) Double.parseDouble(number);
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
        }

        return value;
    }

    /**
     * 字符串解析成int，格式错误则返回0
     *
     * @param s 待解析字符串
     * @return
     */
    public static int toInt(String s) {
        return toInt(s, 0);
    }

    /**
     * 字符串解析成long，格式错误则返回默认值
     *
     * @param s            待解析字符串
     * @param defaultValue 格式错误的返回默认值
     * @return
     */
    public static long toLong(String s, long defaultValue) {
        long value = defaultValue;
        if (TextUtils.isEmpty(s)) {
            return value;
        }
        String number = s.trim();

        try {
            value = Long.parseLong(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            try {
                value = (long) Double.parseDouble(number);
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
        }

        return value;
    }

    /**
     * 字符串解析成long，格式错误则返回0
     *
     * @param s 待解析字符串
     * @return
     */
    public static long toLong(String s) {
        return toLong(s, 0);
    }

    /**
     * 字符串解析成float，格式错误返回默认值
     *
     * @param s            待解析字符串
     * @param defaultValue 格式错误的返回默认值
     * @return
     */
    public static float toFloat(String s, float defaultValue) {
        float value = defaultValue;
        if (TextUtils.isEmpty(s)) {
            return value;
        }
        String number = s.trim();

        try {
            value = Float.parseFloat(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            try {
                value = (float) Double.parseDouble(number);
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
        }

        return value;
    }

    /**
     * 字符串解析成float，格式错误返回0
     *
     * @param s 待解析字符串
     * @return
     */
    public static float toFloat(String s) {
        return toFloat(s, 0);
    }

    /**
     * 字符串解析成double，格式错误转换为默认值
     *
     * @param s            待解析字符串
     * @param defaultValue 格式错误的返回默认值
     * @return
     */
    public static double toDouble(String s, double defaultValue) {
        double value = defaultValue;
        if (TextUtils.isEmpty(s)) {
            return value;
        }
        String number = s.trim();

        try {
            value = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 字符串解析成double，格式错误返回0
     *
     * @param s 待解析字符串
     * @return
     */
    public static double toDouble(String s) {
        return toDouble(s, 0);
    }

    /**
     * 将list的数据转换为单个的String
     * 如： {"aaa", "bbb", "ccc"} -> "aaa, bbb, ccc"
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String fromList(List<T> list) {
        return fromList(list, ",");
    }

    /**
     * 将list的数据转换为单个的String
     * 如： {"aaa", "bbb", "ccc"} -> "aaa, bbb, ccc"
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String fromList(List<T> list, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        if (CollectionUtil.isEmpty(list)) {
            return stringBuilder.toString();
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (t == null) {
                continue;
            }

            stringBuilder.append(t.toString());
            if (i < size - 1) {
                stringBuilder.append(separator);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 将一定格式的String转换为对象列表
     * 如："aaa, bbb, ccc" ->{"aaa", "bbb", "ccc"}
     *
     * @param listString 符合一定格式的字符串
     * @param separator  列表字符串的分隔符
     * @param builder    对象的构造器
     * @param <T>        返回对象列表的的对象的类型
     * @return
     */
    public static <T> List<T> toList(String listString, String separator, ObjectBuilder<T> builder) {
        List<T> list = new ArrayList<>();

        if (TextUtils.isEmpty(listString)) {
            return list;
        }

        if (TextUtils.isEmpty(separator)) {
            T t = builder.build(listString);
            if (t != null) {
                list.add(t);
            }
            return list;
        }

        String[] split = listString.split(separator);
        if (CollectionUtil.isEmpty(split)) {
            return list;
        }

        for (String s : split) {
            if (TextUtils.isEmpty(s)) {
                continue;
            }

            T t = builder.build(s);
            if (t == null) {
                continue;
            }

            list.add(t);
        }

        return list;
    }

    public static <T> List<T> toList(String listString, ObjectBuilder<T> builder) {
        return toList(listString, ",", builder);
    }

    public static List<String> toList(String listString) {
        ObjectBuilder<String> builder = new ObjectBuilder<String>() {
            @Override
            public String build(String s) {
                return s;
            }
        };
        return toList(listString, ",", builder);
    }

    public interface ObjectBuilder<T> {
        T build(String s);
    }


    /**
     * 将字符串转换为SpannalbeString，高亮关键字
     *
     * @param key    关键字
     * @param target 待转化字符串
     * @return SpannableString字符串
     */
    public static SpannableString toSpannableString(String key, @NonNull String target) {
        SpannableString spanStr;
        if (key != null && !key.equals("")) {
            spanStr = new SpannableString(target);
            try {
                Pattern pattern = Pattern.compile(key);
                Matcher matcher = pattern.matcher(spanStr);
                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    spanStr.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (Exception e) {

            }
        } else {
            spanStr = new SpannableString(target);
        }
        return spanStr;
    }

    /**
     * 判断两个字符串的内容是否相同
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean contentEquals(String s1, String s2) {
        return nullToEmpty(s1).trim().equals(nullToEmpty(s2).trim());
    }

    /**
     * 将null字符串转换为内容为空的字符串
     *
     * @param s 待转换的字符串
     * @return 如果输入的胃null，则返回"",否则返回原字符串
     */
    public static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
