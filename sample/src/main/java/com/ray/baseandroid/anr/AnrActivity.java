package com.ray.baseandroid.anr;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @@author      : leixing
 * @@date        : 2017-07-21
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class AnrActivity extends BaseActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.bt_load)
    Button btLoad;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_anr_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                load();
                break;
        }
    }

    private void load() {
        tvResult.setText("loading");
        tvResult.postDelayed(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                while (true) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long a = 12345567;
                    long b = a * (a - 100) / 654321;
                    long c = (b + a) * (b - a - 100) / 1234 / 321;
                    // loop for anr
                    if (currentTimeMillis - start > 20 * 1000) {
                        break;
                    }
                }

                tvResult.setText("loaded");
            }
        }, 500);
    }
}
