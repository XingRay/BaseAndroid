package com.ray.baseandroid.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.baseandroid.intenttest.data.Follower;
import com.ray.baseandroid.intenttest.data.Person;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.java.util.CollectionUtil;
import com.ray.lib.java.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ray.baseandroid.intenttest.IntentSearchActivity.RESULT_SEARCH;

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
    public static final int REQUEST_CODE_SEARCH = 103;

    @BindView(R.id.bt_test01)
    Button btTest01;

    @BindView(R.id.bt_test02)
    Button btTest02;

    @BindView(R.id.bt_test03)
    Button btTest03;

    @BindView(R.id.tv_result)
    TextView tvResult;

    private ArrayList<Person> mPersons;

    public static void start(Activity activity, int requestCode, List<Person> persons) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(PERSONS, new ArrayList<>(persons));
        intent.setClass(activity.getApplicationContext(), IntentReceiverActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity.getApplicationContext(), IntentReceiverActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected boolean isParamsValid(Intent intent) {
        mPersons = intent.getParcelableArrayListExtra(PERSONS);
        return super.isParamsValid(intent);
    }

    @Override
    protected void initVariables() {

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

    @OnClick({R.id.bt_test01, R.id.bt_test02, R.id.bt_test03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_test01:
                exit();
                break;
            case R.id.bt_test02:
                updatePersons();
                break;
            case R.id.bt_test03:
                gotoSearchPage();
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SEARCH) {
            handleSearchResult(resultCode, data);
        }
    }

    private void handleSearchResult(int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        String result = data.getStringExtra(RESULT_SEARCH);
        if (TextUtil.isEmpty(result)) {
            return;
        }

        setResult(RESULT_OK, data);
        finish();
    }

    private void gotoSearchPage() {
        IntentSearchActivity.start(this, REQUEST_CODE_SEARCH);
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
        if (!CollectionUtil.isEmpty(mPersons)) {
            tvResult.setText(mPersons.toString());
        }
    }
}
