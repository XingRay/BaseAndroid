package com.ray.baseandroid.alarm;

import android.content.Intent;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author      : leixing
 * Date        : 2017-06-05
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class AlarmActivity extends BaseActivity {

    @BindView(R.id.tv_notification)
    TextView tvNotification;
    private String mNotification;

    @Override
    protected boolean isParamsValid(Intent intent) {
        mNotification = intent.getStringExtra("notification");
        return super.isParamsValid(intent);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        tvNotification.setText(mNotification);
    }
}
