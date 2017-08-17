package com.ray.baseandroid.counter;

import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.viewcounter.ViewCounter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-08-16
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CounterActivity extends BaseActivity {
    @BindView(R.id.tv_count)
    TextView tvCount;
    private ViewCounter mCounter;

    @Override
    protected void initVariables() {
        mCounter = new ViewCounter()
                .from(0)
                .to(Long.MAX_VALUE)
                .count(1)
                .intervalMills(1)
                .countListener(new ViewCounter.CountListener() {
                    @Override
                    public void onCount(long count, boolean hasNext) {
                        tvCount.setText(String.valueOf(count));
                    }
                });
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_counter);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_start, R.id.bt_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                start();
                break;
            case R.id.bt_stop:
                stop();
                break;
        }
    }


    private void start() {
        mCounter.start();
    }

    private void stop() {
        mCounter.stop();
    }
}
