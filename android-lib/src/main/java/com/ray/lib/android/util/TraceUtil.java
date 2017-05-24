package com.ray.lib.android.util;

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
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        printStackTraceElement(obj, stackTraceElement);
    }

    public static void log() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        printStackTraceElement(stackTraceElement);
    }


    private static void printStackTraceElement(StackTraceElement stackTraceElement) {
        printStackTraceElement(null, stackTraceElement);
    }

    private static void printStackTraceElement(Object obj, StackTraceElement stackTraceElement) {
        String msg = obj == null ? "" : obj.toString();
        String className = stackTraceElement.getClassName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();

        d(_TIME_() + " at " + className + "(" + fileName + ":" + lineNumber + ")"
                + "\n @ " + methodName
                + "\n###############\n"
                + msg
                + "\n---------------");
    }

    public static void printStackTrace() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        printStackTrace(stackTraceElements, 1, Integer.MAX_VALUE);
    }

    public static void printStackTrace(int maxLevel) {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        printStackTrace(stackTraceElements, 1, maxLevel);
    }

    /**
     * print stack trace elements to log
     *
     * @param stackTraceElements stack trace element to be print into log
     * @param startLevel         index from top of stack to be print
     * @param maxLevel           max index of stack to be print
     */
    private static void printStackTrace(StackTraceElement[] stackTraceElements, int startLevel, int maxLevel) {
        d(formatStackTrace(stackTraceElements, startLevel, maxLevel));
    }

    private static String formatStackTrace(StackTraceElement[] stackTraceElements, int startLevel, int maxLevel) {
        //防止溢出
        maxLevel = maxLevel < Integer.MAX_VALUE - startLevel ? maxLevel : Integer.MAX_VALUE - startLevel;
        int level = stackTraceElements.length < maxLevel + startLevel ? stackTraceElements.length : maxLevel + startLevel;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("### ").append(_TIME_()).append("\n");
        for (int i = startLevel; i < level; i++) {
            StackTraceElement element = stackTraceElements[i];
            String className = element.getClassName();
            String fileName = element.getFileName();
            int lineNumber = element.getLineNumber();
            String methodName = element.getMethodName();

            stringBuilder.append("### at ")
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

        return stringBuilder.toString();
    }
}