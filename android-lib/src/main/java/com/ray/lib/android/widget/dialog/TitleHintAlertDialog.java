package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ray.lib.android.R;

public class TitleHintAlertDialog extends Dialog {
    private OnButtonClickListener listener;
    private TextView tvHintText;
    private TextView tvTitle;
    private TextView tvButton;

    public TitleHintAlertDialog(Context context) {
        this(context, "提示信息");
    }

    public TitleHintAlertDialog(Context context, String hint) {
        this(context, hint, "温馨提示");
    }

    public TitleHintAlertDialog(Context context, String hint, String title) {
        this(context, title, hint, "确定");
    }

    public TitleHintAlertDialog(Context context, String title, String hint, String buttonText) {
        super(context, R.style.dialog_style);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_title_hint_alert);
        this.setCancelable(true);

        tvTitle = (TextView) this.findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }

        tvHintText = (TextView) this.findViewById(R.id.tv_hint_text);
        if (tvHintText != null) {
            tvHintText.setText(hint);
        }

        tvButton = (TextView) this.findViewById(R.id.tv_button);
        if (tvButton != null) {
            tvButton.setText(buttonText);
        }

        if (tvButton != null) {
            tvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onButtonClick();
                    }
                    dismiss();
                }
            });
        }
    }

    public TitleHintAlertDialog setButtonText(String buttonText) {
        if (tvButton != null) {
            tvButton.setText(buttonText);
        }
        return this;
    }

    public TitleHintAlertDialog setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        return this;
    }

    public TitleHintAlertDialog setHintText(String hint) {
        if (tvHintText != null) {
            tvHintText.setText(hint);
        }
        return this;
    }

    public TitleHintAlertDialog setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }
}
