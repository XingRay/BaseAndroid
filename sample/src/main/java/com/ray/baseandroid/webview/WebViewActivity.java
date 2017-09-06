package com.ray.baseandroid.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.ButterKnife;

/**
 * Author      : leixing
 * Date        : 2017-05-31
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class WebViewActivity extends BaseActivity {

    public static final String URL = "url";
    private FragmentManager mFragmentManager;
    private String mUrl;

    public static void start(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra(URL, url);
        intent.setClass(context, WebViewActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected boolean isParamsValid(Intent intent) {
        mUrl = intent.getStringExtra(URL);
        return true;
    }

    @Override
    protected void initVariables() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        WebViewFragment fragment = (WebViewFragment) mFragmentManager.findFragmentById(R.id.fl_webview);
        if (fragment == null) {
            fragment = WebViewFragment.newInstance(mUrl);
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_webview, fragment)
                    .commit();
        }
    }

    @Override
    protected void loadData() {

    }
}
