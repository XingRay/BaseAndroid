package com.ray.baseandroid.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.baseandroid.R;
import com.ray.lib.android.util.ViewUtil;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Author      : leixing
 * Date        : 2017-05-23
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */
public class RcvMessageViewBinder extends ItemViewBinder<Message, RcvMessageViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_message_rcv, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Message message) {
        ViewUtil.setText(holder.itemView, R.id.tv_from, message.getFrom());
        ViewUtil.setText(holder.itemView, R.id.tv_content, message.getContent());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
