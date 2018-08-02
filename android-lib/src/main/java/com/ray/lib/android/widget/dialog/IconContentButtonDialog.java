package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * @@author      : leixing
 * @@date        : 2017-06-30
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IconContentButtonDialog extends Dialog {

    protected final ImageView ivImg;
    protected final TextView tvContent;
    protected final TextView tvButton;
    private View.OnClickListener mButtonClickListener;

    public IconContentButtonDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_icon_content_button);
        ivImg = (ImageView) findViewById(R.id.iv_img);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvButton = (TextView) findViewById(R.id.tv_button);

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonClickListener != null) {
                    mButtonClickListener.onClick(v);
                }
                dismiss();
            }
        });
    }

    public IconContentButtonDialog icon(@DrawableRes int resId) {
        ivImg.setImageResource(resId);
        return this;
    }

    public IconContentButtonDialog content(String content) {
        tvContent.setText(content);
        return this;
    }

    public IconContentButtonDialog content(int redId) {
        tvContent.setText(redId);
        return this;
    }

    public IconContentButtonDialog button(String buttonText) {
        tvButton.setText(buttonText);
        return this;
    }

    public IconContentButtonDialog button(int resId) {
        tvButton.setText(resId);
        return this;
    }

    public IconContentButtonDialog buttonClickListener(View.OnClickListener listener) {
        mButtonClickListener = listener;
        return this;
    }
}
