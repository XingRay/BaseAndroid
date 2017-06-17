package com.ray.lib.android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * Author      : leixing
 * Date        :  2016-11-17
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ProgressDialog extends Dialog {

    private TextView tvMessage;

    public ProgressDialog(Context context, String msg) {
        super(context, R.style.dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
        setCancelable(true);

        tvMessage = (TextView) findViewById(R.id.tv_message);
        setMessage(msg);
    }

    public ProgressDialog(Context context) {
        this(context, "确定");
    }

    public ProgressDialog setMessage(String msg) {
        if (tvMessage != null) {
            tvMessage.setText(msg);
        }
        return this;
    }
}
