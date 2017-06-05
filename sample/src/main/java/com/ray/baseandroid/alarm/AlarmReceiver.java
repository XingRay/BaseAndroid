package com.ray.baseandroid.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ray.lib.android.util.TraceUtil;

/**
 * Author      : leixing
 * Date        : 2017-06-05
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TraceUtil.log("alarm time up");
        String notification = intent.getStringExtra("notification");
        Toast.makeText(context, notification, Toast.LENGTH_SHORT).show();
    }
}
