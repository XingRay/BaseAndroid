package com.hecom.activity_dialog;

import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : adapter for render UI for {@link ActivityDialog}
 */

public abstract class DialogAdapter implements DialogHandle {

    private int mLayoutId;
    private DialogFragment mFragment;
    private boolean mAutoDismiss;

    public DialogAdapter() {
        mAutoDismiss = true;
    }

    protected abstract int getLayoutId();

    protected abstract void bindView(View rootView);

    DialogAdapter bindDialog(DialogFragment fragment) {
        mFragment = fragment;
        return this;
    }

    DialogAdapter autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return this;
    }

    protected boolean isAutoDismiss() {
        return mAutoDismiss;
    }

    @Override
    public void dismiss() {
        if (mFragment == null) {
            throw new IllegalStateException("can not dismiss dialog without bind");
        }
        mFragment.dismiss();
    }
}
