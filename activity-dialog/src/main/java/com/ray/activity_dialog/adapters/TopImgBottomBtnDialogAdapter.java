package com.ray.activity_dialog.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.activity_dialog.DialogAdapter;
import com.ray.activity_dialog.R;
import com.ray.activity_dialog.listener.ClickListener;


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
    protected void bindView(View rootView) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_top);
        imageView.setImageResource(mImgResId);

        TextView textView = (TextView) rootView.findViewById(R.id.tv_desc);
        textView.setText(mContent);

        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_btn);
        textView1.setText(mButtonText);

        rootView.findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
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
