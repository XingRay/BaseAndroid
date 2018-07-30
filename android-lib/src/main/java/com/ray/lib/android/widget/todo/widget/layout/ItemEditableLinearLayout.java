package com.ray.lib.android.widget.todo.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by leixing
 * on 2016-11-04.
 * Email : leixing1012@qq.com
 */

public abstract class ItemEditableLinearLayout<Item> extends LinearLayout implements View.OnClickListener {
    private List<Item> items;
    private EditItemListener<Item> editItemListener;
    private View constantView;
    private List<View> itemViews;

    public ItemEditableLinearLayout(Context context) {
        this(context, null);
    }

    public ItemEditableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ItemEditableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.items = new ArrayList<>();
        this.itemViews = new ArrayList<>();
        this.setOrientation(VERTICAL);
        addConstantView();
    }

    private void addConstantView() {
        constantView = getConstantView();
        if (constantView != null) {
            this.addView(constantView);
            constantView.setTag(this);
            constantView.setOnClickListener(this);
        }
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (View v : itemViews) {
            if (v == null) {
                continue;
            }

            Item item = getItem(v);
            items.add(item);
        }

        return Collections.unmodifiableList(items);
    }

    public void setItems(List<Item> items) {
        this.items = items;
        refreshItemView();
    }

    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }

        if (this.items.add(item)) {
            refreshItemView();
        }
    }

    public void removeItem(Item item) {
        if (this.items == null || this.items.size() == 0) {
            return;
        }

        if (this.items.remove(item)) {
            refreshItemView();
        }
    }

    public void addItems(List<Item> items) {
        if (items == null) {
            items = new ArrayList<>();
        }

        if (this.items.addAll(items)) {
            refreshItemView();
        }
    }

    public void setEditItemListener(EditItemListener<Item> listener) {
        this.editItemListener = listener;
    }

    public void refreshItemView() {
        this.removeAllViews();
        itemViews.clear();

        if (items != null && items.size() > 0) {
            for (final Item item : items) {
                if (item == null) {
                    continue;
                }
                View itemView = getItemView(item);
                if (itemView == null) {
                    continue;
                }
                itemViews.add(itemView);
                this.addView(itemView);

                View deleteView = getDeleteViewFromItemView(itemView);
                if (deleteView != null) {
                    deleteView.setTag(item);
                    deleteView.setOnClickListener(this);
                }
            }
        }

        addConstantView();
    }

    protected abstract View getDeleteViewFromItemView(View itemView);

    protected abstract View getConstantView();

    protected abstract View getItemView(Item item);

    protected abstract Item getItem(View v);

    @Override
    public void onClick(View view) {
        if (editItemListener == null || view == null) {
            return;
        }

        Object tag = view.getTag();
        if (tag == this) {
            editItemListener.onAddItem();
        } else if (tag != null) {
            Item item = (Item) tag;
            removeItem(item);
            editItemListener.onRemoveItem(item);
        }
    }

    public interface EditItemListener<Item> {
        void onAddItem();

        void onRemoveItem(Item item);
    }
}
