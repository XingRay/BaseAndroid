package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ray.lib.android.R;


public class TitleContentButtonDialog extends Dialog {
    private OnButtonClickListener mListener;
    private TextView tvContent;
    private TextView tvTitle;
    private TextView tvButton;

    public TitleContentButtonDialog(Context context, String title, String content, String buttonText) {
        super(context/*, R.style.dialog_style*/);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_title_content_button);
        setCancelable(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        tvContent = (TextView) findViewById(R.id.tv_content);
        if (tvContent != null) {
            tvContent.setText(content);
        }

        tvButton = (TextView) findViewById(R.id.tv_button);
        if (tvButton != null) {
            tvButton.setText(buttonText);
        }

        if (tvButton != null) {
            tvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onButtonClick();
                    }
                    dismiss();
                }
            });
        }
    }

    public TitleContentButtonDialog(Context context, String title, String hint) {
        this(context, title, hint, "确定");
    }

    public TitleContentButtonDialog(Context context) {
        this(context, "标题", "提示信息");
    }

    public TitleContentButtonDialog setButtonText(String buttonText) {
        if (tvButton != null) {
            tvButton.setText(buttonText);
        }
        return this;
    }

    public TitleContentButtonDialog setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        return this;
    }

    public TitleContentButtonDialog setContent(String content) {
        if (tvContent != null) {
            tvContent.setText(content);
        }
        return this;
    }

    public TitleContentButtonDialog setOnButtonClickListener(OnButtonClickListener listener) {
        this.mListener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }
}
