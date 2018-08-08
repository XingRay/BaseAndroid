package com.ray.lib.java.util;

import com.ray.lib.java.util.time.TimeUtil;

import java.util.Random;

/**
 * @author      : leixing
 * @date        : 2016-12-27
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RandomUtil {
    private static final char[] wordChars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static Random random;

    private RandomUtil() {
        throw new UnsupportedOperationException();
    }

    public static Random getRandom() {
        if (random == null) {
            synchronized (RandomUtil.class) {
                random = new Random(TimeUtil.getCurrentTimeStamp());
            }
        }

        return random;
    }

    /**
     */
    public static int getRandomInt() {
        return getRandom().nextInt();
    }

    /**
     */
    public static int getRandomInt(int a, int b) {
        if (a == b) {
            return a;
        }

        int min = 0;
        int max = 0;

        if (a > b) {
            max = a;
            min = b;
        } else {
            max = b;
            min = a;
        }

        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    public static int getRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max + 1);
    }

    /**
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
     */
    public static boolean getRandomBoolean() {
        return getRandom().nextBoolean();
    }

    /**
     */
    public static boolean getRandomBoolean(int trueRatio, int falseRatio) {
        int value = getRandom().nextInt(trueRatio + falseRatio);
        return value < trueRatio;
    }
}