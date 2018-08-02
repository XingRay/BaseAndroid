package com.ray.baseandroid.text;

import java.util.regex.Pattern;

/**
 * @@author      : leixing
 * @@date        : 2017-08-08
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RegexUtil {
    /**
     * 移动电话
     */
    public static Pattern MOBILE_PHONE_PATTERN = Pattern.compile(
            "(?<![0-9])" // 左边不能有数字
                    + "((0|\\+86|0086)\\s?)?" // 0 +86 0086
                    + "1[3|4|5|7|8|][0-9]-?[0-9]{4}-?[0-9]{4}" // 186-1234-5678
                    + "(?![0-9])"); // 右边不能有数字

    /**
     * 固定电话
     */
    public static Pattern FIXED_PHONE_PATTERN = Pattern.compile(
            "(?<![0-9])" // 左边不能有数字
                    + "(\\(?0[0-9]{2,3}\\)?-?)?" // 区号
                    + "[0-9]{7,8}"// 市内号码
                    + "(?![0-9])"); // 右边不能有数字

    /**
     * http(s) URL
     */
    public static Pattern HTTP_PATTERN = Pattern.compile(
            "http(s)?://" + "[-0-9a-zA-Z.]+" // 主机名
                    + "(:\\d+)?" // 端口
                    + "(" // 可选的路径和查询 - 开始
                    + "/[-\\w$.+!*'(),%;:@&=]*" // 第一层路径
                    + "(/[-\\w$.+!*'(),%;:@&=]*)*" // 可选的其他层路径
                    + "(\\?[-\\w$.+!*'(),%;:@&=]*)?" // 可选的查询字符串
                    + ")?"); // 可选的路径和查询 - 结束

    /**
     * 邮箱
     */
    public static Pattern GENERAL_EMAIL_PATTERN = Pattern.compile(
            "[0-9a-zA-Z][-._0-9a-zA-Z]{0,63}" // 用户名
                    + "@"
                    + "([0-9a-zA-Z][-0-9a-zA-Z]{0,62}\\.)+" // 域名部分
                    + "[a-zA-Z]{2,3}"); // 顶级域名

}
