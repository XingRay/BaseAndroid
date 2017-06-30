package com.ray.baseandroid.customview;

import android.view.View;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.widget.MarqueeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017/6/18 22:47
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CustomView2Activity extends BaseActivity {

    @BindView(R.id.mtv_text)
    MarqueeTextView mtvText;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_view2);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_click1, R.id.bt_click2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_click1:
                click1();
                break;
            case R.id.bt_click2:
                click2();
                break;
        }
    }

    private void click1() {
    }

    private void click2() {
    }
}
