package com.ray.baseandroid.customview;

import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.TraceUtil;

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

    @BindView(R.id.el_layout1)
    CollapsibleLinearLayout elLayout1;

    @BindView(R.id.el_layout2)
    CollapsibleLinearLayout elLayout2;

    @BindView(R.id.tv_text)
    TextView tvText;

    private boolean mIsCollapsed1;
    private boolean mIsCollapsed2;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_view2);
        ButterKnife.bind(this);
        elLayout2.setExpandListener(new CollapsibleLinearLayout.ActionListener() {
            @Override
            public void onStart() {
                TraceUtil.log();
            }

            @Override
            public void onProgress(int value) {
            }

            @Override
            public void onComplete() {
                TraceUtil.log();
                tvText.setVisibility(View.GONE);
            }
        });
        elLayout2.setCollapseListener(new CollapsibleLinearLayout.ActionListener() {
            @Override
            public void onStart() {
                TraceUtil.log();
            }

            @Override
            public void onProgress(int value) {
            }

            @Override
            public void onComplete() {
                TraceUtil.log();
                tvText.setVisibility(View.VISIBLE);
            }
        });
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
        mIsCollapsed1 = !mIsCollapsed1;
        elLayout1.setExpand(mIsCollapsed1);
    }

    private void click2() {
        mIsCollapsed2 = !mIsCollapsed2;
        elLayout2.setExpand(mIsCollapsed2);
    }
}
