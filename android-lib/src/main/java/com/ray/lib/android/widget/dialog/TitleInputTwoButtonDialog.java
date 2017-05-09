package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.ray.lib.android.R;


public class TitleInputTwoButtonDialog extends Dialog {
    private OnButtonClickListener listener;
    private EditText etInputText;
    private TextView tvTitle;
    private TextView tvLeftButton;
    private TextView tvRightButton;

    public TitleInputTwoButtonDialog(Context context) {
        this(context, "温馨提示");
    }

    public TitleInputTwoButtonDialog(Context context, String title) {
        this(context, title, "取消", "确认");
    }

    public TitleInputTwoButtonDialog(Context context, String title, String leftButtonText, String rightButtonText) {
        super(context, R.style.dialog_style);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_title_input_two_button);
        setCancelable(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        etInputText = (EditText) findViewById(R.id.et_input_text);
        tvLeftButton = (TextView) findViewById(R.id.tv_left_button);
        tvRightButton = (TextView) findViewById(R.id.tv_right_button);

        tvTitle.setText(title);
        tvLeftButton.setText(leftButtonText);
        tvRightButton.setText(rightButtonText);

        if (tvLeftButton != null) {
            tvLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onLeftButtonClick();
                    }
                    dismiss();
                }
            });
        }

        if (tvRightButton != null) {
            tvRightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onRightButtonClick();
                    }
                    dismiss();
                }
            });
        }
    }

    public void setRightButtonText(String rightButtonText) {
        if (tvRightButton != null) {
            tvRightButton.setText(rightButtonText);
        }
    }

    public void setLeftButtonText(String leftButtonText) {
        if (tvLeftButton != null) {
            tvLeftButton.setText(leftButtonText);
        }
    }

    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    public String getInputText() {
        if (etInputText != null) {
            return etInputText.getText().toString().trim();
        }

        return "";
    }

    public TitleInputTwoButtonDialog setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onLeftButtonClick();

        void onRightButtonClick();
    }
}
