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

public class DialogAdapter {

    private int mLayoutId;
    private ViewBinder mViewBinder;

    public DialogAdapter() {

    }

    public DialogAdapter layoutId(int layoutId) {
        mLayoutId = layoutId;
        return this;
    }

    int getLayoutId() {
        return mLayoutId;
    }

    public DialogAdapter viewBinder(ViewBinder viewBinder) {
        mViewBinder = viewBinder;
        return this;
    }

    void bindView(View rootView, DialogFragment fragment) {
        mViewBinder.bindView(rootView, fragment);
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

        public static <T> T findView(View view, int resId) {
            return (T) view.findViewById(resId);
        }
    }
}
