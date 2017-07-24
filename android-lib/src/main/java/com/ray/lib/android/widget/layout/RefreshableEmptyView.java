package com.ray.lib.android.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * Author      : leixing
 * Date        : 2017-07-03
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RefreshableEmptyView extends FrameLayout {
    // --- default values --- //
    private static final int DEFAULT_CONTENT_COLOR = 0xff333333;
    private static final int DEFAULT_CONTENT_SIZE = 12;
    private static final int DEFAULT_REFRESH_COLOR = 0xff333333;
    private static final int DEFAULT_REFRESH_SIZE = 12;
    public static final int DEFAULT_REFRESH_BACKGROUND_COLOR = 0xff50a2ff;

    private RefreshListener mRefreshListener;
    private boolean mRefreshEnable;
    private TextView tvContent;
    private ImageView ivIcon;
    private TextView tvRefresh;
    private ProgressBar pbProgress;
    private LinearLayout llContent;

    public RefreshableEmptyView(Context context) {
        super(context);
    }

    public RefreshableEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshableEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.layout_refresh_empty_view, this);
        setClickable(true);

        findViews();
        setListener();
        applyAttributes(context, attrs);
    }

    private void findViews() {
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvRefresh = (TextView) findViewById(R.id.tv_refresh_text);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
    }

    private void setListener() {
        tvRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefreshListener != null) {
                    setRefreshing(true);
                    mRefreshListener.onRefresh(RefreshableEmptyView.this);
                }
            }
        });
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshableEmptyView);
        if (typedArray == null) {
            return;
        }

        applyContent(typedArray);
        applyIcon(typedArray);
        applyRefresh(typedArray);

        typedArray.recycle();
    }

    private void applyContent(TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_content_text)) {
            String text = typedArray.getString(R.styleable.RefreshableEmptyView_content_text);
            tvContent.setText(text);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_content_text_color)) {
            int color = typedArray.getColor(R.styleable.RefreshableEmptyView_content_text_color, DEFAULT_CONTENT_COLOR);
            tvContent.setTextColor(color);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_content_text_size)) {
            float size = typedArray.getDimension(R.styleable.RefreshableEmptyView_content_text_size, DEFAULT_CONTENT_SIZE);
            tvContent.setTextSize(size);
        }
    }

    private void applyIcon(TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_icon_src)) {
            Drawable drawable = typedArray.getDrawable(R.styleable.RefreshableEmptyView_icon_src);
            ivIcon.setImageDrawable(drawable);
        }
    }

    private void applyRefresh(TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_refresh_text)) {
            String text = typedArray.getString(R.styleable.RefreshableEmptyView_refresh_text);
            tvRefresh.setText(text);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_refresh_text_color)) {
            int color = typedArray.getColor(R.styleable.RefreshableEmptyView_refresh_text_color, DEFAULT_REFRESH_COLOR);
            tvRefresh.setTextColor(color);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_refresh_text_size)) {
            float size = typedArray.getDimension(R.styleable.RefreshableEmptyView_refresh_text_size, DEFAULT_REFRESH_SIZE);
            tvRefresh.setTextSize(size);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_refresh_enable)) {
            mRefreshEnable = typedArray.getBoolean(R.styleable.RefreshableEmptyView_refresh_enable, mRefreshEnable);
            tvRefresh.setVisibility(mRefreshEnable ? VISIBLE : GONE);
        }

        if (typedArray.hasValue(R.styleable.RefreshableEmptyView_refresh_background)) {
            int color = typedArray.getColor(R.styleable.RefreshableEmptyView_refresh_background, DEFAULT_REFRESH_BACKGROUND_COLOR);
            tvRefresh.setBackgroundColor(color);
        }
    }

    public void setRefreshing(boolean isRefreshing) {
        pbProgress.setVisibility(isRefreshing ? VISIBLE : GONE);
        llContent.setVisibility(isRefreshing ? GONE : VISIBLE);
    }

    public void setRefreshEnable(boolean enable) {
        mRefreshEnable = enable;
        tvRefresh.setVisibility(enable ? VISIBLE : GONE);
    }

    public void setRefreshListener(RefreshListener listener) {
        mRefreshListener = listener;
    }

    public RefreshableEmptyView icon(@DrawableRes int resId) {
        ivIcon.setImageResource(resId);
        return this;
    }

    public RefreshableEmptyView contentText(CharSequence text) {
        tvContent.setText(text);
        return this;
    }

    public RefreshableEmptyView contentText(@StringRes int resId) {
        tvContent.setText(resId);
        return this;
    }

    public RefreshableEmptyView refreshText(CharSequence text) {
        tvRefresh.setText(text);
        return this;
    }

    public RefreshableEmptyView refreshText(@StringRes int resId) {
        tvRefresh.setText(resId);
        return this;
    }

    public interface RefreshListener {
        void onRefresh(View v);
    }
}
