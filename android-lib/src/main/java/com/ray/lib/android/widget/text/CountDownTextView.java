package com.ray.lib.android.widget.text;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.util.Timer;

/**
 * @author      : leixing
 * @date        : 2017-08-03
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CountDownTextView extends AppCompatTextView {

    private CountDownHandler mHandler;

    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void removeOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        super.removeOnAttachStateChangeListener(listener);
    }

    @Override
    public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.removeOnLayoutChangeListener(listener);
        mHandler = new CountDownHandler(Looper.myLooper());
        Timer mTimer = new Timer();
    }

    public void start() {

    }

    public void pause() {

    }

    public void restart() {

    }

    public void stop() {

    }

    public void from(long from){

    }

    public void to(long to){

    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    private static class CountDownHandler extends Handler {
        private CountDownHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
