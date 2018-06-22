package com.ray.lib.java.util;

/**
 * use for process Url
 * 用于操作Url数据
 */
public class UrlUtil {

    /**
     *
     * @param str
     * @return
     */
    public static boolean isStartsWithIP(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }

        String startByIP = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}.*";
        return str.matches(startByIP);
    }

    /**
     * 将一段字符串转换为完整的Url, 例如
     * 192.168.0.1 -> http://192.168.0.1
     * google.com -> http://www.google.com
     * https://www.facebook.com  -> https://www.facebook.com
     *
     * @param str
     * @return
     */
    public static String convertToURL(String str, boolean useSSL) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (!(str.startsWith("http://") || str.startsWith("https://"))) {
            if (useSSL) {
                stringBuilder.append("https://");
            } else {
                stringBuilder.append("http://");
            }
        }

        if (!(str.startsWith("www.") || isStartsWithIP(str))) {
            stringBuilder.append("www.");
        }

        stringBuilder.append(str);

        return stringBuilder.toString();
    }
}
