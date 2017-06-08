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

public class TitleContentTwoButtonDialogAdapter extends DialogAdapter {

    private CharSequence mTitle;
    private CharSequence mContent;
    private CharSequence mLeftText;
    private CharSequence mRightText;
    private ClickListener mLeftListener;
    private ClickListener mRightListener;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_activity_title_content_two_button;
    }

    @Override
    protected void bindView(View rootView, DialogFragment fragment) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(mTitle);

        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_content);
        textView1.setText(mContent);

        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_left_button);
        textView2.setText(mLeftText);

        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_right_button);
        textView3.setText(mRightText);

        rootView.findViewById(R.id.tv_left_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftListener != null) {
                    mLeftListener.onClick();
                }
                if (isAutoDismiss()) {
                    dismiss();
                }
            }
        });

        rootView.findViewById(R.id.tv_right_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightListener != null) {
                    mRightListener.onClick();
                }
                if (isAutoDismiss()) {
                    dismiss();
                }
            }
        });
    }

    public TitleContentTwoButtonDialogAdapter title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public TitleContentTwoButtonDialogAdapter content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TitleContentTwoButtonDialogAdapter leftText(CharSequence leftButtonText) {
        mLeftText = leftButtonText;
        return this;
    }

    public TitleContentTwoButtonDialogAdapter rightText(CharSequence rightButtonText) {
        mRightText = rightButtonText;
        return this;
    }

    public TitleContentTwoButtonDialogAdapter leftListener(ClickListener listener) {
        mLeftListener = listener;
        return this;
    }

    public TitleContentTwoButtonDialogAdapter rightListener(ClickListener listener) {
        mRightListener = listener;
        return this;
    }
}
