package com.ray.baseandroid.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.util.StringUtil;


/**
 * Created by leixing on 2016/7/23.
 */
public class TitleHintInputTwoButtonDialog extends Dialog {
    private OnButtonClickListener listener;
    private TextView tvHintText;
    private TextView tvTitle;
    private TextView tvLeftButton;
    private TextView tvRightButton;
    private EditText etInput;
    private View inputUnderLine;

    public TitleHintInputTwoButtonDialog(Context context) {
        this(context, "提示信息");
    }

    public TitleHintInputTwoButtonDialog(Context context, String hint) {
        this(context, hint, "温馨提示");
    }

    public TitleHintInputTwoButtonDialog(Context context, String hint, String title) {
        this(context, title, hint, "输入信息");
    }

    public TitleHintInputTwoButtonDialog(Context context, String hint, String title, String input) {
        this(context, title, hint, input, "取消", "确定");
    }

    public TitleHintInputTwoButtonDialog(Context context, String hint, String title, String input, String leftButtonText, String rightButtonText) {
        super(context, R.style.dialog_style);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_title_hint_input_two_button);
        setCancelable(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvHintText = (TextView) findViewById(R.id.tv_hint_text);
        etInput = (EditText) findViewById(R.id.et_input);
        inputUnderLine = findViewById(R.id.v_input_under_line);
        tvLeftButton = (TextView) findViewById(R.id.tv_left_button);
        tvRightButton = (TextView) findViewById(R.id.tv_right_button);

        tvTitle.setText(title);
        tvHintText.setHint(hint);
        etInput.setText(input);
        tvLeftButton.setText(leftButtonText);
        tvRightButton.setText(rightButtonText);

        tvLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLeftButtonClick(getInputText());
                }
                dismiss();
            }
        });

        tvRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRightButtonClick(getInputText());
                }
                dismiss();
            }
        });
    }

    private String getInputText() {
        Editable text = etInput.getText();
        if (text == null) {
            return "";
        }

        return StringUtil.nullToEmpty(text.toString()).trim();
    }

    public TitleHintInputTwoButtonDialog setRightButtonText(String rightButtonText) {
        if (tvRightButton != null) {
            tvRightButton.setText(rightButtonText);
        }
        return this;
    }

    public TitleHintInputTwoButtonDialog setLeftButtonText(String leftButtonText) {
        if (tvLeftButton != null) {
            tvLeftButton.setText(leftButtonText);
        }
        return this;
    }

    public TitleHintInputTwoButtonDialog setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        return this;
    }

    public TitleHintInputTwoButtonDialog setHintText(String title) {
        if (tvHintText != null) {
            tvHintText.setText(title);
        }
        return this;
    }

    public TitleHintInputTwoButtonDialog setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onLeftButtonClick(String inputText);

        void onRightButtonClick(String inputText);
    }
}
