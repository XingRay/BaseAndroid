package com.ray.baseandroid.multiprocess;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import com.ray.baseandroid.IMultiProcessService;
import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.AppUtil;
import com.ray.lib.android.util.ToastUtil;
import com.ray.lib.android.util.TraceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author      : leixing
 * @date        : 2017-05-22
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiProcessActivity extends BaseActivity {
    @BindView(R.id.et_uid)
    EditText etUid;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_data)
    EditText etData;

    private IMultiProcessService mMultiProcessService;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_multi_process);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        String uid = MultiProcessCache.getInstance().getUid();
        String name = MultiProcessCache.getInstance().getName();
        String data = MultiProcessCache.getInstance().getData();

        etUid.setText(uid);
        etName.setText(name);
        etData.setText(data);

        String processName = AppUtil.getProcessName(mContext);
        ToastUtil.showToast(this, processName);
    }

    @OnClick({R.id.tv_load, R.id.tv_save, R.id.start, R.id.bind, R.id.unbind, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_load:
                load();
                break;
            case R.id.tv_save:
                save();
                break;
            case R.id.start:
                start();
                break;
            case R.id.bind:
                bind();
                break;
            case R.id.unbind:
                unbind();
                break;
            case R.id.stop:
                stop();
                break;
        }
    }

    private void load() {
        loadData();
    }

    private void save() {
        if (mMultiProcessService == null) {
            TraceUtil.log("save to local");
            MultiProcessCache.getInstance().setUid(etUid.getText().toString().trim());
            MultiProcessCache.getInstance().setName(etName.getText().toString().trim());
            MultiProcessCache.getInstance().setData(etData.getText().toString().trim());
            ToastUtil.showToast(this, "save to local");
        } else {
            TraceUtil.log("save to remote");
            try {
                mMultiProcessService.setName(etName.getText().toString().trim());
                ToastUtil.showToast(this, "save to remote");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void bind() {
        TraceUtil.log();
        Intent intent = new Intent(this, MultiProcessService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    private void unbind() {
        TraceUtil.log();
        unbindService(mServiceConnection);
    }

    private void start() {
        TraceUtil.log();
        Intent intent = new Intent();
        intent.setClass(this, MultiProcessService.class);
        startService(intent);
    }

    private void stop() {
        TraceUtil.log();
        Intent intent = new Intent();
        intent.setClass(this, MultiProcessService.class);
        stopService(intent);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TraceUtil.log();
            mMultiProcessService = IMultiProcessService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMultiProcessService = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind();
    }
}
