package com.ray.baseandroid.widget.todo.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 可自定义标题的日起控件
 * Created by Jiang on 2015/9/18.
 */
public class SetTitleDatePickerDialog {

    private Activity activity;
    private int theme;
    private String title;
    private int year;
    private int month;
    private int day;
    private OnSetTitleDatePickerDialogListener listener;
    private boolean isAutoDismiss;

    public void setListener(OnSetTitleDatePickerDialogListener listener) {
        this.listener = listener;
    }

    public SetTitleDatePickerDialog(Activity activity, int theme, String title, int year, int month,
                                    int day, OnSetTitleDatePickerDialogListener listener) {
        this.activity = activity;
        this.theme = theme;
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.listener = listener;
        this.isAutoDismiss = true;
    }

    public AlertDialog create() {
        //通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, theme);
        final DatePicker datePicker = new DatePicker(builder.getContext());
        //设置日期简略显示 否则详细显示 包括:星期周
        datePicker.setCalendarViewShown(false);
        if (day == -1) {
            datePicker.findViewById(Resources.getSystem().getIdentifier("day", "id", "android"))
                    .setVisibility(View.GONE);
            day = 1;
        }
        //初始化当前日期
        datePicker.init(year, month, day, null);
        //设置date布局
        builder.setView(datePicker);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Calendar date = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(), datePicker.getDayOfMonth());
                String str = datePicker.getYear() + "-"
                        + (datePicker.getMonth() + 1) + "-"
                        + datePicker.getDayOfMonth();
                if (listener != null) {
                    listener.onPositive(date.getTimeInMillis(), str);
                }
                activity.removeDialog(0);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (listener != null) {
                    listener.onNegative();
                }
                activity.removeDialog(0);
            }
        });
        return builder.create();
    }

    public interface OnSetTitleDatePickerDialogListener {
        void onPositive(long time, String data);

        void onNegative();
    }
}
