package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * @@author      : leixing
 * @@date        : 2017-07-12
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IconTitleContentTwoButtonDialog extends Dialog {
    protected final ImageView ivIcon;
    protected final TextView tvContent;
    protected final TextView tvLeft;
    private View.OnClickListener mLeftClickListener;
    private final TextView tvTitle;
    private final TextView tvRight;
    private View.OnClickListener mRightClickListener;

    public IconTitleContentTwoButtonDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_icon_title_content_two_button);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
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

    public IconTitleContentTwoButtonDialog icon(@DrawableRes int resId) {
        ivIcon.setImageResource(resId);
        return this;
    }

    public IconTitleContentTwoButtonDialog title(String title) {
        tvTitle.setText(title);
        return this;
    }

    public IconTitleContentTwoButtonDialog title(@StringRes int redId) {
        tvTitle.setText(redId);
        return this;
    }

    public IconTitleContentTwoButtonDialog content(String content) {
        tvContent.setText(content);
        return this;
    }

    public IconTitleContentTwoButtonDialog content(@StringRes int redId) {
        tvContent.setText(redId);
        return this;
    }

    public IconTitleContentTwoButtonDialog left(String buttonText) {
        tvLeft.setText(buttonText);
        return this;
    }

    public IconTitleContentTwoButtonDialog left(@StringRes int resId) {
        tvLeft.setText(resId);
        return this;
    }

    public IconTitleContentTwoButtonDialog leftColor(int color) {
        tvLeft.setTextColor(color);
        return this;
    }

    public IconTitleContentTwoButtonDialog right(String buttonText) {
        tvRight.setText(buttonText);
        return this;
    }

    public IconTitleContentTwoButtonDialog right(@StringRes int resId) {
        tvRight.setText(resId);
        return this;
    }

    public IconTitleContentTwoButtonDialog rightColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    public IconTitleContentTwoButtonDialog leftClickListener(View.OnClickListener listener) {
        mLeftClickListener = listener;
        return this;
    }

    public IconTitleContentTwoButtonDialog rightClickListener(View.OnClickListener listener) {
        mRightClickListener = listener;
        return this;
    }
}
