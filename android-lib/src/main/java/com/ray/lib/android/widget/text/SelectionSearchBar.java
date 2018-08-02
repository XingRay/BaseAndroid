package com.ray.lib.android.widget.text;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ray.lib.android.R;


/**
 * @@author      : leixing
 * @@date        : 2017-07-11
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : 带选项文本的搜索栏
 */

public class SelectionSearchBar extends RelativeLayout {

    private TextView tvSelection;
    private SearchEditText etKeyword;
    private ImageView ivClear;
    private OnClickListener mSelectionOnClickListener;

    public SelectionSearchBar(Context context) {
        this(context, null);
    }

    public SelectionSearchBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectionSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.layout_selection_serach_bar, this);

        tvSelection = findViewById(R.id.tv_selection);
        etKeyword = findViewById(R.id.et_keyword);
        ivClear = findViewById(R.id.iv_clear);

        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    ivClear.setVisibility(GONE);
                } else {
                    ivClear.setVisibility(VISIBLE);
                }
            }
        });

        ivClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etKeyword.setText("");
            }
        });

        tvSelection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectionOnClickListener != null) {
                    mSelectionOnClickListener.onClick(SelectionSearchBar.this);
                }
            }
        });

        etKeyword.requestFocus();
    }

    public void setSearchKeyword(CharSequence keyword) {
        etKeyword.setText(keyword);
        etKeyword.setSelection(keyword.length());
    }

    public void setSearchKeyword(@StringRes int resId) {
        etKeyword.setText(resId);
    }

    public void setOnSearchListener(SearchEditText.OnSearchListener listener) {
        etKeyword.setOnSearchListener(listener);
    }

    public void setSelectionText(String selectionText) {
        tvSelection.setText(selectionText);
    }

    public void setSelectionText(@StringRes int resId) {
        tvSelection.setText(resId);
    }

    public void setSearchDelayMills(int mills) {
        etKeyword.setSearchDelayMills(mills);
    }

    public void setSelectionOnClickListener(OnClickListener listener) {
        mSelectionOnClickListener = listener;
    }
}
