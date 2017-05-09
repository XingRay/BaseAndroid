package com.ray.commonlistview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.commonlistview.MultiTypeSupport;
import com.ray.commonlistview.ViewHolder;
import com.ray.commonlistview.listener.OnItemClickListener;
import com.ray.commonlistview.listener.OnLongClickListener;

import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-06
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : RecyclerView适配器
 */

public abstract class Adapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<T> mList;
    private int mLayoutId;
    private MultiTypeSupport<T> mMultiTypeSupport;
    private OnItemClickListener mItemClickListener;
    private OnLongClickListener mLongClickListener;
    private RecyclerView mParent;
    private int mHeaderSize;

    public Adapter(Context context, List<T> list, int layoutId) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mLayoutId = layoutId;
    }

    public Adapter(Context context, List<T> list, MultiTypeSupport<T> multiTypeSupport) {
        this(context, list, -1);
        mMultiTypeSupport = multiTypeSupport;
    }

    public void setHeaderSize(int headerSize) {
        mHeaderSize = headerSize;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getViewType(mList.get(position), position);
        }

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiTypeSupport != null) {
            mLayoutId = mMultiTypeSupport.getLayoutId(viewType);
        }

        if (mParent == null) {
            mParent = (RecyclerView) parent;
        }

        return new ViewHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int layoutPosition = holder.getLayoutPosition();
        final int id = holder.getAdapterPosition() - mHeaderSize;

        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(mParent, holder.itemView, layoutPosition, id);
                }
            });
        }

        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mLongClickListener.onLongClick(mParent, holder.itemView, layoutPosition, id);
                }
            });
        }

        int viewType = mMultiTypeSupport == null ? 0 : mMultiTypeSupport.getViewType(mList.get(position), position);

        bindView(holder, mList.get(position), viewType);
    }

    protected abstract void bindView(ViewHolder holder, T t, int viewType);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
