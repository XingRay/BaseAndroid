package com.ray.baseandroid.commonlistview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import com.ray.lib.java.util.collection.CollectionUtil;

import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-10
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DragItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {

    private final List<T> mItems;
    private final RecyclerView.Adapter mAdapter;
    private final int mDragColor;
    private final int mEndColor;
    private int mHeaderSize;

    public DragItemTouchHelperCallback(List<T> items, RecyclerView.Adapter adapter, int dragColor, int endColor) {
        mItems = items;
        mAdapter = adapter;
        mDragColor = dragColor;
        mEndColor = endColor;
        mHeaderSize = 0;
    }

    public DragItemTouchHelperCallback(List<T> items, RecyclerView.Adapter adapter) {
        this(items, adapter, 0xfffef8fa, 0xffffffff);
    }

    public DragItemTouchHelperCallback<T> setHeaderSize(int headerSize) {
        mHeaderSize = headerSize;
        return this;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        CollectionUtil.move(mItems, fromPosition - mHeaderSize, targetPosition - mHeaderSize);
        mAdapter.notifyItemMoved(fromPosition, targetPosition);
        return true;
    }

    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        int targetPosition = target.getAdapterPosition();
        return targetPosition >= mHeaderSize;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     * 拖动选择状态改变回调
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(mDragColor);
        }
    }

    /**
     * 回到正常状态的时候回调
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(mEndColor);
        ViewCompat.setTranslationX(viewHolder.itemView, 0);
    }
}