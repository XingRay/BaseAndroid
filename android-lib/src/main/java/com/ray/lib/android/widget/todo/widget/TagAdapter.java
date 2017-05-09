package com.ray.lib.android.widget.todo.widget;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class TagAdapter<T> {
    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<>();

    public TagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    public TagAdapter(T[] datas) {
        mTagDatas = new ArrayList<>(Arrays.asList(datas));
    }

    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    public void setSelectedList(int... pos) {
        for (int i : pos)
            mCheckedPosList.add(i);
        notifyDataChanged();
    }

    HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }

    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

    interface OnDataChangedListener {
        void onChanged();
    }

}
