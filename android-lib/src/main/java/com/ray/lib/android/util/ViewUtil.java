package com.ray.lib.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.lib.java.util.TextUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author      : leixing
 * @date        : 2017-04-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ViewUtil {
    /**
     * util不能创建对象
     */
    private ViewUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 设置view的可见性
     *
     * @param view
     * @param visibility
     */
    public static void setViewVisibility(final View view, final int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dp2px(Context context, float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics(context));
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getDisplayMetrics(context));
    }

    public static float px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        Resources resources;

        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        return resources.getDisplayMetrics();
    }

    public static <V extends View> V findView(View v, int resId) {
        return (V) v.findViewById(resId);
    }

    public static void textViewShowText(TextView view) {
        view.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    public static void textViewHideText(TextView view) {
        view.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public static void editTextMoveCursorToLast(EditText editText) {
        String text = editText.getText().toString();
        editText.setSelection(text.length());
    }

    public static void setFocusOnView(View v) {
        if (v == null) {
            return;
        }

        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
    }

    public static void postEnableView(final View view, final boolean enable) {
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(enable);
            }
        });
    }

    public static void chageUnderlineColor(View underline, boolean hasFocus, int focusColor, int noFocusColor) {
        if (underline == null) {
            return;
        }

        if (hasFocus) {
            underline.setBackgroundColor(focusColor);
        } else {
            underline.setBackgroundColor(noFocusColor);
        }
    }

    public static void changeButtonBackgroundAlpha(Button button, EditText editText) {
        String text = editText.getText().toString().trim();

        if (TextUtils.isEmpty(text)) {
            button.getBackground().setAlpha(0x7f);
        } else {
            button.getBackground().setAlpha(0xff);
        }
    }

    public static void setText(View rootView, int id, CharSequence content) {
        TextView textView = rootView.findViewById(id);
        textView.setText(content);
    }

    public static void setOnClickListener(View rootView, int id, View.OnClickListener listener) {
        rootView.findViewById(id).setOnClickListener(listener);
    }

    public static void setImgRes(View rootView, int id, int imgResId) {
        ImageView imageView = rootView.findViewById(id);
        imageView.setImageResource(imgResId);
    }

    @NonNull
    public static CharSequence highLightKeyword(CharSequence raw, String keyword, int highLightColor) {
        if (TextUtils.isEmpty(raw)) {
            return "";
        }

        if (TextUtils.isEmpty(keyword)) {
            return raw;
        }

        SpannableString spannableString = new SpannableString(raw);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(spannableString);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new ForegroundColorSpan(highLightColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    public static CharSequence scaleText(CharSequence raw, String regex, float scale) {
        if (TextUtil.isEmpty(raw)) {
            return "";
        }
        if (TextUtil.isEmpty(regex)) {
            return raw;
        }

        SpannableString spannableString = new SpannableString(raw);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raw);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new RelativeSizeSpan(scale), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }
}
