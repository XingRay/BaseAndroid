package com.ray.activity_dialog;

import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : adapter for render UI for {@link ActivityDialog}
 */

public abstract class DialogAdapter {
    private boolean mIsAutoDismiss;
    private ActivityDialog mDialog;

    public DialogAdapter() {
        mIsAutoDismiss = true;
    }

    void autoDismiss(boolean autoDismiss) {
        mIsAutoDismiss = autoDismiss;
    }

    public boolean isAutoDismiss() {
        return mIsAutoDismiss;
    }

    protected abstract int getLayoutId();

    protected abstract void bindView(View rootView);

    final void bindDialog(ActivityDialog dialog) {
        mDialog = dialog;
    }

    void unbindDialog() {
        mDialog = null;
    }

    protected final void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
