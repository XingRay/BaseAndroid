package com.ray.viewcounter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Author      : leixing
 * Date        : 2017-08-16
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ViewCounter implements Counter {

    public static final int MSG_COUNT = 100;
    private final CounterHandler mHandler;
    private long mStart;
    private long mEnd;
    private long mIntervalMills;
    private long mCount;
    private CountListener mCountListener;
    private long mCurrentValue;
    private long mStartMills;
    private boolean mStrictMode;
    private long mCountTime;

    public ViewCounter() {
        mHandler = new CounterHandler(Looper.getMainLooper());
    }

    public ViewCounter from(long start) {
        mStart = start;
        return this;
    }

    public ViewCounter to(long end) {
        mEnd = end;
        return this;
    }

    public ViewCounter intervalMills(long intervalMills) {
        mIntervalMills = intervalMills;
        return this;
    }

    public ViewCounter count(long count) {
        mCount = count;
        return this;
    }

    public ViewCounter countListener(CountListener listener) {
        mCountListener = listener;
        return this;
    }

    public ViewCounter strictMode(boolean strictMode) {
        mStrictMode = strictMode;
        return this;
    }

    public void start() {
        removeMessage();
        mCurrentValue = mStart;
        if (mStrictMode) {
            mCountTime = 0;
            mStartMills = System.currentTimeMillis();
        }
        sendMessage(0);
    }

    private void pause() {
        // TODO: 2017-08-16
    }

    private void restart() {
        // TODO: 2017-08-16
    }

    public void stop() {
        removeMessage();
    }

    @UiThread
    private void onCount() {
        boolean hasNext;
        long nextValue;
        if (mCurrentValue < mEnd) {
            nextValue = Math.min(mCurrentValue + mCount, mEnd);
            hasNext = true;
        } else if (mCurrentValue > mEnd) {
            nextValue = Math.max(mCurrentValue - mCount, mEnd);
            hasNext = true;
        } else {
            nextValue = mEnd;
            hasNext = false;
        }

        if (mCountListener != null) {
            mCountListener.onCount(mCurrentValue, hasNext);
        }

        mCurrentValue = nextValue;
        if (!hasNext) {
            return;
        }

        if (mStrictMode) {
            mCountTime++;
            long currentTimeMillis = System.currentTimeMillis();
            long nextTimeMills = mStartMills + mCountTime * mIntervalMills;
            long delayMills = Math.max(0, nextTimeMills - currentTimeMillis);
            sendMessage(delayMills);
        } else {
            sendMessage(mIntervalMills);
        }
    }

    private void sendMessage(long delayMills) {
        Message message = mHandler.obtainMessage(MSG_COUNT);
        message.obj = new WeakReference<Counter>(this);
        mHandler.sendMessageDelayed(message, delayMills);
    }

    private void removeMessage() {
        mHandler.removeMessages(MSG_COUNT);
    }

    public interface CountListener {
        void onCount(long count, boolean hasNext);
    }

    private static class CounterHandler extends Handler {
        private CounterHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_COUNT:
                    handleCountMessage(msg);
                    break;
                default:
            }
        }

        private void handleCountMessage(Message msg) {
            if (!(msg.obj instanceof WeakReference)) {
                return;
            }
            WeakReference reference = (WeakReference) msg.obj;
            Object o = reference.get();
            if (o == null || !(o instanceof ViewCounter)) {
                return;
            }

            ViewCounter counter = (ViewCounter) o;
            counter.onCount();
        }
    }
}
