package com.ray.lib.android.widget.todo.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ray.lib.android.R;

import java.util.Calendar;

/**
 * Created by leixing on 2016/11/6 19:46.
 * Email: leixing1012@qq.com
 */
public class DateSelectorPopupWindow extends PopupWindow implements View.OnClickListener {
    private final PopupWindow popup;
    private final TextView tvLeftText;
    private final TextView tvRightText;
    private final DatePicker dpDatePicker;
    private OnTextClickListener listener;

    public DateSelectorPopupWindow(Context context) {
        Calendar cal = Calendar.getInstance();
        View contentView = View.inflate(context, R.layout.popup_window_date_selector_two_button, null);
        popup = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setBackgroundDrawable(new ColorDrawable());
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setAnimationStyle(R.style.window_from_bottom);

        dpDatePicker = contentView.findViewById(R.id.dp_date_picker);
        dpDatePicker.setCalendarViewShown(false);
        dpDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        tvLeftText = contentView.findViewById(R.id.tv_left_text);
        tvLeftText.setOnClickListener(this);

        tvRightText = contentView.findViewById(R.id.tv_right_text);
        tvRightText.setOnClickListener(this);
    }

    public DateSelectorPopupWindow(Context context, Date date) {
        this(context);
        if (date != null) {
            dpDatePicker.updateDate(date.getYear(), date.getMonth() - 1, date.getDay());
        }
    }

    public DateSelectorPopupWindow(Context context, int year, int month, int day) {
        this(context);
        dpDatePicker.updateDate(year, month - 1, day);
    }

    public void setOnTextClickListener(OnTextClickListener listener) {
        this.listener = listener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        popup.setOnDismissListener(onDismissListener);
    }

    public void show(View view) {
        popup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View v) {
        if (v == tvLeftText) {
            if (listener != null) {
                listener.onLeftTextClick(getDate());
                popup.dismiss();
            }
        } else if (v == tvRightText) {
            if (listener != null) {
                listener.onRightTextClick(getDate());
                popup.dismiss();
            }
        }
    }

    public Date getDate() {
        int year = dpDatePicker.getYear();
        int month = dpDatePicker.getMonth() + 1;
        int day = dpDatePicker.getDayOfMonth();
        return new Date(year, month, day);
    }

    public interface OnTextClickListener {
        void onLeftTextClick(Date d);

        void onRightTextClick(Date d);
    }

    public static class Date {
        private int year;
        private int month;
        private int day;

        public Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public static Date fromString(String dateString) {
            if (TextUtils.isEmpty(dateString)) {
                return null;
            }

            String[] split = dateString.split("-");
            if (split.length < 3) {
                return null;
            }

            int year = -1;
            int month = -1;
            int day = -1;

            try {
                year = Integer.parseInt(split[0]);
                month = Integer.parseInt(split[1]);
                day = Integer.parseInt(split[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if (year == -1 || month == -1 || day == -1) {
                return null;
            }

            return new Date(year, month, day);
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return year + "-" + month + "-" + day;
        }
    }
}