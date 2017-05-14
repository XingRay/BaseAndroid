package com.ray.lib.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * utils for call
 * 关于拨号通话的工具箱
 */
public class CallUtil {
    private CallUtil() {
    }

    //<uses-permission android:name="android.permission.CALL_PHONE" />
    public static void endCall() {
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            Method getServiceMethod = cls.getDeclaredMethod("getService", String.class);
            IBinder binder = (IBinder) getServiceMethod.invoke(null, Context.TELEPHONY_SERVICE);
//            ITelephony iTelephony = ITelephony.Stub.asInterface(binder);
//            iTelephony.endCall();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
        }
    }

    public static void dial(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
        return;
    }


    //<uses-permission android:name="android.permission.CALL_PHONE" />
    public static void call(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
//        context.startActivity(intent);
        return;
    }
}
