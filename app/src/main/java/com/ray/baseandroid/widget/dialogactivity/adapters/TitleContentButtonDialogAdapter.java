package com.ray.baseandroid.widget.dialogactivity.adapters;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.hecom.R;
import com.hecom.util.ViewUtil;
import com.hecom.widget.dialogactivity.DialogAdapter;
import com.hecom.widget.dialogactivity.listener.ClickListener;

/**
 * Author      : leixing
 * Date        : 2017-04-17
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TitleContentButtonDialogAdapter extends DialogAdapter {
    private CharSequence mTitle;
    private CharSequence mContent;
    private CharSequence mButtonText;
    private ClickListener mListener;

    public TitleContentButtonDialogAdapter() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_title_content_button;
    }

    @Override
    protected void bindView(View rootView, DialogFragment fragment) {
        super.bindView(rootView, fragment);

        ViewUtil.setText(rootView, R.id.tv_title, mTitle);
        ViewUtil.setText(rootView, R.id.tv_content, mContent);
        ViewUtil.setText(rootView, R.id.tv_button, mButtonText);


        ViewUtil.setOnClickListener(rootView, R.id.tv_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick();
                }
                if (isAutoDismiss()) {
                    dismiss();
                }
            }
        });
    }

    public TitleContentButtonDialogAdapter title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public TitleContentButtonDialogAdapter content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TitleContentButtonDialogAdapter buttonText(CharSequence buttonText) {
        mButtonText = buttonText;
        return this;
    }

    public TitleContentButtonDialogAdapter clickListener(ClickListener listener) {
        mListener = listener;
        return this;
    }
}
