package com.ray.lib.android.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 控件工具
 * Created by leixing on 2016/4/26.
 */
public class WidgetUtil {

    //显示/隐藏TextView中内容, 常用语密码输入框(EditText)的密码的显示隐藏切换
    public static void textViewShowText(TextView view) {
        view.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    public static void textViewHideText(TextView view) {
        view.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    //将EditText的光标移至末尾
    public static void editTextMoveCursorToLast(EditText editText) {
        String text = editText.getText().toString();
        editText.setSelection(text.length());
    }

    //控件获取焦点
    public static void setFocusOnView(View v) {
        if (v == null) {
            return;
        }

        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
    }

    //在UI线程中使能控件(Button等)
    public static void postEnableView(final View view, final boolean enable) {
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(enable);
            }
        });
    }
}
