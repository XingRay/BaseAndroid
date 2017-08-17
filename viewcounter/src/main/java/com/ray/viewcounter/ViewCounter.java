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
 * Description : counter for widget
 */

public class ViewCounter {
    private static final int MSG_COUNT = 100;
    private static final int MIN_INTERVAL_MILLS = 1;

    private final CounterHandler mHandler;
    private long mStart = 0;
    private long mEnd = Long.MAX_VALUE;
    private long mIntervalMills = MIN_INTERVAL_MILLS;
    private long mCount;
    private CountListener mCountListener;
    private long mCurrentValue;
    private long mStartMills;
    private long mCountTime;
    private long mNextValue;
    private boolean mStrictMode;

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
        mIntervalMills = Math.max(intervalMills, MIN_INTERVAL_MILLS);
        return this;
    }

    public ViewCounter count(long count) {
        mCount = count;
        return this;
    }

    public ViewCounter strictMode(boolean strictMode) {
        mStrictMode = strictMode;
        return this;
    }

    public ViewCounter countListener(CountListener listener) {
        mCountListener = listener;
        return this;
    }


    public ViewCounter start() {
        removeMessage();
        mCurrentValue = mStart;
        mNextValue = mStart;
        mCountTime = 0;
        mStartMills = System.currentTimeMillis();
        sendMessage(0);
        return this;
    }

//    private ViewCounter pause() {
//        // TODO: 2017-08-16
//        return this;
//    }

//    private ViewCounter resume() {
//        // TODO: 2017-08-16
//        return this;
//    }

    public ViewCounter stop() {
        removeMessage();
        return this;
    }

    public long getCurrentValue() {
        return mCurrentValue;
    }

    @UiThread
    private void onCount() {
        long currentTimeMillis = System.currentTimeMillis();
        long countTimes = (currentTimeMillis - mStartMills) / mIntervalMills + 1;
        boolean hasNext = false;
        while (mCountTime < countTimes) {
            hasNext = count();
            if (mStrictMode && mCountListener != null) {
                // listener will invoke after every count in strict mode
                mCountListener.onCount(mCurrentValue, hasNext);
            }
            if (!hasNext) {
                break;
            }
            mCountTime++;
        }

        if (!mStrictMode && mCountListener != null) {
            // listener will invoke once in non strict mode
            mCountListener.onCount(mCurrentValue, hasNext);
        }
        if (!hasNext) {
            return;
        }
        long nextTimeMills = mStartMills + mCountTime * mIntervalMills;
        long delayMills = Math.max(0, nextTimeMills - currentTimeMillis);
        sendMessage(delayMills);
    }

    private boolean count() {
        boolean hasNext;
        mCurrentValue = mNextValue;
        if (mCurrentValue < mEnd) {
            mNextValue = Math.min(mCurrentValue + mCount, mEnd);
            hasNext = true;
        } else if (mCurrentValue > mEnd) {
            mNextValue = Math.max(mCurrentValue - mCount, mEnd);
            hasNext = true;
        } else {
            hasNext = false;
        }
        return hasNext;
    }

    private void sendMessage(long delayMills) {
        Message message = mHandler.obtainMessage(MSG_COUNT);
        message.obj = new WeakReference<>(this);
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
