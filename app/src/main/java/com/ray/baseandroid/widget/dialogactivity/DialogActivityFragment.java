package com.ray.baseandroid.widget.dialogactivity;

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

public class DialogActivityFragment extends DialogFragment {
    public static final String EVENT_ID = "event_id";
    public static final String CANCELABLE = "cancelable";
    private View mRootView;
    private long mEventId;
    private DialogAdapter mAdapter;


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
        DialogActivityManager.getInstance().removeAdapter(mEventId);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }

    public static DialogActivityFragment newInstance(long eventId, boolean cancelable) {
        DialogActivityFragment fragment = new DialogActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_ID, eventId);
        bundle.putBoolean(CANCELABLE, cancelable);
        fragment.setArguments(bundle);
        return fragment;
    }

    public DialogActivityFragment() {
    }


    private void initVariables() {
        Bundle arguments = getArguments();
        mEventId = arguments.getLong(EVENT_ID);
        boolean cancelable = arguments.getBoolean(CANCELABLE, false);
        mAdapter = DialogActivityManager.getInstance().getAdapter(mEventId);
        setCancelable(cancelable);
    }

    private void initView() {
        mAdapter.bindView(mRootView, this);
    }

    private void loadData() {

    }
}
