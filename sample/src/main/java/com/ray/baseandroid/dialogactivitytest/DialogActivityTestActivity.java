package com.ray.baseandroid.dialogactivitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DialogActivityTestActivity extends BaseActivity {
    @BindView(R.id.tv_test01)
    TextView tvTest01;
    @BindView(R.id.tv_test02)
    TextView tvTest02;
    private Context mContext;

    @Override
    protected void initVariables() {
        mContext = this.getApplicationContext();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dialog_activity_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startTestService() {
        Intent intent = new Intent();
        intent.setClass(mContext, DialogActivityTestService.class);
        startService(intent);
    }

    @OnClick({R.id.tv_test01, R.id.tv_test02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test01:
                test01();
                break;
            case R.id.tv_test02:
                test02();
                break;
        }
    }

    private void test01() {
        startTestService();
    }

    private void test02() {

    }
}
