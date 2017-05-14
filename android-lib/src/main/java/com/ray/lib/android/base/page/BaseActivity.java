package com.ray.lib.android.base.page;

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
 * Description : activity的基类
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

    /**
     * 根据调用activity的intent所携带的参数，判断activity是否可以显示
     *
     * @param intent 启动activity的参数
     * @return activity是否可以显示
     */
    protected boolean isParamsValid(Intent intent) {
        return true;
    }

    /**
     * 初始化变量， 如presenter，adapter，数据列表等
     */
    protected abstract void initVariables();

    /**
     * 初始化控件，在这个方法中调用{@link android.app.Activity#setContentView(int)}设置布局， 绑定布局(通过
     * {@link android.app.Activity#findViewById(int)}或者ButterKnife{@link <a href="https://github.com/JakeWharton/butterknife"/>})。
     * 及设置监听器。
     */
    protected abstract void initView();

    /**
     * 载入数据，从服务器或者本地获取数据，然后展示在页面中。
     */
    protected abstract void loadData();

    /**
     * @param id  资源id
     * @param <T> 控件的类型参数
     * @return 控件对象
     */
    @SuppressWarnings("unchecked")
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
