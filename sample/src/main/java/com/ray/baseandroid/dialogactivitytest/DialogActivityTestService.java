package com.ray.baseandroid.dialogactivitytest;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.util.ViewUtil;
import com.xingray.activitydialog.ActivityDialog;
import com.xingray.activitydialog.DialogAdapter;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DialogActivityTestService extends Service {
    private static final String TAG = "DialogService";
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mContext = this.getApplicationContext();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        test01();
        return super.onStartCommand(intent, flags, startId);
    }

    private void test01() {
        new ActivityDialog(this.getApplicationContext())
                .cancelable(true)
                .name("test")
                .width(ViewGroup.LayoutParams.MATCH_PARENT)
                .height(ViewGroup.LayoutParams.WRAP_CONTENT)
                .priority(10)
                .Adapter(new DialogAdapter() {
                    @Override
                    protected int getLayoutId() {
                        return R.layout.layout_dialog_alert;
                    }

                    @Override
                    protected void bindView(View rootView) {
                        TextView tvMsg = ViewUtil.findView(rootView, R.id.tv_msg);
                        tvMsg.setText("test01 in service");

                        TextView tvConfirm = (TextView) rootView.findViewById(R.id.tv_confirm);
                        tvConfirm.setText("ok");

                        tvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dismiss();
                                Log.i("test", "onClick");
                            }
                        });
                    }
                })
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Log.i("test", "onShow");
                    }
                })
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Log.i("test", "onDismiss");
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.i("test", "onCancel");
                    }
                })
                .show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

}
