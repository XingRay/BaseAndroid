package com.ray.baseandroid.intenttest;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.intenttest.data.Follower;
import com.ray.baseandroid.intenttest.data.Person;
import com.ray.lib.android.base.page.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ray.baseandroid.intenttest.IntentSearchActivity.RESULT_SEARCH;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IntentTestActivity extends BaseActivity {
    public static final int REQUEST_CODE_PERSON = 101;
    private static final int REQUEST_CODE_SEARCH = 102;

    @BindView(R.id.bt_test01)
    Button btTest01;

    @BindView(R.id.bt_test02)
    Button btTest02;

    @BindView(R.id.tv_result)
    TextView tvResult;

    private List<Person> mPersons;

    @Override
    protected void initVariables() {
        mPersons = Arrays.asList(
                new Person("alex", 20)
                        .addFollower(new Follower("bob", 21))
                        .addFollower(new Follower("coco", 22))
        );
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_intent_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        showPersons();
    }

    @OnClick({R.id.bt_test01, R.id.bt_test02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_test01:
                gotoReceiverPage1();
                break;
            case R.id.bt_test02:
                gotoReceiverPage2();
                break;
        }
    }

    private void gotoReceiverPage1() {
        IntentReceiverActivity.start(this, REQUEST_CODE_PERSON, mPersons);
    }

    private void gotoReceiverPage2() {
        IntentReceiverActivity.start(this, REQUEST_CODE_SEARCH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PERSON:
                handlePersonResult(resultCode, data);
                break;
            case REQUEST_CODE_SEARCH:
                handleSearchResult(resultCode, data);
                break;
            default:
        }
    }

    private void handlePersonResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            showPersons();
        }
    }

    private void handleSearchResult(int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        String result = data.getStringExtra(RESULT_SEARCH);
        if (!TextUtils.isEmpty(result)) {
            tvResult.setText(result);
        }
    }

    private void showPersons() {
        tvResult.setText(mPersons.toString());
    }
}
