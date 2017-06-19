package com.ray.baseandroid.dialogactivitytest;

import android.app.Activity;
import android.content.Intent;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-06-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class BigMemoryActivity extends BaseActivity {

    private List<int[]> mArrays;

    public static void start(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, BigMemoryActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        mArrays = new ArrayList<>();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_big_memory);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.bt_load)
    public void onViewClicked() {
        loadBigMemory();
    }

    private void loadBigMemory() {
        mArrays.add(new int[1024 * 1024]);
        ToastUtil.showToast(this, "memory : " + mArrays.size() + "M");
    }
}
