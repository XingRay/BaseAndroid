package com.ray.baseandroid.pages.dialogactivitytest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.util.ViewUtil;
import com.ray.baseandroid.widget.dialogactivity.DialogActivityManager;
import com.ray.baseandroid.widget.dialogactivity.DialogAdapter;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
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
        DialogActivityManager.getInstance().showDialog(this.getApplicationContext(),
                new DialogAdapter()
                        .layoutId(R.layout.layout_dialog_alert)
                        .viewBinder(new DialogAdapter.ViewBinder() {
                            @Override
                            protected void bindView(View rootView, final DialogFragment fragment) {
                                TextView tvMsg = ViewUtil.findView(rootView, R.id.tv_msg);
                                tvMsg.setText("test01 in service");

                                TextView tvConfirm = (TextView) rootView.findViewById(R.id.tv_confirm);

                                tvConfirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        fragment.dismiss();
                                    }
                                });
                            }
                        }));
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
