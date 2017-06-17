package com.ray.baseandroid.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.intenttest.data.Follower;
import com.ray.baseandroid.intenttest.data.Person;
import com.ray.lib.android.base.page.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class IntentReceiverActivity extends BaseActivity {
    public static final String PERSONS = "persons";
    @BindView(R.id.tv_test01)
    TextView tvTest01;
    @BindView(R.id.tv_test02)
    TextView tvTest02;
    private ArrayList<Person> mPersons;

    public static void start(Activity activity, int requestCode, List<Person> persons) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(PERSONS, new ArrayList<>(persons));
        intent.setClass(activity.getApplicationContext(), IntentReceiverActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        mPersons = intent.getParcelableArrayListExtra(PERSONS);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_intent_receiver);
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
                exit();
                break;
            case R.id.tv_test02:
                updatePersons();
                break;
        }
    }

    private void exit() {
        setResult(RESULT_OK);
        finish();
    }

    private void updatePersons() {
        if (mPersons == null) {
            return;
        }

        for (Person person : mPersons) {
            person.setAge(person.getAge() + 1);
            person.setName(person.getName() + "-" + (char) ('a' + person.getAge() % 26));
            List<Follower> followers = person.getFollowers();
            if (followers == null) {
                continue;
            }
            for (Follower follower : followers) {
                follower.setAge(follower.getAge() + 1);
                follower.setName(follower.getName() + "-" + (char) ('a' + follower.getAge() % 26));
            }
        }
        showPersons();
    }

    private void showPersons() {
        tvTest02.setText(mPersons.toString());
    }
}
