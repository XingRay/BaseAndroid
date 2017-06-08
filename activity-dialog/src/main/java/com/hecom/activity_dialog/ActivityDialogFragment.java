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
    public static final String EVENT_ID = "event_id";
    public static final String CANCELABLE = "cancelable";
    private View mRootView;
    private long mEventId;
    private DialogAdapter mAdapter;

    public ActivityDialogFragment() {
    }

    public static ActivityDialogFragment newInstance(long eventId, boolean cancelable) {
        ActivityDialogFragment fragment = new ActivityDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_ID, eventId);
        bundle.putBoolean(CANCELABLE, cancelable);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityDialog.getInstance().removeAdapter(mEventId);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }

    private void initVariables() {
        Bundle arguments = getArguments();
        mEventId = arguments.getLong(EVENT_ID);
        boolean cancelable = arguments.getBoolean(CANCELABLE, false);
        mAdapter = ActivityDialog.getInstance().getAdapter(mEventId);
        mAdapter.dialogFragment(this);
        setCancelable(cancelable);
    }

    private void initView() {
        mAdapter.bindView(mRootView, this);
    }

    private void loadData() {

    }
}
