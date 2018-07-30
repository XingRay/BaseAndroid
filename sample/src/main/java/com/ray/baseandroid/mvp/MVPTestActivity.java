package com.ray.baseandroid.mvp;

import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.base.page.LifeCycleObserver;
import com.ray.lib.android.util.TraceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : leixing
 * @date : 2017-05-25
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MVPTestActivity extends BaseActivity implements MVPTestContract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;
    private MVPTestPresenter mPresenter;

    @Override
    protected void initVariables() {
        mPresenter = new MVPTestPresenter(this);

        addLifeCycleObserver(new LifeCycleObserver() {
            @Override
            public void onCreate() {
                TraceUtil.log();
            }

            @Override
            public void onStart() {
                TraceUtil.log();
            }

            @Override
            public void onResume() {
                TraceUtil.log();
            }

            @Override
            public void onPause() {
                TraceUtil.log();
            }

            @Override
            public void onStop() {
                TraceUtil.log();
            }

            @Override
            public void onDestroy() {
                TraceUtil.log();
                removeLifeCycleObserver(this);
            }
        });
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_mvp_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        mPresenter.loadData();
    }


    @OnClick(R.id.tv_content)
    public void onViewClicked() {
        TraceUtil.log();
    }

    @Override
    public void setContent(String content) {
        tvContent.setText(content);
    }
}
