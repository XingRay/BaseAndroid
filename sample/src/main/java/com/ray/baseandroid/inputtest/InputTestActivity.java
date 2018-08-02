package com.ray.baseandroid.inputtest;


import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.detector.keyboard.KeyBoardStateDetector;
import com.ray.lib.android.detector.keyboard.KeyBoardStateListener;
import com.ray.lib.android.util.TraceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @@author      : leixing
 * @@date        : 2017-04-25
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class InputTestActivity extends BaseActivity implements KeyBoardStateListener {
    @BindView(R.id.tv_text)
    TextView tvText;
    private KeyBoardStateDetector mKeyBoardStateDetector;

    @Override
    protected void initVariables() {
        mKeyBoardStateDetector = new KeyBoardStateDetector();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_input_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        mKeyBoardStateDetector.detect(this, this);
    }

    @Override
    public void onPopup() {
        TraceUtil.log();
    }

    @Override
    public void onDismiss() {
        TraceUtil.log();
    }

    @OnClick(R.id.tv_text)
    public void onViewClicked() {
        TraceUtil.log();
    }
}
