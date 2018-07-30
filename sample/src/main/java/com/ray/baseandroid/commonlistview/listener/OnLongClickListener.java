package com.ray.baseandroid.commonlistview.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-07
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : 条目的长按事件接口
 */

public interface OnLongClickListener {
    boolean onLongClick(RecyclerView parent, View view, int position, int id);
}
