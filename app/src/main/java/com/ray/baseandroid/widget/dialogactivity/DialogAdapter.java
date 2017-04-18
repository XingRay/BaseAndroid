package com.ray.baseandroid.widget.dialogactivity;

import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : DialogAdapter适配器,用于提供视图展示的参数和事件回调
 */

public class DialogAdapter implements DialogOperator {

    private int mLayoutId;
    private ViewBinder mViewBinder;
    private DialogFragment mFragment;
    private boolean mAutoDismiss;

    public DialogAdapter() {
        mAutoDismiss = true;
    }

    public DialogAdapter layoutId(int layoutId) {
        mLayoutId = layoutId;
        return this;
    }

    protected int getLayoutId() {
        return mLayoutId;
    }

    public DialogAdapter viewBinder(ViewBinder viewBinder) {
        mViewBinder = viewBinder;
        return this;
    }

    protected void bindView(View rootView, DialogFragment fragment) {
        mViewBinder.bindView(rootView, fragment);
    }

    DialogAdapter dialogFragment(DialogFragment fragment) {
        mFragment = fragment;
        return this;
    }

    DialogAdapter autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return this;
    }

    protected boolean isAutoDismiss() {
        return mAutoDismiss;
    }

    /**
     * 视图绑定器，用于绑定控件的数据和事件
     */
    public static abstract class ViewBinder {
        /**
         * 绑定控件的数据和事件
         *
         * @param rootView
         * @param fragment
         */
        protected abstract void bindView(View rootView, DialogFragment fragment);
    }

    @Override
    public void dismiss() {
        mFragment.dismiss();
    }
}
