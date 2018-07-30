package com.ray.baseandroid.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author      : leixing
 * @date        : 2017-07-12
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IntentSearchActivity extends BaseActivity {
    public static final String RESULT_SEARCH = "search";
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_click)
    Button btClick;

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, IntentSearchActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_intent_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_click:
                confirm();
                break;
        }
    }

    private void confirm() {
        String result = etInput.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra(RESULT_SEARCH, result);
        setResult(RESULT_OK, intent);
        finish();
    }
}
