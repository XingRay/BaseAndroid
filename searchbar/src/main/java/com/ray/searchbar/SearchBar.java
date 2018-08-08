package com.ray.searchbar;

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


/**
 * @author : leixing
 * @date : 2017-08-14
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : search bar for search keyword
 */

@SuppressWarnings("unused")
public class SearchBar extends RelativeLayout {

    private SearchEditText etKeyword;
    private ImageView ivClear;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.layout_serach_bar, this);

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

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etKeyword.setText("");
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
        etKeyword.setSelection(etKeyword.getText().length());
    }

    public void setAutoSearchEnable(boolean enable) {
        etKeyword.setAutoSearchEnable(enable);
    }

    public void setOnSearchListener(OnSearchListener listener) {
        etKeyword.setOnSearchListener(listener);
    }

    public void setSearchDelayMills(int mills) {
        etKeyword.setSearchDelayMills(mills);
    }

    public String getKeyword() {
        return etKeyword.getText().toString().trim();
    }
}