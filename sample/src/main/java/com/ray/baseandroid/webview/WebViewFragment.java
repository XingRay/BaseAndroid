package com.ray.baseandroid.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author      : leixing
 * @date        : 2017-05-31
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : fragment as {@code WebView} container
 */

public class WebViewFragment extends BaseFragment {

    public static final String URL = "url";
    private String mUrl;

    @BindView(R.id.wv_web)
    WebView wvWeb;

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
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, rootView);
        initWebView();
        return rootView;
    }

    private void initWebView() {
        wvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }


        });
    }

    @Override
    protected void loadData() {
        wvWeb.loadUrl(mUrl);
    }
}
