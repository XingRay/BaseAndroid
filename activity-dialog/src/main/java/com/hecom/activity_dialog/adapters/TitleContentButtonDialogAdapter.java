package com.hecom.activity_dialog.adapters;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.hecom.activity_dialog.DialogAdapter;
import com.hecom.activity_dialog.R;
import com.hecom.activity_dialog.listener.ClickListener;


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

        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(mTitle);

        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_content);
        textView1.setText(mContent);

        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_button);
        textView2.setText(mButtonText);

        rootView.findViewById(R.id.tv_button).setOnClickListener(new View.OnClickListener() {
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
