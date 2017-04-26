package com.ray.baseandroid.widget.dialogactivity.adapters;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.ray.baseandroid.R;
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

public class TopImgBottomBtnDialogAdapter extends DialogAdapter {
    private CharSequence mContent;
    private CharSequence mButtonText;
    private int mImgResId;
    private ClickListener mListener;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_top_image_bottom_button;
    }

    @Override
    protected void bindView(View rootView, DialogFragment fragment) {
        super.bindView(rootView, fragment);

        ViewUtil.setImgRes(rootView, R.id.iv_top, mImgResId);
        ViewUtil.setText(rootView, R.id.tv_desc, mContent);
        ViewUtil.setText(rootView, R.id.tv_btn, mButtonText);


        ViewUtil.setOnClickListener(rootView, R.id.tv_btn, new View.OnClickListener() {
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



        /*Window window = fragment.getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);*/
    }

    public TopImgBottomBtnDialogAdapter content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TopImgBottomBtnDialogAdapter buttonText(CharSequence buttonText) {
        mButtonText = buttonText;
        return this;
    }

    public TopImgBottomBtnDialogAdapter imgResId(int imgResId) {
        mImgResId = imgResId;
        return this;
    }

    public TopImgBottomBtnDialogAdapter listener(ClickListener listener) {
        mListener = listener;
        return this;
    }
}
