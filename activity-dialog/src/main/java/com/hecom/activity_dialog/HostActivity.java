package com.hecom.activity_dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : host activity for dialog
 */

public class HostActivity extends FragmentActivity {
    public static final String CODE = "code";
    public static final String CANCELABLE = "cancelable";
    public static final String PRIORITY = "priority";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String DIALOG_ACTIVITY_FRAGMENT = "DIALOG_ACTIVITY_FRAGMENT";
    public static final String STYLE = "style";
    public static final String THEME = "theme";

    /**
     * min value of width
     */
    private static final int MIN_WIDTH = 240;

    /**
     * min value of height
     */
    private static final int MIN_HEIGHT = 160;


    private boolean mCancelable;
    private long mCode;
    private long mPriority;
    private int mWidth;
    private int mHeight;
    private FragmentManager mFragmentManager;
    private int mStyle;
    private int mTheme;

    static void showDialog(Context context, long code, boolean cancelable, long priority, int width, int height, int style, int theme) {
        Intent intent = new Intent();

        intent.putExtra(CODE, code);
        intent.putExtra(CANCELABLE, cancelable);
        intent.putExtra(PRIORITY, priority);
        intent.putExtra(WIDTH, width);
        intent.putExtra(HEIGHT, height);
        intent.putExtra(STYLE, style);
        intent.putExtra(THEME, theme);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, HostActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isParamsValid(getIntent())) {
            finish();
            return;
        }
        initVariables();
        initView();
        showDialog();
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
    private boolean isParamsValid(Intent intent) {
        long priority = intent.getLongExtra(PRIORITY, 0);
        if (priority < mPriority) {
            return false;
        }

        mPriority = priority;
        mCancelable = intent.getBooleanExtra(CANCELABLE, false);
        mCode = intent.getLongExtra(CODE, -1);
        mWidth = Math.max(intent.getIntExtra(WIDTH, 0), MIN_WIDTH);
        mHeight = Math.max(intent.getIntExtra(HEIGHT, 0), MIN_HEIGHT);
        mStyle = intent.getIntExtra(STYLE, 0);
        mTheme = intent.getIntExtra(THEME, 0);
        return true;
    }

    private void initVariables() {
        mFragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        setFinishOnTouchOutside(mCancelable);

        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout, new FrameLayout.LayoutParams(mWidth, mHeight));
    }

    private void showDialog() {
        ActivityDialogFragment fragment = (ActivityDialogFragment) mFragmentManager.findFragmentByTag(DIALOG_ACTIVITY_FRAGMENT);
        if (fragment == null) {
            fragment = ActivityDialogFragment.newInstance(mCode, mCancelable, mStyle, mTheme);
        }

        fragment.show(mFragmentManager, DIALOG_ACTIVITY_FRAGMENT);
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
