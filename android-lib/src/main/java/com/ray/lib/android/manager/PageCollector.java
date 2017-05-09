package com.ray.lib.android.manager;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PageCollector {
    private static List<WeakReference<Activity>> activityList = new ArrayList<>();

    public static void add(Activity activity) {
        activityList.add(new WeakReference<>(activity));
    }

    public static void remove(Activity activity) {
        for (WeakReference<Activity> wrActivity : activityList) {
            Activity a = wrActivity.get();
            if (a == activity) {
                activityList.remove(wrActivity);
            }
        }
    }

    public static void arrange() {
        ArrayList<WeakReference<Activity>> nullList = new ArrayList<>();

        for (WeakReference<Activity> wrActivity : activityList) {
            Activity activity = wrActivity.get();
            if (activity == null) {
                nullList.add(wrActivity);
            }
        }

        activityList.removeAll(nullList);
    }

    public static int getPageCount() {
        arrange();

        return activityList.size();
    }

    public static void clear() {
        for (WeakReference<Activity> wrActivity : activityList) {
            Activity activity = wrActivity.get();
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }

        activityList.clear();
        System.gc();
    }
}
