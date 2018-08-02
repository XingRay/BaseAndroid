package com.ray.lib.android.base.recyclerview;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author : leixing
 * email : leixing@baidu.com
 * @date : 2018/8/2 14:31
 * <p>
 * description : xxx
 */
public class Util {
    public static <T> int getSize(Iterable<T> iterable) {
        if (iterable == null) {
            return 0;
        }
        if (iterable instanceof Collection) {
            return ((Collection<T>) iterable).size();
        }
        int size = 0;
        for (T e : iterable) {
            size++;
        }
        return size;
    }

    public static boolean isOutOfIndex(Iterable<?> iterable, int index) {
        return index < 0 || index >= getSize(iterable);
    }

    public static <T> void move(List<T> list, int fromIndex, int toIndex) {
        if (isOutOfIndex(list, fromIndex)) {
            return;
        }

        if (isOutOfIndex(list, toIndex)) {
            return;
        }

        if (fromIndex == toIndex) {
            return;
        }

        if (fromIndex > toIndex) {
            for (int i = fromIndex; i > toIndex; i--) {
                Collections.swap(list, i, i - 1);
            }
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                Collections.swap(list, i, i + 1);
            }
        }
    }
}
