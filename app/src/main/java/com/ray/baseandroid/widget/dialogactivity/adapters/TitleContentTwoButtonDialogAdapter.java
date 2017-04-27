package com.ray.baseandroid.widget.dialogactivity.adapters;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.ray.baseandroid.R;
import com.ray.baseandroid.util.ViewUtil;
import com.ray.baseandroid.widget.dialogactivity.DialogAdapter;
import com.ray.baseandroid.widget.dialogactivity.listener.ClickListener;


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
        ViewUtil.setText(rootView, R.id.tv_title, mTitle);
        ViewUtil.setText(rootView, R.id.tv_content, mContent);
        ViewUtil.setText(rootView, R.id.tv_left_button, mLeftText);
        ViewUtil.setText(rootView, R.id.tv_right_button, mRightText);


        ViewUtil.setOnClickListener(rootView, R.id.tv_left_button, new View.OnClickListener() {
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


        ViewUtil.setOnClickListener(rootView, R.id.tv_right_button, new View.OnClickListener() {
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
