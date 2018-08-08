package com.ray.lib.android.widget.text;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.lang.ref.WeakReference;


/**
 * @author      : leixing
 * @date        : 2017-07-11
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SearchEditText extends AppCompatEditText {
    public static final int MSG_SEARCH = 101;
    public static final int DEFAULT_SEARCH_DELAY_MILLS = 800;
    private int mSearchDelayMillis;
    private OnSearchListener mOnSearchListener;
    private SearchHandler mHandler;

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHandler = new SearchHandler();
        mSearchDelayMillis = DEFAULT_SEARCH_DELAY_MILLS;

        setFocusableInTouchMode(true);
        setMaxLines(1);
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);


        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    clearSearchMessage();
                    if (mOnSearchListener != null) {
                        mOnSearchListener.onSearch(false, getText().toString().trim());
                        return true;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearSearchMessage();
                if (mOnSearchListener != null) {
                    SearchMessage searchMessage = new SearchMessage(mOnSearchListener, s.toString().trim());
                    Message message = mHandler.obtainMessage(MSG_SEARCH, searchMessage);
                    mHandler.sendMessageDelayed(message, mSearchDelayMillis);
                }
            }
        });
    }

    private void clearSearchMessage() {
        if (mHandler.hasMessages(MSG_SEARCH)) {
            mHandler.removeMessages(MSG_SEARCH);
        }
    }

    public void setOnSearchListener(OnSearchListener listener) {
        mOnSearchListener = listener;
    }

    public void setSearchDelayMills(int mills) {
        mSearchDelayMillis = mills;
    }

    private static class SearchMessage {
        private WeakReference<OnSearchListener> wrListener;
        private String keyword;

        public SearchMessage(OnSearchListener listener, String keyword) {
            this.wrListener = new WeakReference<>(listener);
            this.keyword = keyword;
        }

        @Nullable
        public OnSearchListener getListener() {
            return wrListener == null ? null : wrListener.get();
        }

        public String getKeyword() {
            return keyword;
        }

        @Override
        public String toString() {
            return "SearchMessage{" +
                    "wrListener=" + wrListener +
                    ", keyword='" + keyword + '\'' +
                    '}';
        }
    }

    private static class SearchHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }

            if (msg.what == MSG_SEARCH) {
                if (msg.obj == null || !(msg.obj instanceof SearchMessage)) {
                    throw new IllegalStateException("error message, what = " + msg.what + " obj = " + msg.obj);
                }

                SearchMessage searchMessage = (SearchMessage) msg.obj;
                OnSearchListener listener = searchMessage.getListener();
                String keyword = searchMessage.getKeyword();
                if (listener != null) {
                    listener.onSearch(true, keyword);
                }
            }
        }
    }

    /**
     * 搜索监听器， 用于触发搜索动作
     */
    public interface OnSearchListener {
        /**
         * 搜索回调， 用户按下键盘的搜索按键或者输入内容改变之后超过延时阈值后会自动触发
         *
         * @param isAuto  是否是自动触发
         *                如果是因为内容改变超过延时阈值则为{@code true}, 用户按下软键盘的
         *                搜索触发搜索则为{@code false}
         * @param keyword 用户输入的搜索关键字
         */
        @UiThread
        void onSearch(boolean isAuto, String keyword);
    }
}
