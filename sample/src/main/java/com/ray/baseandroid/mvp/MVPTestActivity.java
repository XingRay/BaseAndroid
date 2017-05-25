package com.ray.baseandroid.mvp;

import com.ray.lib.android.base.page.mvp.BaseMvpActivity;
import com.ray.lib.android.base.page.mvp.BasePresenter;

/**
 * Author      : leixing
 * Date        : 2017-05-25
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MVPTestActivity extends BaseMvpActivity implements MVPTestContract.View {
    @Override
    protected BasePresenter<?> newPresenter() {
        return new MVPTestPresenter(this);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
