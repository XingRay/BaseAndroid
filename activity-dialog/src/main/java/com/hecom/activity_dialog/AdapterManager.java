package com.hecom.activity_dialog;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Author      : leixing
 * Date        : 2017-06-12
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : manager for adapters, use {@code Map} to cache {@link DialogAdapter},
 * {@link HostActivity} get adapter by {@code AdapterManager}
 */

public class AdapterManager {
    private static volatile AdapterManager INSTANCE;

    private final Map<Long, DialogAdapter> mAdapters;


    public static AdapterManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AdapterManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdapterManager();
                }
            }
        }

        return INSTANCE;
    }


    @SuppressLint("UseSparseArrays")
    private AdapterManager() {
        //LongSparseArray<DialogActivityAdapter> mAdapters = new LongSparseArray<>();
        mAdapters = new HashMap<>();
    }

    /**
     * save a {@link DialogAdapter}
     *
     * @param code    unique code for {@code DialogAdapter}
     * @param adapter adapter for render dialog UI
     */
    public DialogAdapter put(long code, DialogAdapter adapter) {
        synchronized (mAdapters) {
            return mAdapters.put(code, adapter);
        }
    }

    /**
     * get {@code DialogAdapter} form cache by unique code
     *
     * @param code unique code for {@code DialogAdapter}
     * @return cached adapter
     */
    public DialogAdapter get(long code) {
        synchronized (mAdapters) {
            return mAdapters.get(code);
        }
    }

    /**
     * remove {@code DialogAdapter} from cache
     *
     * @param code unique code for adapter
     * @return previous adapter save by the {@code code}
     */
    public DialogAdapter remove(long code) {
        synchronized (mAdapters) {
            return mAdapters.remove(code);
        }
    }
}
