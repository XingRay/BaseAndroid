package com.hecom.activity_dialog;

import android.content.Context;

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
    private boolean mCancelable;

    /**
     * priority of the dialog
     * higher the value is, more chance the dialog has to show
     */
    private int mPriority;

    /**
     * name of the dialog, if two {@code ActivityDialog} have the same name, the later one will replace
     * the previous one
     */
    private String mName;

    /**
     * adapter for render UI by given data
     */
    private DialogAdapter mAdapter;

    /**
     * width of the dialog
     */
    private int mWidth;

    /**
     * height of the dialog
     */
    private int mHeight;
    private int mStyle;
    private int mTheme;

    public ActivityDialog(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        mContext = context;

        mCancelable = true;
        mPriority = 0;
        mName = "";
    }

    public ActivityDialog cancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public ActivityDialog priority(int priority) {
        mPriority = priority;
        return this;
    }

    public ActivityDialog name(String name) {
        mName = name;
        return this;
    }

    public ActivityDialog Adapter(DialogAdapter adapter) {
        mAdapter = adapter;
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
    public ActivityDialog show() {
        if (mAdapter == null) {
            throw new IllegalArgumentException("adapter can not be null");
        }

        long code = CodeGenerator.getInstance().next();
        AdapterManager.getInstance().put(code, mAdapter);

        HostActivity.showDialog(mContext, code, mCancelable, mPriority, mWidth, mHeight, mStyle, mTheme);
        return this;
    }

    public ActivityDialog dismiss() {
        if (mAdapter != null) {
            mAdapter.dismiss();
        }

        return this;
    }

    public boolean isShowing() {
        // TODO: 2017-06-12
        return true;
    }
}