package com.ray.searchbar;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.lang.ref.WeakReference;


/**
 * Author      : leixing
 * Date        : 2017-07-11
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : EditText user for search keyword
 */

public class SearchEditText extends WatchableEditText {
    public static final int MSG_SEARCH = 101;
    public static final int DEFAULT_SEARCH_DELAY_MILLS = 800;
    private int mSearchDelayMillis;
    private OnSearchListener mOnSearchListener;
    private SearchHandler mHandler;
    private boolean mAutoSearchEnable;

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

        setMaxLines(1);
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);


        setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    clearSearchMessage();
                    if (mOnSearchListener != null) {
                        mOnSearchListener.onSearch(false, false, getText().toString().trim());
                        return true;
                    }
                }
                return false;
            }
        });


        addWatcher(new Watcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after, boolean isProgrammatically) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count, boolean isProgrammatically) {

            }

            @Override
            public void afterTextChanged(Editable s, boolean isProgrammatically) {
                clearSearchMessage();
                if (mAutoSearchEnable && mOnSearchListener != null) {
                    SearchMessage searchMessage = new SearchMessage(mOnSearchListener, s.toString().trim(), isProgrammatically);
                    Message message = mHandler.obtainMessage(MSG_SEARCH, searchMessage);
                    mHandler.sendMessageDelayed(message, mSearchDelayMillis);
                }
            }
        });
    }

    public void setAutoSearchEnable(boolean enable) {
        mAutoSearchEnable = enable;
    }

    public void setOnSearchListener(OnSearchListener listener) {
        mOnSearchListener = listener;
    }

    public void setSearchDelayMills(int mills) {
        mSearchDelayMillis = mills;
    }

    private void clearSearchMessage() {
        if (mHandler.hasMessages(MSG_SEARCH)) {
            mHandler.removeMessages(MSG_SEARCH);
        }
    }

    private static class SearchMessage {
        private WeakReference<OnSearchListener> wrListener;
        private String keyword;
        private boolean isProgrammatically;

        public SearchMessage(OnSearchListener listener, String keyword, boolean isProgrammatically) {
            this.wrListener = new WeakReference<>(listener);
            this.keyword = keyword;
            this.isProgrammatically = isProgrammatically;
        }

        @Nullable
        public OnSearchListener getListener() {
            return wrListener == null ? null : wrListener.get();
        }

        public String getKeyword() {
            return keyword;
        }

        public boolean isProgrammatically() {
            return isProgrammatically;
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
                    throw new IllegalStateException("error message, what = " + msg.what
                            + " obj = " + msg.obj);
                }

                SearchMessage searchMessage = (SearchMessage) msg.obj;
                OnSearchListener listener = searchMessage.getListener();
                String keyword = searchMessage.getKeyword();
                boolean isProgrammatically = searchMessage.isProgrammatically();
                if (listener != null) {
                    listener.onSearch(isProgrammatically, true, keyword);
                }
            }
        }
    }

    public interface OnSearchListener {
        /**
         * listener for search, will invoke when user click search key in soft input method or after
         * {@link SearchEditText#mSearchDelayMillis}
         *
         * @param isProgrammatically {@code false} if text is changed by user input,  {@code true} if
         *                           text changed by {@link android.widget.EditText#setText(CharSequence, TextView.BufferType)}
         * @param isAuto             {@code true} if search action cause by {@link SearchEditText#mSearchDelayMillis}
         *                           time out, {@code false} if search action cause by user click "search"
         *                           key in keyboard
         * @param keyword            keyword for search
         */
        @UiThread
        void onSearch(boolean isProgrammatically, boolean isAuto, String keyword);
    }
}