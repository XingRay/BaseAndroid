package com.ray.activity_dialog;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : dialog user for show a dialog at background
 */

public class ActivityDialog {

    /**
     * context for dialog, it can be {@code Activity} or {@code Service} or {@code Application}
     */
    private final Context mContext;

    /**
     * is dialog will dismiss when click outside of it
     */
    boolean mCancelable;

    /**
     * is this dialog will auto dismiss after any click event
     */
    boolean mAutoDismiss;

    /**
     * priority of the dialog
     * higher the value is, more chance the dialog has to show
     */
    int mPriority;

    /**
     * name of the dialog, if two {@code ActivityDialog} have the same name, the later one will replace
     * the previous one
     */
    String mName;

    /**
     * adapter for render UI by given data
     */
    DialogAdapter mAdapter;

    /**
     * width of the dialog
     */
    int mWidth;

    /**
     * height of the dialog
     */
    int mHeight;

    /**
     * style of dialog
     */
    int mStyle;

    /**
     * theme of dialog
     */
    int mTheme;

    /**
     * is dialog showing
     */
    private boolean mIsShowing;

    /**
     * the host where this dialog actually show at
     */
    private Activity mHost;

    /**
     * unique code of this dialog, update at
     */
    private long mCode;

    public ActivityDialog(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        mContext = context;
        mCode = CodeGenerator.getInstance().next();

        // default values
        mAutoDismiss = true;
        mCancelable = true;
        mPriority = 0;
        mName = "";
        mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public ActivityDialog cancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public ActivityDialog priority(int priority) {
        mPriority = priority;
        return this;
    }

    public ActivityDialog autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        if (mAdapter != null) {
            mAdapter.autoDismiss(autoDismiss);
        }
        return this;
    }

    public ActivityDialog name(String name) {
        mName = name;
        return this;
    }

    public ActivityDialog Adapter(DialogAdapter adapter) {
        if (mAdapter != null) {
            mAdapter.unbindDialog();
        }
        mAdapter = adapter;
        mAdapter.bindDialog(this);
        mAdapter.autoDismiss(mAutoDismiss);
        return this;
    }

    public ActivityDialog width(int width) {
        mWidth = width;
        return this;
    }

    public ActivityDialog height(int height) {
        mHeight = height;
        return this;
    }

    public ActivityDialog style(int style) {
        mStyle = style;
        return this;
    }

    public ActivityDialog theme(int theme) {
        mTheme = theme;
        return this;
    }

    /**
     * show this dialog to user
     */
    public void show() {
        if (mIsShowing) {
            return;
        }
        mIsShowing = true;

        if (mAdapter == null) {
            throw new IllegalArgumentException("adapter can not be null");
        }

        DialogManager.getInstance().put(mCode, this);
        HostActivity.showDialog(mContext, mCode);
    }

    public void dismiss() {
        if (!mIsShowing) {
            return;
        }
        mIsShowing = false;

        if (mHost != null && !mHost.isFinishing()) {
            mHost.finish();
        }
        DialogManager.getInstance().remove(mCode);
    }

    public boolean isShowing() {
        return mIsShowing;
    }

    void bindHost(Activity host) {
        mHost = host;
    }

    void unbindHost() {
        mHost = null;
    }
}