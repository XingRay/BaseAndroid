package com.ray.baseandroid.webview;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.java.util.TextUtil;

import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author      : leixing
 * @date        : 2017-09-06
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class WebTestActivity extends BaseActivity {
    @BindView(R.id.et_url)
    EditText etUrl;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.bt_click)
    public void onViewClicked() {
        openUrl();
    }

    private void openUrl() {
        String url = etUrl.getText().toString().trim();
        if (TextUtil.isEmpty(url)) {
            return;
        }
        WebViewActivity.start(this, url);
    }
}
