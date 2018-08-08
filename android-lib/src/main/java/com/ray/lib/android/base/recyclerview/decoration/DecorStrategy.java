package com.ray.lib.android.base.recyclerview.decoration;

/**
 * @author : leixing
 * @date : 2018-01-22
 * <p>
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface DecorStrategy {
    boolean hasDecor(int position, int itemCount, int headerCount, int footerCount);
}
