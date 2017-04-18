package com.ray.baseandroid.widget.dialogactivity.adapters;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.hecom.R;
import com.hecom.util.ViewUtil;
import com.hecom.widget.dialogactivity.DialogAdapter;

/**
 * Author      : leixing
 * Date        : 2017-04-17
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ProgressDialogAdapter extends DialogAdapter {

    private CharSequence mContent;

    public ProgressDialogAdapter() {
        super();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_progress_dialog;
    }

    @Override
    protected void bindView(View rootView, DialogFragment fragment) {
        ViewUtil.setText(rootView, R.id.tv_message, mContent);
    }

    public ProgressDialogAdapter content(CharSequence text) {
        mContent = text;
        return this;
    }
}
