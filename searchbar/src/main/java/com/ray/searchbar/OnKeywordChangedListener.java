package com.ray.searchbar;

/**
 * @author      : leixing
 * @date        : 2017-09-04
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface OnKeywordChangedListener {
    /**
     * invoke when input new keyword, you can stop last search task here
     *
     * @param keyword keyword for search
     */
    void onKeywordChanged(String keyword);
}
