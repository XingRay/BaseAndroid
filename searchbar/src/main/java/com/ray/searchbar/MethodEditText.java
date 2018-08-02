package com.ray.searchbar;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * @author : leixing
 * @date : 2017-08-01
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : an method EditText can listen text changed and recognize if it is trigger by user's
 * operation or {@link android.widget.EditText#setText(CharSequence)}
 */

@SuppressWarnings("unused")
public class MethodEditText extends AppCompatEditText {
    private ArrayList<Watcher> mWatchers;
    private Method mMethod;

    public MethodEditText(Context context) {
        this(context, null);
    }

    public MethodEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MethodEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMethod = Method.USER_INPUT;
        setFocusableInTouchMode(true);
        setTextWatcher();
    }

    /**
     * add watcher for text change event
     *
     * @param watcher watcher for text change event
     */
    public void addWatcher(Watcher watcher) {
        if (mWatchers == null) {
            mWatchers = new ArrayList<>();
        }
        mWatchers.add(watcher);
    }

    /**
     * remove watcher when watcher is no more need
     *
     * @param watcher watcher for text change event
     */
    public void removeWatcher(Watcher watcher) {
        if (mWatchers != null) {
            int i = mWatchers.indexOf(watcher);

            if (i >= 0) {
                mWatchers.remove(i);
            }
        }
    }

    private void setTextWatcher() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sendBeforeTextChanged(s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sendOnTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                sendAfterTextChanged(s);
            }
        });
    }

    private void sendBeforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mWatchers != null) {
            final ArrayList<Watcher> list = mWatchers;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).beforeTextChanged(s, start, count, after, mMethod);
            }
        }
    }

    private void sendOnTextChanged(CharSequence s, int start, int before, int count) {
        if (mWatchers != null) {
            final ArrayList<Watcher> list = mWatchers;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).onTextChanged(s, start, before, count, mMethod);
            }
        }
    }

    private void sendAfterTextChanged(Editable s) {
        if (mWatchers != null) {
            final ArrayList<Watcher> list = mWatchers;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).afterTextChanged(s, mMethod);
            }
        }

        mMethod = Method.USER_INPUT;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        mMethod = Method.SET_TEXT;
        super.setText(text, type);
    }

    /**
     * like {@link TextWatcher}, the difference between {@link Watcher} and {@link TextWatcher} is
     * {@code isProgrammatically} in every callback indicate if change of text is from user's
     * operation or invoke method {@link android.widget.EditText#setText(CharSequence)}
     */
    @SuppressWarnings("WeakerAccess")
    public interface Watcher {
        void beforeTextChanged(CharSequence s, int start, int count, int after, Method method);

        void onTextChanged(CharSequence s, int start, int before, int count, Method method);

        void afterTextChanged(Editable s, Method method);
    }

    public enum Method {
        /**
         * 用户手动输入文本触发回调
         */
        USER_INPUT,

        /**
         * 程序调用{@link android.widget.TextView#setText(CharSequence)}触发回调
         */
        SET_TEXT
    }
}
