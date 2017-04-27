package com.ray.baseandroid.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ray.baseandroid.R;

/**
 * Created by leixing on 2016/7/23.
 */
public class TitleHintTwoButtonDialog extends Dialog {
    private OnButtonClickListener listener;
    private TextView tvHintText;
    private TextView tvTitle;
    private TextView tvLeftButton;
    private TextView tvRightButton;

    public TitleHintTwoButtonDialog(Context context) {
        this(context, "提示信息");
    }

    public TitleHintTwoButtonDialog(Context context, String hint) {
        this(context, hint, "温馨提示");
    }


    public TitleHintTwoButtonDialog(Context context, String hint, String title) {
        this(context, hint, title, "取消", "确认");
    }

    public TitleHintTwoButtonDialog(Context context, String hint, String title, String leftButtonText, String rightButtonText) {
        super(context, R.style.dialog_style);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_title_hint_two_button);
        setCancelable(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvHintText = (TextView) findViewById(R.id.tv_hint_text);
        tvLeftButton = (TextView) findViewById(R.id.tv_left_button);
        tvRightButton = (TextView) findViewById(R.id.tv_right_button);

        tvTitle.setText(title);
        tvHintText.setHint(hint);
        tvLeftButton.setText(leftButtonText);
        tvRightButton.setText(rightButtonText);

        tvLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLeftButtonClick();
                }
                dismiss();
            }
        });

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

    public TitleHintTwoButtonDialog setRightButtonText(String rightButtonText) {
        if (tvRightButton != null) {
            tvRightButton.setText(rightButtonText);
        }
        return this;
    }

    public TitleHintTwoButtonDialog setLeftButtonText(String leftButtonText) {
        if (tvLeftButton != null) {
            tvLeftButton.setText(leftButtonText);
        }
        return this;
    }

    public TitleHintTwoButtonDialog setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        return this;
    }

    public TitleHintTwoButtonDialog setHintText(String title) {
        if (tvHintText != null) {
            tvHintText.setText(title);
        }
        return this;
    }

    public TitleHintTwoButtonDialog setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onLeftButtonClick();

        void onRightButtonClick();
    }
}
