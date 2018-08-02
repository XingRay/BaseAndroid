package com.ray.baseandroid.alarm;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.TraceUtil;
import com.ray.lib.java.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @@author      : leixing
 * @@date        : 2017-06-05
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class AlarmSetActivity extends BaseActivity {
    @BindView(R.id.bt_time_picker)
    Button btTimePicker;

    private int mHour;
    private int mMinute;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_alarm_set);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_time_picker, R.id.bt_set_clock, R.id.bt_notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_time_picker:
                pickTime();
                break;
            case R.id.bt_set_clock:
                setClock();
                break;
            case R.id.bt_notification:
                notification();
                break;
        }
    }

    private void pickTime() {
        TimePicker timePicker = new TimePicker(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(timePicker);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                btTimePicker.setText(mHour + ":" + mMinute);
            }
        });
        dialog.show();
    }

    private void setClock() {
        TraceUtil.log();
        Intent intent = new Intent();
        intent.setAction("alarm_clock");
        intent.putExtra("notification", "Time's up");

        PendingIntent broadcast = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        PendingIntent service = PendingIntent.getService(mContext, 0, intent, 0);
        PendingIntent activity = PendingIntent.getActivity(mContext, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUtil.getCurrentTimeStamp() + 5 * 1000, 60 * 1000, broadcast);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUtil.getCurrentTimeStamp() + 10 * 1000, 60 * 1000, service);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUtil.getCurrentTimeStamp() + 15 * 1000, 60 * 1000, activity);
    }

    private void notification() {
        Intent intent = new Intent();
        intent.setAction("alarm_clock");
        intent.putExtra("notification", "Time's up");
        startActivity(intent);
    }
}
