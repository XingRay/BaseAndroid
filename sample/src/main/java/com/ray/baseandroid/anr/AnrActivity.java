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
 * Author      : leixing
 * Date        : 2017-07-21
 * Email       : leixing@hecom.cn
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
        for(int i=0; i<Integer.MAX_VALUE; i++){
            int k = 123*321;
            int l = 123/321;
            /*for(int j=0; j<Integer.MAX_VALUE; j++){

            }*/
        }

        tvResult.setText("loaded");
    }
}
