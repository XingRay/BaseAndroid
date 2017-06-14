package com.ray.activity_dialog.adapters;

import android.view.View;
import android.widget.TextView;

import com.ray.activity_dialog.DialogAdapter;
import com.ray.activity_dialog.R;


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
    protected void bindView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_message);
        textView.setText(mContent);
    }

    public ProgressDialogAdapter content(CharSequence text) {
        mContent = text;
        return this;
    }
}
