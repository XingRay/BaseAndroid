
package com.leixing.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/1 10:50
 * <p>
 * description : xxx
 */
public class AdapterFlowLayout extends FlowLayout {

    private BaseFlowAdapter mAdapter;

    public AdapterFlowLayout(Context context) {
        this(context, null);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(BaseFlowAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setOnChangedListener(new BaseFlowAdapter.OnChangedListener() {
            @Override
            public void onDataSetChanged() {
                refreshView();
            }
        });
    }

    private void refreshView() {
        removeAllViews();
        final BaseFlowAdapter adapter = mAdapter;

        for (int i = 0, size = adapter.getCount(); i < size; i++) {
            final Object item = adapter.getItem(i);
            final int position = i;

            @SuppressWarnings("unchecked")
            View view = adapter.getView(this, i, item);
            addView(view);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //noinspection unchecked
                    adapter.onItemClick(AdapterFlowLayout.this, position, item);
                }
            });
        }
    }
}
