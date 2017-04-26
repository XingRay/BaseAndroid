package com.ray.baseandroid.pages.dim;

import android.view.WindowManager;
import android.widget.TextView;

import com.ray.mytest.R;
import com.ray.mytest.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author      : leixing
 * Date        : 2017-04-21
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DimActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        setContentView(R.layout.activity_dim);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        tvText.setText("dim activity");
    }
}
