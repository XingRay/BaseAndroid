package com.ray.lib.java.util.collection;

/**
 * @author : leixing
 * @date : 2018/6/22 17:39
 * <p>
 * description : Extractor
 */
public interface Extractor<T, K> {
    K extract(T t);
}