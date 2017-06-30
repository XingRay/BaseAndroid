package com.ray.lib.android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.StringRes;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ray.lib.android.R;

public class MarqueeTextView extends View implements Marquee {
    private static final int MARQUEE_MSG = 200;

    //  -------  attributes  ----------//
    private float restartDistance = 50.0f;
    private int marqueeIntervalMills = 30;
    private int restartDelayMills = 2000;
    private float marqueeSpeed = 1.0f;
    private boolean mIsMarqueeRunning = false;
    private String mText = "";
    private float mTextSize = 12;
    private int mTextColor = Color.BLACK;

    private float mScrollX;
    private Handler mHandler;
    private TextPaint mTextPaint;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyAttributes(context, attrs);

        mHandler = new InternalHandler();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

        if (mIsMarqueeRunning && !TextUtils.isEmpty(mText)) {
            sendMessage(restartDelayMills);
        }
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarqueeTextView);
        if (typedArray == null) {
            return;
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_restart_distance)) {
            restartDistance = typedArray.getFloat(R.styleable.MarqueeTextView_restart_distance, restartDistance);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_marquee_interval_mills)) {
            marqueeIntervalMills = typedArray.getInt(R.styleable.MarqueeTextView_marquee_interval_mills, marqueeIntervalMills);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_restart_delay_mills)) {
            restartDelayMills = typedArray.getInt(R.styleable.MarqueeTextView_restart_delay_mills, restartDelayMills);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_marquee_speed)) {
            marqueeSpeed = typedArray.getFloat(R.styleable.MarqueeTextView_marquee_speed, marqueeSpeed);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_marquee)) {
            mIsMarqueeRunning = typedArray.getBoolean(R.styleable.MarqueeTextView_marquee, mIsMarqueeRunning);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_text)) {
            mText = typedArray.getString(R.styleable.MarqueeTextView_text);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_textSize)) {
            mTextSize = typedArray.getDimension(R.styleable.MarqueeTextView_textSize, mTextSize);
        }

        if (typedArray.hasValue(R.styleable.MarqueeTextView_textColor)) {
            mTextColor = typedArray.getColor(R.styleable.MarqueeTextView_textColor, mTextColor);
        }

        typedArray.recycle();
    }

    public final void setText(@StringRes int resId) {
        setText(getContext().getResources().getText(resId));
    }

    public final void setText(CharSequence text) {
        String t = text == null ? "" : text.toString();
        if (!t.equals(mText)) {
            mText = t;
            requestLayout();
            invalidate();
        }
    }

    public void setTextSize(float size) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setTextSize(int unit, float size) {
        Context c = getContext();
        Resources r;

        if (c == null)
            r = Resources.getSystem();
        else
            r = c.getResources();

        setRawTextSize(TypedValue.applyDimension(
                unit, size, r.getDisplayMetrics()));
    }

    private void setRawTextSize(float size) {
        if (size != mTextPaint.getTextSize()) {
            mTextPaint.setTextSize(size);
            requestLayout();
            invalidate();
        }
    }

    public void setTextColor(int color) {
        if (color != mTextPaint.getColor()) {
            mTextPaint.setColor(color);
            invalidate();
        }
    }

    public void startMarquee() {
        mIsMarqueeRunning = true;
        sendMessage();
    }

    public void stopMarquee() {
        removeMessage();
        mScrollX = 0;
        mIsMarqueeRunning = false;
        invalidate();
    }

    public void pauseMarquee() {
        removeMessage();
        mIsMarqueeRunning = false;
    }

    public boolean needMarquee() {
        return (mTextPaint.measureText(mText) + 0.5) > getWidth();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!TextUtils.isEmpty(mText) && mIsMarqueeRunning)
            sendMessage(restartDelayMills);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeMessage();

    }

    private void sendMessage() {
        sendMessage(0);
    }

    private void sendMessage(int delay) {
        Message message = mHandler.obtainMessage(MARQUEE_MSG);
        message.obj = this;
        mHandler.sendMessageDelayed(message, delay);
    }

    private void removeMessage() {
        if (mHandler.hasMessages(MARQUEE_MSG))
            mHandler.removeMessages(MARQUEE_MSG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = (int) (mTextPaint.measureText(mText) + 0.5);
                break;
            case MeasureSpec.UNSPECIFIED:
                width = (int) (mTextPaint.measureText(mText) + 0.5);
                break;
            default:
                width = widthSize;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = (int) (mTextPaint.getTextSize() + 0.5);
                break;
            case MeasureSpec.UNSPECIFIED:
                height = (int) (mTextPaint.getTextSize() + 0.5);
                break;
            default:
                height = heightSize;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(mText)) {
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            float baseLine = (getMeasuredHeight() - fontMetrics.ascent - fontMetrics.descent) / 2;
            canvas.drawText(mText, mScrollX, baseLine, mTextPaint);
        }
    }

    @Override
    public void onMarquee() {
        if (Math.abs(mScrollX) > (mTextPaint.measureText(mText) + restartDistance)) {
            //text is out of view, restart marquee
            mScrollX = 0;
            invalidate();
            if (mIsMarqueeRunning) {
                sendMessage(restartDelayMills);
            }
        } else {
            //marquee, scroll to left by marquee speed
            mScrollX -= marqueeSpeed;
            invalidate();
            if (mIsMarqueeRunning) {
                sendMessage(marqueeIntervalMills);
            }
        }
    }

    private static class InternalHandler extends Handler {
        InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MARQUEE_MSG:
                    ((Marquee) msg.obj).onMarquee();
                    break;
            }
        }
    }
}