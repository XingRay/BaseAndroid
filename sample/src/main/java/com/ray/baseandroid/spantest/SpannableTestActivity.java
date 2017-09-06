package com.ray.baseandroid.spantest;

import android.widget.EditText;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-09-06
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SpannableTestActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.et_regex)
    EditText etRegex;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_spannable_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.bt_click)
    public void onViewClicked() {
        setSpannableText();
    }

    private void setSpannableText() {
        String raw = etText.getText().toString().trim();
        String regex = etRegex.getText().toString().trim();
        tvText.setText(ViewUtil.scaleText(raw, regex, 1.5f));
    }
}
