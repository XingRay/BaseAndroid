package com.ray.searchbar;

import android.support.annotation.UiThread;

/**
 * @author : leixing
 * @date : 2017-09-04
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : OnSearchListener
 */

public interface OnSearchListener {
    /**
     * listener for search, will invoke when user click search key in soft input method or after
     * {@link SearchEditText#mSearchDelayMillis}
     *
     * @param trigger trigger for search
     * @param keyword keyword for search
     */
    @UiThread
    void onSearch(SearchEditText.SearchTrigger trigger, String keyword);
}
