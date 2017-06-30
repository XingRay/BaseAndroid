package com.ray.lib.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ray.lib.android.R;


/**
 * Author      : leixing
 * Date        : 2017-06-30
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class NotifyBannerView extends RelativeLayout {

    private DismissListener mDismissListener;
    private ShowListener mShowListener;
    private ImageView ivClose;
    private MarqueeTextView mtvText;

    public NotifyBannerView(Context context) {
        this(context, null);
    }

    public NotifyBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotifyBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_notify_banner_view, this);

        ivClose = (ImageView) findViewById(R.id.iv_close);
        mtvText = (MarqueeTextView) findViewById(R.id.mtv_text);

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setText(int resId) {
        mtvText.setText(resId);
    }

    public void setText(String text) {
        mtvText.setText(text);
    }

    public void dismiss() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility == GONE || visibility == INVISIBLE) {
            mtvText.stopMarquee();
            if (mDismissListener != null) {
                mDismissListener.onDismiss(this);
            }
        }

        if (visibility == VISIBLE) {
            mtvText.startMarqueeDelayed();
            if (mShowListener != null) {
                mShowListener.onShow(this);
            }
        }
    }

    public void setDismissListener(DismissListener listener) {
        mDismissListener = listener;
    }

    public void setShowListener(ShowListener listener) {
        mShowListener = listener;
    }

    public interface DismissListener {
        void onDismiss(View v);
    }

    public interface ShowListener {
        void onShow(View v);
    }
}
