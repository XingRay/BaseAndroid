package com.ray.baseandroid.widget.dialogactivity;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 弹窗Activity的管理器，用于管理事件和{@link DialogAdapter}
 */

public class DialogActivityManager {
    private static volatile DialogActivityManager INSTANCE;

    /**
     * 事件id，每次获取时数值+1
     */
    private AtomicLong eventId;
    private final Map<Long, DialogAdapter> mAdapters;

    public static DialogActivityManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DialogActivityManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DialogActivityManager();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * 弹出dialog
     *
     * @param context    上下文信息
     * @param cancelable 是否可以去取消
     * @param priority   优先级
     * @param width      弹窗的宽度
     * @param height     弹窗的高度
     * @param adapter    适配器
     */
    public DialogOperator showDialog(Context context, boolean cancelable, long priority, int width, int height, DialogAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter can not be null");
        }

        long eventId = generateEventId();
        mAdapters.put(eventId, adapter);

        DialogActivity.showDialog(context, eventId, cancelable, priority, width, height);
        return adapter;
    }

    public DialogOperator showDialog(Context context, boolean cancelable, long priority, DialogAdapter adapter) {
        return showDialog(context, cancelable, priority, 160, 280, adapter);
    }

    public DialogOperator showDialog(Context context, boolean cancelable, DialogAdapter adapter) {
        return showDialog(context, cancelable, 0, adapter);
    }

    public DialogOperator showDialog(Context context, DialogAdapter adapter) {
        return showDialog(context, false, adapter);
    }

    /**
     * 获取缓存的Adapter
     *
     * @param eventId
     * @return
     */
    DialogAdapter getAdapter(long eventId) {
        return mAdapters.get(eventId);
    }

    /**
     * 从缓存中移除adapter
     *
     * @param eventId
     */
    void removeAdapter(long eventId) {
        mAdapters.remove(eventId);
    }

    private DialogActivityManager() {
        eventId = new AtomicLong(0);

        //API最低等级到16时，可以使用以下优化
        //LongSparseArray<DialogActivityAdapter> mAdapters = new LongSparseArray<>();
        mAdapters = new HashMap<>();
    }

    /**
     * 产生一个唯一的事件id
     *
     * @return
     */
    private long generateEventId() {
        return eventId.getAndIncrement();
    }
}
