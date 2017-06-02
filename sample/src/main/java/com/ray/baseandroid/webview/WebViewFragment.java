package com.ray.baseandroid.webview;

import android.os.Bundle;
import android.view.View;

import com.ray.lib.android.base.page.BaseFragment;

/**
 * Author      : leixing
 * Date        : 2017-05-31
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : fragment as {@code WebView} container
 */

public class WebViewFragment extends BaseFragment {

    public static final String URL = "url";
    private String mUrl;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle params = new Bundle();
        params.putString(URL, url);
        fragment.setArguments(params);
        return fragment;
    }

    public WebViewFragment() {
    }

    @Override
    protected void initVariables() {
        Bundle arguments = getArguments();
        mUrl = arguments.getString(URL);
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void loadData() {

    }
}
