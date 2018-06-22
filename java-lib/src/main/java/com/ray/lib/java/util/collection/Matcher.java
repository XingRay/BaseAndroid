package com.ray.lib.java.util.collection;

/**
 * @author : leixing
 * @date : 2018/6/22 17:39
 * <p>
 * description : matcher
 */
public interface Matcher<T> {
    boolean isMatch(T t1, T t2);
}
