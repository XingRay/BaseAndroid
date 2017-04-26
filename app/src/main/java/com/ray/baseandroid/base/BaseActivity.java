package com.ray.baseandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isParamsValid(getIntent())) {
            finish();
            return;
        }
        initVariables();
        initView();
        loadData();
    }

    protected boolean isParamsValid(Intent intent) {
        return true;
    }

    protected abstract void initVariables();

    protected abstract void initView();

    protected abstract void loadData();

    protected <T extends View> T findView(int id) {
        View view = findViewById(id);
        if (view == null) {
            throw new IllegalArgumentException("can not find view(id:" + id + ") in activity:" + this.getClass().getSimpleName());
        }
        return (T) view;
    }

    protected void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
