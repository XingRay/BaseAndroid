package com.ray.lib.java.util;

import java.io.PrintStream;
import java.util.List;

/**
 * @author      : LeiXing
 * @date        : 2016/11/19
 * Email       : leixing1012@qq.com
 * Version     : 0.01
 * Description : xxx
 */
public class IOUtil {
    private IOUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T> void print(PrintStream printStream, List<T> list) {
        if (list == null || list.isEmpty()) {
            printStream.println("[]");
            return;
        }

        printStream.println("[");
        for (T t : list) {
            printStream.println("\t" + t);
        }
        printStream.println("]");
    }
}
