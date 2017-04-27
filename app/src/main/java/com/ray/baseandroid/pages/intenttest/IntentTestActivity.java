package com.ray.baseandroid.pages.intenttest;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.base.BaseActivity;
import com.ray.baseandroid.pages.intenttest.data.Follower;
import com.ray.baseandroid.pages.intenttest.data.Person;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IntentTestActivity extends BaseActivity {
    public static final int REQUEST_CODE_PERSON = 101;
    @BindView(R.id.tv_test01)
    TextView tvTest01;
    @BindView(R.id.tv_test02)
    TextView tvTest02;
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

    @OnClick({R.id.tv_test01, R.id.tv_test02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_test01:
                gotoReceiverPage();
                break;
            case R.id.tv_test02:
                break;
        }
    }

    private void gotoReceiverPage() {
        IntentReceiverActivity.start(this, REQUEST_CODE_PERSON, mPersons);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PERSON) {
            if (resultCode == RESULT_OK) {
                showPersons();
            }
        }
    }

    private void showPersons() {
        tvTest02.setText(mPersons.toString());
    }
}
