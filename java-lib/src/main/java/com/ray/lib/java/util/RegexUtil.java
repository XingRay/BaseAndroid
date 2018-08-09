package com.ray.lib.java.util;

import java.util.regex.Pattern;

/**
 * Created by leixing on 2016/12/17 01:58.
 * Email: leixing1012@qq.com
 */

public class RegexUtil {
    /**
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     */
    public static final String REGEX_IDCARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     */
    public static final String REGEX_IDCARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    /**
     */
    public static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    /**
     */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     */
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 移动电话
     */
    public static Pattern MOBILE_PHONE_PATTERN = Pattern.compile(
            "(?<![0-9])"
                    + "((0|\\+86|0086)\\s?)?"
                    + "1[3|4|5|7|8|][0-9]-?[0-9]{4}-?[0-9]{4}"
                    + "(?![0-9])");

    /**
     * 固定电话
     */
    public static Pattern FIXED_PHONE_PATTERN = Pattern.compile(
            "(?<![0-9])"
                    + "(\\(?0[0-9]{2,3}\\)?-?)?"
                    + "[0-9]{7,8}"
                    + "(?![0-9])");

    /**
     * http(s) URL
     */
    public static Pattern HTTP_PATTERN = Pattern.compile(
            "http(s)?://" + "[-0-9a-zA-Z.]+"
                    + "(:\\d+)?"
                    + "("
                    + "/[-\\w$.+!*'(),%;:@&=]*"
                    + "(/[-\\w$.+!*'(),%;:@&=]*)*"
                    + "(\\?[-\\w$.+!*'(),%;:@&=]*)?"
                    + ")?");

    /**
     * 邮箱
     */
    public static Pattern GENERAL_EMAIL_PATTERN = Pattern.compile(
            "[0-9a-zA-Z][-._0-9a-zA-Z]{0,63}"
                    + "@"
                    + "([0-9a-zA-Z][-0-9a-zA-Z]{0,62}\\.)+"
                    + "[a-zA-Z]{2,3}");

    private RegexUtil() {
        throw new UnsupportedOperationException();
    }
}
