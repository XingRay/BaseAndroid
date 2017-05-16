package com.ray.lib.java.util;

import java.util.Random;

/**
 * Java字符串的处理
 * Created by leixing on 2016/4/26.
 */
public class StringUtil {

    /**
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return "";
        }

        char[] charArray = str.toCharArray();

        if (charArray[0] >= 'a' && charArray[0] <= 'z') {
            charArray[0] -= 'a' - 'A';
        }

        return new String(charArray);
    }

    /**
     *
     * @param str
     * @return
     */
    public static String deleteLastChar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        char[] original = str.toCharArray();
        int newLength = original.length - 1;
        if (newLength <= 0) {
            return "";
        }

        char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0, newLength);

        return new String(copy);
    }

    /**
     * 判断输入的字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     */
    public static final char[] wordChars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    /**
     * 获取随机的字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        if (length <= 0) {
            return "";
        }

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(wordChars.length);
            stringBuilder.append(wordChars[index]);
        }

        return stringBuilder.toString();
    }
}
