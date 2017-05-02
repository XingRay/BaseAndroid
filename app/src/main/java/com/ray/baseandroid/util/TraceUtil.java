package com.ray.baseandroid.util;

import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TraceUtil {

    private static boolean logEnable = true;

    public static void enableLog() {
        logEnable = true;
    }

    public static void disableLog() {
        logEnable = false;
    }

    public static void e(String tag, Object msg) {
        if (logEnable) {
            // msg.toString() may cause NullPointerException
            Log.e(tag, "" + msg);
        }
    }

    public static void w(String tag, Object msg) {
        if (logEnable) {
            Log.w(tag, "" + msg);
        }
    }

    public static void i(String tag, Object msg) {
        if (logEnable) {
            Log.i(tag, "" + msg);
        }
    }

    public static void d(String tag, Object msg) {
        if (logEnable) {
            Log.d(tag, "" + msg);
        }
    }

    public static void v(String tag, Object msg) {
        if (logEnable) {
            Log.v(tag, "" + msg);
        }
    }

    public static void e(Object msg) {
        if (logEnable) {
            String tag = getSimpleClassName();
            Log.e(tag, "" + msg);
        }
    }

    public static void w(Object msg) {
        if (logEnable) {
            String tag = getSimpleClassName();
            Log.w(tag, "" + msg);
        }
    }

    public static void i(Object msg) {
        if (logEnable) {
            String tag = getSimpleClassName();
            Log.i(tag, "" + msg);
        }
    }

    public static void d(Object msg) {
        if (logEnable) {
            String tag = getSimpleClassName();
            Log.d(tag, "" + msg);
        }
    }

    public static void v(Object msg) {
        if (logEnable) {
            String tag = getSimpleClassName();
            Log.v(tag, "" + msg);
        }
    }

    private static String getSimpleClassName() {
        String simpleClassName = null;
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        if (stackTraceElement != null) {
            simpleClassName = stackTraceElement.getClass().getSimpleName();
        }
        return simpleClassName == null ? "" : simpleClassName;
    }

    public static String _FILE_() {
        return new Throwable().getStackTrace()[1].getFileName();
    }

    public static String _CLASS_() {
        return new Throwable().getStackTrace()[1].getClassName();
    }

    public static String _SIMPLE_CLASS_() {
        return new Throwable().getStackTrace()[1].getClass().getSimpleName();
    }

    public static String _FUNC_() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }

    public static int _LINE_() {
        return new Throwable().getStackTrace()[1].getLineNumber();
    }

    public static String _TIME_() {
        return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.CHINA).format(new Date());
    }

    public static void enterFunction() {
        d(new Throwable().getStackTrace()[1].getFileName(), "enter " + new Throwable().getStackTrace()[1].getMethodName());
    }

    public static void exitFunction() {
        d(new Throwable().getStackTrace()[1].getFileName(), "exit " + new Throwable().getStackTrace()[1].getMethodName());
    }

    public static void showExecutedLine() {
        d(new Throwable().getStackTrace()[1].getFileName(), "line : " + new Throwable().getStackTrace()[1].getLineNumber() + "executed");
    }

    public static void log(@Nullable Object obj) {
        String msg = "" + obj;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String className = stackTraceElement.getClassName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();

        d("at " + className + "(" + fileName + ":" + lineNumber + ")" + " @ " + methodName + "\n###############\n" + msg + "\n---------------");
    }

    public static void log() {
        String msg = "";
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String className = stackTraceElement.getClassName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();

        d("at " + className + "(" + fileName + ":" + lineNumber + ")" + " @ " + methodName + "\n###############\n" + msg + "\n---------------");
    }

    private static void printStackTrace(int startLevel, int maxLevel) {
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        //防止溢出
        maxLevel = maxLevel < Integer.MAX_VALUE - startLevel ? maxLevel : Integer.MAX_VALUE - startLevel;
        int level = stackTrace.length < maxLevel + startLevel ? stackTrace.length : maxLevel + startLevel;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = startLevel; i < level; i++) {
            StackTraceElement element = stackTrace[i];
            String className = element.getClassName();
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            String methodName = element.getMethodName();

            stringBuilder
                    .append("### at ")
                    .append(className)
                    .append("(")
                    .append(fileName)
                    .append(":")
                    .append(lineNumber)
                    .append(")")
                    .append(" @ ")
                    .append(methodName)
                    .append("\n");
        }

        d(stringBuilder.toString());
    }

    public static void printStackTrace() {
        printStackTrace(2, Integer.MAX_VALUE);
    }

    public static void printStackTrace(int maxLevel) {
        printStackTrace(2, maxLevel);
    }
}