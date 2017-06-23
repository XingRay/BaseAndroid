package com.ray.baseandroid.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.commonlistview.util.RefreshViewCreator;

/**
 * Created by Darren on 2017/1/3.
 * Email: 240336124@qq.com
 * Description: 默认样式的头部刷新
 * 如淘宝、京东、不同的样式可以自己去实现
 */

public class DefaultRefreshCreator extends RefreshViewCreator {
    // 加载数据的ImageView
    private ProgressBar pbProgress;
    private TextView tvHint;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.xlistview_footer, parent, false);
        pbProgress = (ProgressBar) refreshView.findViewById(R.id.xlistview_footer_progressbar);
        tvHint = (TextView) refreshView.findViewById(R.id.xlistview_footer_hint_textview);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        tvHint.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshing() {
        tvHint.setVisibility(View.GONE);
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopRefresh() {
        pbProgress.setVisibility(View.GONE);
    }
}