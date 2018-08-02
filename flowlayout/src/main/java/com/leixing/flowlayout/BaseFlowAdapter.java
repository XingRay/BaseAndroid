package com.leixing.flowlayout;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/1 10:52
 * <p>
 * description : xxx
 */
public abstract class BaseFlowAdapter<T> {

    private OnChangedListener mOnChangedListener;
    private ItemOnClickListener<T> mItemOnClickListener;

    private final List<T> mList;

    public BaseFlowAdapter() {
        mList = new ArrayList<>();
    }

    public void addAll(List<T> list) {
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void set(List<T> list) {
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setOnChangedListener(OnChangedListener listener) {
        mOnChangedListener = listener;
    }

    private void notifyDataSetChanged() {
        if (mOnChangedListener != null) {
            mOnChangedListener.onDataSetChanged();
        }
    }

    public int getCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    /**
     * 根据位置和数据生成视图
     *
     * @param container 父容器
     * @param position  位置
     * @param t         数据项
     * @return 生成的视图
     */
    protected abstract View getView(ViewGroup container, int position, T t);

    void onItemClick(ViewGroup container, int position, T t) {
        if (mItemOnClickListener != null) {
            mItemOnClickListener.onItemClick(container, position, t);
        }
    }

    public interface OnChangedListener {
        /**
         * 数据更新回调
         */
        void onDataSetChanged();
    }

    public void setItemOnClickListener(ItemOnClickListener<T> listener) {
        mItemOnClickListener = listener;
    }
}
