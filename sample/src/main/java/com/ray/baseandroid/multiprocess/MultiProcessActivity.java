package com.ray.baseandroid.multiprocess;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-05-22
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiProcessActivity extends BaseActivity {
    @BindView(R.id.tv_uid)
    TextView tvUid;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_data)
    TextView tvData;

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

        tvUid.setText(uid);
        tvName.setText(name);
        tvData.setText(data);
    }

    @OnClick({R.id.start, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                start();
                break;
            case R.id.stop:
                stop();
                break;
        }
    }

    private void start() {
        Intent intent = new Intent();
        intent.setClass(this, MultiProcessService.class);
        startService(intent);
    }

    private void stop() {
        Intent intent = new Intent();
        intent.setClass(this, MultiProcessService.class);
        stopService(intent);
    }
}
