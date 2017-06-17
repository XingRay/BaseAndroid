package com.ray.lib.android.widget.todo.widget.toast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ray.lib.android.R;


/**
 * Created by leixing
 * on 2016-10-31.
 * Email : leixing1012@gmail.cn
 */

public class TextInCenterToast extends Toast {
    public static final int LENGTH_KEEP_SHOWING = -100;
    private static TextInCenterToast instance;
    private final TextView tvText;
    private final Handler mHandler;
    private boolean mIsCanceled = false;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    private TextInCenterToast(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_toast_text_in_center_toast, null);
        tvText = (TextView) view.findViewById(R.id.tv_text);
        Looper mainLooper = Looper.getMainLooper();
        mHandler = new Handler(mainLooper);

        this.setGravity(Gravity.CENTER, 0, 0);
        this.setDuration(Toast.LENGTH_SHORT);
        this.setView(view);
    }

    public static TextInCenterToast getInstance(Context context) {
        if (instance == null) {
            synchronized (TextInCenterToast.class) {
                if (instance == null) {
                    instance = new TextInCenterToast(context);
                }
            }
        }
        return instance;
    }

    public TextInCenterToast setText(String text) {
        tvText.setText(text);
        return this;
    }

    public TextInCenterToast showText(String text) {
        return showText(text, LENGTH_KEEP_SHOWING);
    }

    public TextInCenterToast showText(String text, int duration) {
        this.setText(text);

        if (duration == LENGTH_KEEP_SHOWING) {
            this.setDuration(Toast.LENGTH_LONG);
            mIsCanceled = false;
            showUntilCancel();
        } else {
            mIsCanceled = true;
            this.setDuration(duration);
            this.show();
        }

        return this;
    }

    public void hide() {
        this.cancel();
        mIsCanceled = true;
    }

    public boolean isShowing() {
        return !mIsCanceled;
    }

    private void showUntilCancel() {
        if (mIsCanceled) {
            return;
        }

        show();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                showUntilCancel();
            }
        }, 1000);
    }
}
