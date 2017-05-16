package com.ray.lib.java.util;

/**
 * Created by leixi on 2016/10/23.
 */
public class TextUtil {
    private TextUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }
}
