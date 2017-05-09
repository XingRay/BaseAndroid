package com.ray.lib.android.widget.dialogactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 抽象的窗口宿主类Activity
 */

public class DialogActivity extends FragmentActivity {
    public static final String EVENT_ID = "event_id";
    public static final String CANCELABLE = "cancelable";
    public static final String PRIORITY = "priority";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String DIALOG_ACTIVITY_FRAGMENT = "DIALOG_ACTIVITY_FRAGMENT";
    /**
     * 最小宽度
     */
    private static final int MIN_WIDTH = 240;

    /**
     * 最小高度
     */
    private static final int MIN_HEIGHT = 160;

    private boolean mCancelable;
    private long mEventId;
    private long mPriority;
    private int mWidth;
    private int mHeight;

    static void showDialog(Context context, long eventId, boolean cancelable, long priority, int width, int height) {
        Intent intent = new Intent();

        intent.putExtra(EVENT_ID, eventId);
        intent.putExtra(CANCELABLE, cancelable);
        intent.putExtra(PRIORITY, priority);
        intent.putExtra(WIDTH, width);
        intent.putExtra(HEIGHT, height);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, DialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!canPageShow(getIntent())) {
            finish();
            return;
        }
        initVariables();
        initView();
        loadData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * 根据参数判断页面是否满足展示的条件，不满住条件则在Activity的onCreate方法就finish
     *
     * @param intent 参数
     * @return
     */
    private boolean canPageShow(Intent intent) {
        long priority = intent.getLongExtra(PRIORITY, 0);
        if (priority < mPriority) {
            return false;
        }

        mPriority = priority;
        mCancelable = intent.getBooleanExtra(CANCELABLE, false);
        mEventId = intent.getLongExtra(EVENT_ID, -1);
        mWidth = Math.max(intent.getIntExtra(WIDTH, -1), MIN_WIDTH);
        mHeight = Math.max(intent.getIntExtra(HEIGHT, -1), MIN_HEIGHT);

        return true;
    }

    private void initVariables() {

    }

    private void initView() {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        setFinishOnTouchOutside(mCancelable);

        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new FrameLayout.LayoutParams(mWidth, mHeight));
    }

    private void loadData() {
        DialogActivityFragment fragment = DialogActivityFragment.newInstance(mEventId, mCancelable);
        fragment.show(getSupportFragmentManager(), DIALOG_ACTIVITY_FRAGMENT);
    }

    @Override
    public void onBackPressed() {
        if (mCancelable) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            if (mCancelable) {
                finish();
            }
            return true;
        }

        return super.onTouchEvent(event);
    }
}
