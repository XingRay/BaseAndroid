package com.ray.baseandroid.util;

import java.util.Random;

/**
 * Author      : leixing
 * Date        : 2016-12-27
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RandomUtil {
    private RandomUtil() {
        throw new UnsupportedOperationException();
    }

    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            synchronized (RandomUtil.class) {
                random = new Random(TimeUtil.getCurrentTimeStamp());
            }
        }

        return random;
    }

    /**
     * 返回随机整数
     *
     * @return
     */
    public static int getRandomInt() {
        return getRandom().nextInt();
    }

    /**
     * 返回[min, max)内的随机整数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max) {
        int trueMin = min <= max ? min : max;
        int trueMax = max >= min ? max : min;

        return trueMin + getRandom().nextInt(trueMax - trueMin);
    }

    private static final char[] wordChars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    /**
     * 返回由length个字符(a-z A-Z 0-9)组成的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        if (length <= 0) {
            return "";
        }


        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = getRandom().nextInt(wordChars.length);
            stringBuilder.append(wordChars[index]);
        }

        return stringBuilder.toString();
    }

    /**
     * 获取随机的布尔值
     *
     * @return
     */
    public static boolean getRandomBoolean() {
        return getRandom().nextBoolean();
    }

    /**
     * 返回随机的布尔值，返回的true和false按照指定的比率概率分布
     *
     * @param trueRatio  返回真值的比例
     * @param falseRatio 返回假值的比例
     * @return
     */
    public static boolean getRandomBoolean(int trueRatio, int falseRatio) {
        int value = getRandom().nextInt(trueRatio + falseRatio);
        return value < trueRatio;
    }
}