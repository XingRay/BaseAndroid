package com.ray.linkifytext;

import android.text.Spannable;
import android.widget.TextView;

public class CompatibilityUtils {


    public static boolean isTextSelectable(TextView textView) {
        return CompatibilityUtilsImpl.isTextSelectable(textView);
    }

    public static void resetMyLeadingMarginSpanState(Spannable ss) {
        CompatibilityUtilsImpl.resetMyLeadingMarginSpanState(ss);
    }

    public static void setMyLeadingMarginSpanCurrentLine(Spannable ss, int line) {
        CompatibilityUtilsImpl.setMyLeadingMarginSpanCurrentLine(ss, line);
    }

    public static void callMyLeadingMarginSpanMeasure(Spannable ss) {
        CompatibilityUtilsImpl.callMyLeadingMarginSpanMeasure(ss);
    }
}
