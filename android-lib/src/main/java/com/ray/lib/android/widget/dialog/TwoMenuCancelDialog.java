package com.ray.lib.android.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ray.lib.android.R;

public class TwoMenuCancelDialog extends Dialog {
    private onMenuClickListener listener;
    private TextView tvMenu2;
    private TextView tvMenu1;
    private TextView tvMenu3;

    public TwoMenuCancelDialog(Context context, String menu1, String menu2, String menu3) {
        super(context, R.style.dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_two_menu_cancel_dialog);
        setCancelable(true);

        tvMenu1 = (TextView) findViewById(R.id.tv_menu1);
        if (tvMenu1 != null) {
            tvMenu1.setText(menu1);
            tvMenu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onMenu1Click();
                    }
                    dismiss();
                }
            });
        }

        tvMenu2 = (TextView) findViewById(R.id.tv_menu2);
        if (tvMenu2 != null) {
            tvMenu2.setText(menu2);
            tvMenu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onMenu2Click();
                    }
                    dismiss();
                }
            });
        }

        tvMenu3 = (TextView) findViewById(R.id.tv_menu3);
        if (tvMenu3 != null) {
            tvMenu3.setText(menu3);
            tvMenu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onMenu3Click();
                    }
                    dismiss();
                }
            });
        }
    }

    public TwoMenuCancelDialog(Activity activity, String menu1, String menu2) {
        this(activity, menu1, menu2, "取消");
    }

    public TwoMenuCancelDialog(final Activity activity) {
        this(activity, "标题", "提示信息");
    }

    public TwoMenuCancelDialog setMenu1(final String text) {
        if (tvMenu1 != null) {
            tvMenu1.setText(text);
        }
        return this;
    }

    public TwoMenuCancelDialog setMenu2(final String text) {
        if (tvMenu2 != null) {
            tvMenu2.setText(text);
        }
        return this;
    }

    public TwoMenuCancelDialog setMenu3(final String text) {
        if (tvMenu3 != null) {
            tvMenu3.setText(text);
        }
        return this;
    }

    public TwoMenuCancelDialog setOnMenuClickListener(onMenuClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface onMenuClickListener {
        void onMenu1Click();

        void onMenu2Click();

        void onMenu3Click();
    }
}
