package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * @author      : leixing
 * @date        : 2017-07-12
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TitleContentTwoButtonDialog extends Dialog {
    protected final TextView tvContent;
    protected final TextView tvLeft;

    private final TextView tvTitle;
    private final TextView tvRight;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    public TitleContentTwoButtonDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_title_content_two_button);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);

        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftClickListener != null) {
                    mLeftClickListener.onClick(v);
                }
                dismiss();
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightClickListener != null) {
                    mRightClickListener.onClick(v);
                }
                dismiss();
            }
        });
    }

    public TitleContentTwoButtonDialog title(String title) {
        tvTitle.setText(title);
        return this;
    }

    public TitleContentTwoButtonDialog title(@StringRes int redId) {
        tvTitle.setText(redId);
        return this;
    }

    public TitleContentTwoButtonDialog content(String content) {
        tvContent.setText(content);
        return this;
    }

    public TitleContentTwoButtonDialog content(@StringRes int redId) {
        tvContent.setText(redId);
        return this;
    }

    public TitleContentTwoButtonDialog left(String buttonText) {
        tvLeft.setText(buttonText);
        return this;
    }

    public TitleContentTwoButtonDialog left(@StringRes int resId) {
        tvLeft.setText(resId);
        return this;
    }

    public TitleContentTwoButtonDialog leftColor(int color) {
        tvLeft.setTextColor(color);
        return this;
    }

    public TitleContentTwoButtonDialog right(String buttonText) {
        tvRight.setText(buttonText);
        return this;
    }

    public TitleContentTwoButtonDialog right(@StringRes int resId) {
        tvRight.setText(resId);
        return this;
    }

    public TitleContentTwoButtonDialog rightColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    public TitleContentTwoButtonDialog leftClickListener(View.OnClickListener listener) {
        mLeftClickListener = listener;
        return this;
    }

    public TitleContentTwoButtonDialog rightClickListener(View.OnClickListener listener) {
        mRightClickListener = listener;
        return this;
    }
}
