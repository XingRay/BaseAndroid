package com.ray.baseandroid.oom;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;


/**
 * @author      : leixing
 * @date        : 2017-04-19
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class OOMTestActivity extends BaseActivity implements View.OnClickListener {

    public static final String NUM = "num";
    private int[] cache;
    private int num;
    private TextView tvNum;

    public static void start(Activity activity, int no) {
        Intent intent = new Intent();
        intent.putExtra(NUM, no);
        intent.setClass(activity, OOMTestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        cache = new int[1024 * 1024 * 4];
        num = getIntent().getIntExtra(NUM, 0);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_oom_test);
        tvNum = findViewById(R.id.tv_num);
        tvNum.setText("" + num);
        tvNum.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        if (v == tvNum) {
            nextPage();
        }
    }

    private void nextPage() {
        OOMTestActivity.start(this, ++num);
    }
}
