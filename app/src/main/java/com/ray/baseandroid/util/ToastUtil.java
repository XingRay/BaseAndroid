package com.ray.baseandroid.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(final Activity activity, @StringRes final int stringRes) {
        if (activity == null) {
            return;
        }

        Toast.makeText(activity, stringRes, Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程可弹出Toast的方法
     *
     * @param activity
     * @param content
     */
    public static void showToast(final Activity activity, final String content) {
        if (activity == null) {
            return;
        }

        Toast.makeText(activity, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间Toast
     *
     * @param context
     * @param content
     */
    public static void showToastShort(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showToastShort(Context context, int stringRes) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show();
    }

    public static void showToastShort(final Activity activity, final String content) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 短时间Toast
     *
     * @param context
     * @param content
     */
    public static void showToastLong(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    public static void showToastLong(final Activity activity, final String content) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), content, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showTestToast(Context context, String content) {
        if (mToast == null) {
            mToast = new Toast(context);
            TextView tv = new TextView(context);
            tv.setText(content);
            mToast.setView(tv);
        } else {

            ((TextView) mToast.getView()).setText(content);
        }
        mToast.show();
        Log.i("MyTest", content);
    }
}
