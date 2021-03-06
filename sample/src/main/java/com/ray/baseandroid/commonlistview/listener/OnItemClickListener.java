package com.ray.baseandroid.commonlistview.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author      : leixing
 * @date        : 2017-04-07
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : Recyclerview 的条目点击事件接口
 */

public interface OnItemClickListener {
    void onItemClick(RecyclerView parent, View view, int position, int id);
}
