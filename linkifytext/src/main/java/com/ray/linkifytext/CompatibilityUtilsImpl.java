package com.ray.linkifytext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.text.Spannable;
import android.view.ViewConfiguration;
import android.widget.TextView;

/**
 * I use 2 classes because I still support the version 1.5 which throws
 * VerifyException if to put all code in the single class
 */
@SuppressLint("NewApi")
public class CompatibilityUtilsImpl {

    public static void setDisplayHomeAsUpEnabled(Activity activity) {
        activity.getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static boolean hasHardwareMenu(Context context, int currentVersion) {
        if (currentVersion < 11) {
            return true;
        } else if (currentVersion >= 11 && currentVersion <= 13) {
            return false;
        }

        return ViewConfiguration.get(context).hasPermanentMenuKey();
    }

    public static boolean isTextSelectable(TextView textView) {
        return textView.isTextSelectable();
    }

    public static void copyText(Activity activity, String label, String text, int currentVersion) {
        if (currentVersion < 11) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(label, text);
            clipboard.setPrimaryClip(clip);
        }
    }

    public static class API4 {
        public static boolean isTablet(Context context) {
            return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                    >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        }
    }


    public static void resetMyLeadingMarginSpanState(Spannable ss) {
        MyLeadingMarginSpan2[] spans = ss.getSpans(0, ss.length(), MyLeadingMarginSpan2.class);
        for (MyLeadingMarginSpan2 span : spans) {
            span.resetDrawState();
        }
    }

    public static void setMyLeadingMarginSpanCurrentLine(Spannable ss, int line) {
        MyLeadingMarginSpan2[] spans = ss.getSpans(0, ss.length(), MyLeadingMarginSpan2.class);
        for (MyLeadingMarginSpan2 span : spans) {
            span.setMyLeadingMarginSpanCurrentLine(line);
        }
    }

    public static void callMyLeadingMarginSpanMeasure(Spannable ss) {
        MyLeadingMarginSpan2[] spans = ss.getSpans(0, ss.length(), MyLeadingMarginSpan2.class);
        for (MyLeadingMarginSpan2 span : spans) {
            span.onMeasure();
        }
    }
}
