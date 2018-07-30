package com.ray.searchbar;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * @author      : leixing
 * @date        : 2017-08-01
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : an watchable EditText can listen text changed and recognize if it is form user's
 * operation or {@link android.widget.EditText#setText(CharSequence)}
 */

public class WatchableEditText extends AppCompatEditText {
    private ArrayList<Watcher> mWatchers;
    private boolean mIsTextSetProgrammatically;

    public WatchableEditText(Context context) {
        this(context, null);
    }

    public WatchableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIsTextSetProgrammatically = false;
        setFocusableInTouchMode(true);
        setTextWatcher();
    }

    /**
     * add watcher for listen text change event
     *
     * @param watcher
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
     * @param watcher
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
                list.get(i).beforeTextChanged(s, start, count, after, mIsTextSetProgrammatically);
            }
        }
    }

    private void sendOnTextChanged(CharSequence s, int start, int before, int count) {
        if (mWatchers != null) {
            final ArrayList<Watcher> list = mWatchers;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).onTextChanged(s, start, before, count, mIsTextSetProgrammatically);
            }
        }
    }

    private void sendAfterTextChanged(Editable s) {
        if (mWatchers != null) {
            final ArrayList<Watcher> list = mWatchers;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).afterTextChanged(s, mIsTextSetProgrammatically);
            }
        }

        mIsTextSetProgrammatically = false;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        mIsTextSetProgrammatically = true;
        super.setText(text, type);
    }

    /**
     * like {@link TextWatcher}, the difference between {@link Watcher} and {@link TextWatcher} is
     * {@code isProgrammatically} in every callback indicate if change of text is from user's
     * operation or invoke method {@link android.widget.EditText#setText(CharSequence)}
     */
    public interface Watcher {
        void beforeTextChanged(CharSequence s, int start, int count, int after, boolean isProgrammatically);

        void onTextChanged(CharSequence s, int start, int before, int count, boolean isProgrammatically);

        void afterTextChanged(Editable s, boolean isProgrammatically);
    }
}
