package com.ray.searchbar;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * @author : leixing
 * @date : 2017-09-04
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

class SearchMessage {
    private final WeakReference<OnSearchListener> wrListener;
    private final String keyword;
    private final SearchEditText.SearchTrigger trigger;

    public SearchMessage(OnSearchListener listener, String keyword, SearchEditText.SearchTrigger trigger) {
        this.wrListener = new WeakReference<>(listener);
        this.keyword = keyword;
        this.trigger = trigger;
    }

    @Nullable
    OnSearchListener getListener() {
        return wrListener.get();
    }

    String getKeyword() {
        return keyword;
    }

    public SearchEditText.SearchTrigger getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "\"SearchMessage\": {"
                + "\"wrListener\": \"" + wrListener
                + ", \"keyword\": \"" + keyword + '\"'
                + ", \"trigger\": \"" + trigger
                + '}';
    }
}
