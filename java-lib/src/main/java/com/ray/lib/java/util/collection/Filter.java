package com.ray.lib.java.util.collection;

/**
 * @author : leixing
 * @date : 2018/6/22 17:39
 * <p>
 * description : Filter
 */
public interface Filter<T> {
    boolean isSelected(T t);
}