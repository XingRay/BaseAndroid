package com.ray.baseandroid.mvp;

import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.mvp.BaseMvpActivity;
import com.ray.lib.android.util.TraceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-05-25
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MVPTestActivity extends BaseMvpActivity<MVPTestContract.Presenter> implements MVPTestContract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected MVPTestContract.Presenter newPresenter() {
        return new MVPTestPresenter(this);
    }

    @Override
    protected void initVariables() {

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
