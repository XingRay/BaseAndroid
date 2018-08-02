package com.ray.lib.android.base.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.lib.android.base.recyclerview.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author : leixing
 * email : leixing@baidu.com
 * @date : 2018/8/2 14:15
 * <p>
 * description : xxx
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private final List<T> mItems;
    private OnItemClickListener mOnItemClickListener;
    private final LayoutInflater mInflater;
    private int mItemLayoutId;
    private BaseViewHolderFactory mFactory;
    private BaseMultiTypeSupport mMultiTypeSupport;

    public BaseAdapter(Context context) {
        mItems = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public BaseAdapter itemLayoutId(int itemLayoutId) {
        mItemLayoutId = itemLayoutId;
        return this;
    }

    public BaseAdapter viewHolderFactory(BaseViewHolderFactory factory) {
        mFactory = factory;
        return this;
    }

    public BaseAdapter multiTypeSupport(BaseMultiTypeSupport support) {
        mMultiTypeSupport = support;
        return this;
    }

    public void update(List<T> list) {
        mItems.clear();
        if (list != null) {
            mItems.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void add(T item) {
        add(mItems.size(), item);
    }

    public void add(int index, T item) {
        if (item == null) {
            return;
        }
        mItems.add(index, item);
        notifyItemInserted(index);
    }

    public void addAll(List<T> list) {
        addAll(mItems.size(), list);
    }

    public void addAll(int start, List<T> items) {
        if (items == null) {
            return;
        }

        mItems.addAll(start, items);
        notifyItemRangeInserted(start, items.size());
    }

    public void remove(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, int count) {
        if (position < 0 || position >= mItems.size()) {
            return;
        }

        ListIterator<T> iterator = mItems.listIterator(position);
        int countToRemove = count;
        while (iterator.hasNext() && countToRemove > 0) {
            iterator.next();
            iterator.remove();
            countToRemove--;
        }

        notifyItemRangeRemoved(position, count);
    }

    public void moveItem(int from, int to) {
        Util.move(mItems, from, to);
        notifyItemMoved(from, to);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getItemViewType(mItems, position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        if (mMultiTypeSupport != null) {
            layoutId = mMultiTypeSupport.getLayoutId(viewType);
        } else {
            layoutId = mItemLayoutId;
        }
        View itemView = mInflater.inflate(layoutId, parent, false);
        return mFactory.createViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            holder.onRefreshItemView(payloads);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T t = mItems.get(position);
        holder.onBindItemView(t, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
