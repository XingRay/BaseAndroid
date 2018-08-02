package com.ray.searchbar;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;


/**
 * @author : leixing
 * @date : 2017-07-11
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : EditText user for search keyword
 */

@SuppressWarnings("unused")
public class SearchEditText extends MethodEditText {
    public static final int MSG_SEARCH = 101;
    public static final int MSG_CLEAR = 100;

    /**
     * 默认是否开始文本输入自动触发
     */
    public static final boolean DEFAULT_AUTO_SET_TEXT_ENABLE = false;
    public static final boolean DEFAULT_AUTO_USER_INPUT_ENABLE = false;
    public static final boolean DEFAULT_ACTION_PERFORM_ENABLE = true;
    public static final boolean DEFAULT_ACTION_SOFT_INPUT_ENABLE = true;

    private int mSearchDelayMillis = 500;
    private boolean mAutoSearchEnable = true;

    private OnSearchListener mOnSearchListener;
    private OnKeywordChangedListener mOnKeywordChangedListener;
    private OnClearListener mOnClearListener;

    private SearchHandler mHandler;
    private HashMap<SearchTrigger, Boolean> mTriggerEnableMap;

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHandler = new SearchHandler();

        mTriggerEnableMap = new HashMap<>();
        mTriggerEnableMap.put(SearchTrigger.AUTO_SET_TEXT, DEFAULT_AUTO_SET_TEXT_ENABLE);
        mTriggerEnableMap.put(SearchTrigger.AUTO_USER_INPUT, DEFAULT_AUTO_USER_INPUT_ENABLE);
        mTriggerEnableMap.put(SearchTrigger.ACTION_PERFORM, DEFAULT_ACTION_PERFORM_ENABLE);
        mTriggerEnableMap.put(SearchTrigger.ACTION_SOFT_INPUT, DEFAULT_ACTION_SOFT_INPUT_ENABLE);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setMaxLines(1);
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(SearchTrigger.ACTION_SOFT_INPUT);
                    return true;
                }
                return false;
            }
        });


        addWatcher(new Watcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after, Method method) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count, Method method) {

            }

            @Override
            public void afterTextChanged(Editable s, Method method) {
                clearSearchMessage();
                String keyword = s.toString().trim();
                if (mOnKeywordChangedListener != null) {
                    mOnKeywordChangedListener.onKeywordChanged(keyword);
                }

                if (TextUtils.isEmpty(keyword)) {
                    if (mOnClearListener != null) {
                        mOnClearListener.onClear();
                    }
                    return;
                }
                SearchTrigger trigger = getTrigger(method);

                if (mAutoSearchEnable && isTriggerEnable(trigger)) {
                    sendSearchMessage(keyword, trigger, mSearchDelayMillis);
                }
            }
        });
    }

    private SearchTrigger getTrigger(Method method) {
        switch (method) {
            case SET_TEXT:
                return SearchTrigger.AUTO_SET_TEXT;
            case USER_INPUT:
                return SearchTrigger.AUTO_USER_INPUT;
            default:
                return SearchTrigger.AUTO_USER_INPUT;
        }
    }

    public void setTriggerEnable(SearchTrigger method, boolean enable) {
        mTriggerEnableMap.put(method, enable);
    }

    public boolean isTriggerEnable(SearchTrigger method) {
        Boolean enable = mTriggerEnableMap.get(method);
        return enable == null ? false : enable;
    }

    public void popupSoftInput() {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(this, 0);
        }
    }

    private void sendSearchMessage(String keyword, SearchTrigger trigger, long delayMills) {
        if (mOnSearchListener == null) {
            return;
        }
        SearchMessage searchMessage = new SearchMessage(mOnSearchListener, keyword, trigger);
        Message message = mHandler.obtainMessage(MSG_SEARCH, searchMessage);
        mHandler.sendMessageDelayed(message, delayMills);
    }

    public void setAutoSearchEnable(boolean enable) {
        mAutoSearchEnable = enable;
    }

    public void setOnSearchListener(OnSearchListener listener) {
        mOnSearchListener = listener;
    }

    public void setOnKeywordChangedListener(OnKeywordChangedListener listener) {
        mOnKeywordChangedListener = listener;
    }

    public void setOnClearListener(OnClearListener listener) {
        mOnClearListener = listener;
    }

    public void setSearchDelayMills(int mills) {
        mSearchDelayMillis = mills;
    }

    private void clearSearchMessage() {
        if (mHandler.hasMessages(MSG_SEARCH)) {
            mHandler.removeMessages(MSG_SEARCH);
        }
    }

    /**
     * perform search action
     */
    public void performSearch() {
        performSearch(SearchTrigger.ACTION_PERFORM);
    }

    private void performSearch(SearchTrigger trigger) {
        clearSearchMessage();
        sendSearchMessage(getKeyword(), trigger, 0);
    }

    public String getKeyword() {
        return getText().toString().trim();
    }

    private static class SearchHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }

            switch (msg.what) {
                case MSG_SEARCH:
                    handleSearchMessage(msg);
                    break;
                case MSG_CLEAR:
                    handleClearMessage(msg);
                    break;
                default:
            }
        }

        private void handleClearMessage(Message msg) {
            if (msg.obj == null || !(msg.obj instanceof WeakReference)) {
                return;
            }
            WeakReference listenerWeakReference = (WeakReference) msg.obj;
            Object o = listenerWeakReference.get();
            if (o == null || !(o instanceof OnClearListener)) {
                return;
            }
            OnClearListener onClearListener = (OnClearListener) o;
            onClearListener.onClear();
        }

        private void handleSearchMessage(Message msg) {
            if (msg.obj == null || !(msg.obj instanceof SearchMessage)) {
                return;
            }

            SearchMessage searchMessage = (SearchMessage) msg.obj;
            OnSearchListener listener = searchMessage.getListener();
            String keyword = searchMessage.getKeyword();
            SearchTrigger trigger = searchMessage.getTrigger();
            if (listener != null) {
                listener.onSearch(trigger, keyword);
            }
        }
    }

    public enum SearchTrigger {
        /**
         * 用户手动输入文本自动进行搜索
         */
        AUTO_USER_INPUT,

        /**
         * 程序调用{@link android.widget.TextView#setText(CharSequence)}后，自动进入搜索
         */
        AUTO_SET_TEXT,

        /**
         * 通过软键盘的搜索按钮触发搜索
         */
        ACTION_SOFT_INPUT,

        /**
         * 程序直接条用{@link SearchEditText#performSearch(SearchTrigger)}方法触发搜索
         * 一般是用户点击按钮后程序调用
         */
        ACTION_PERFORM
    }
}