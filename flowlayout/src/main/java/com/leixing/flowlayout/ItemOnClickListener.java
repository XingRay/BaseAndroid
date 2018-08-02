
package com.leixing.flowlayout;

import android.view.ViewGroup;

/**
 * @author : leixing
 * email : leixing1012@qq.com
 * @date : 2018/8/1 11:26
 * <p>
 * description : 条目点击事件监听器
 */
public interface ItemOnClickListener<T> {
    /**
     * 条目点击事件回调
     *
     * @param parent   父布局
     * @param position 点击的位置
     * @param t        对应的数据
     */
    void onItemClick(ViewGroup parent, int position, T t);
}
