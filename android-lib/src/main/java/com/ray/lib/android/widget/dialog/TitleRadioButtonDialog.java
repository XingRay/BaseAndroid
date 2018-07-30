package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TitleRadioButtonDialog extends Dialog {

    private final TextView tvTitle;
    private final RadioButton rbHint;
    private OnButtonClickListener mListener;

    public TitleRadioButtonDialog(Context context) {
        this(context, "已经确认");
    }

    public TitleRadioButtonDialog(Context context, String radioText) {
        this(context, "温馨提示", radioText);
    }

    public TitleRadioButtonDialog(Context context, String title, String radioText) {
        this(context, title, radioText, "确定");
    }

    public TitleRadioButtonDialog(Context context, String title, String radioText, String buttonText) {
        super(context, R.style.dialog);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_title_radio_button);
        this.setCancelable(true);

        tvTitle = (TextView) this.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        rbHint = (RadioButton) this.findViewById(R.id.rb_hint);
        rbHint.setText(radioText);
        rbHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbHint.toggle();
            }
        });

        TextView tvButton = (TextView) this.findViewById(R.id.tv_button);
        tvButton.setText(buttonText);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onButtonClick(rbHint.isChecked());
                }
                dismiss();
            }
        });
    }

    public TitleRadioButtonDialog setOnButtonClickListener(OnButtonClickListener listener) {
        mListener = listener;
        return this;
    }

    public interface OnButtonClickListener {
        void onButtonClick(boolean isCheck);
    }
}
