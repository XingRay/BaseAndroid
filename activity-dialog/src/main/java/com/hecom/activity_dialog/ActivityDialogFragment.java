package com.hecom.activity_dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 用于展示弹窗视图的DialogFragment
 */

public class ActivityDialogFragment extends DialogFragment {
    public static final String CODE = "code";
    public static final String CANCELABLE = "cancelable";
    public static final String STYLE = "style";
    public static final String THEME = "theme";
    private View mRootView;
    private long mCode;
    private DialogAdapter mAdapter;

    public ActivityDialogFragment() {
    }

    public static ActivityDialogFragment newInstance(long code, boolean cancelable, int style, int theme) {
        ActivityDialogFragment fragment = new ActivityDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(CODE, code);
        bundle.putBoolean(CANCELABLE, cancelable);
        bundle.putInt(STYLE, style);
        bundle.putInt(THEME, theme);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(mAdapter.getLayoutId(), null);
        initView();
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AdapterManager.getInstance().remove(mCode);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }

    private void initVariables() {
        Bundle arguments = getArguments();
        mCode = arguments.getLong(CODE);

        mAdapter = AdapterManager.getInstance().get(mCode);
        mAdapter.bindDialog(this);

        boolean cancelable = arguments.getBoolean(CANCELABLE, false);
        setCancelable(cancelable);

        int style = arguments.getInt(STYLE);
        if (style == 0) {
            style = STYLE_NO_TITLE;
        }

        int theme = arguments.getInt(THEME);
        if (theme == 0) {
            theme = R.style.DialogNoTitle;
        }

        setStyle(style, theme);
    }

    private void initView() {
        mAdapter.bindView(mRootView);
    }
}
