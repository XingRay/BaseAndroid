package com.ray.baseandroid.util;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CollectionUtil {
    /**
     * 判断集合、map、数组是否为空
     *
     * @return 集合是否为空
     */
    public static <T extends Collection<?>> boolean isEmpty(T collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T extends Map<?, ?>> boolean isEmpty(T map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(JSONArray array) {
        return array == null || array.length() == 0;
    }

    /**
     * 判断集合、Map、数组是否含有元素
     *
     * @return 是否含有元素
     */
    public static boolean hasElem(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean hasElem(Map map) {
        return !isEmpty(map);
    }

    public static <T> boolean hasElem(T[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(char[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(int[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(long[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(float[] array) {
        return !isEmpty(array);
    }

    public static boolean hasElem(double[] array) {
        return !isEmpty(array);
    }

    /**
     * 获取集合/map/数组的大小
     *
     * @param collection 集合
     * @return 集合的大小
     */
    public static int getSize(Collection collection) {
        return isEmpty(collection) ? 0 : collection.size();
    }

    public static int getSize(Map map) {
        return isEmpty(map) ? 0 : map.size();
    }

    public static <T> int getSize(T[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(boolean[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(byte[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(char[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(int[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(long[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(float[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(double[] array) {
        return isEmpty(array) ? 0 : array.length;
    }

    public static int getSize(JSONArray array) {
        return isEmpty(array) ? 0 : array.length();
    }

    /**
     * 能否指定位置获取非空元素
     *
     * @param array    数组
     * @param position 获取元素的位置
     * @param <T>      泛型参数
     * @return 获取到的元素值
     */
    public static <T> boolean canGetElem(T[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length && array[position] != null;
    }

    public static <T> boolean canGetElem(List<T> list, int position) {
        return !isEmpty(list) && position >= 0 && position < list.size() && list.get(position) != null;
    }

    public static boolean canGetElem(boolean[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(byte[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(char[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(int[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(long[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(float[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    public static boolean canGetElem(double[] array, int position) {
        return !isEmpty(array) && position >= 0 && position < array.length;
    }

    /**
     * 是否是有效的下标
     *
     * @param collection 用于判断的集合
     * @param index      待判断的下标
     * @return 下标是否是集合有效的下标
     */
    public static boolean isValidIndex(Collection<?> collection, int index) {
        if (collection == null) {
            throw new IllegalArgumentException("collection can not be null");
        }

        return index >= 0 && index < collection.size();
    }

    /**
     * 安全的从指定位置获取元素，无法取到则返回空
     *
     * @param list     列表
     * @param position 位置
     * @param <T>      泛型参数
     * @return 获取到的元素的值，没有获取则返回{@code null}
     */
    public static <T> T safetyGet(List<T> list, int position) {
        T t = null;
        if (canGetElem(list, position)) {
            t = list.get(position);
        }

        return t;
    }

    public static <T> T safetyGet(T[] array, int position) {
        T t = null;
        if (canGetElem(array, position)) {
            t = array[position];
        }

        return t;
    }

    /**
     * 查找元素在数组中的下标, 如果数组为空或者没有找到元素则返回-1
     *
     * @param e     元素的值
     * @param array 数组
     * @param <E>   泛型参数
     * @return 元素在数组中的位置，没有找到则返回{@code -1}
     */
    public static <E> int getIndexInArray(E e, E[] array) {
        if (e == null || isEmpty(array)) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (e.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 从数组中获取元素, 如果数组为空或者下标越界则返回空
     *
     * @param index 下标
     * @param array 数组
     * @param <E>   泛型参数
     * @return 获取到的元素的值
     */
    public static <E> E getElementInArray(int index, E[] array) {
        if (array == null || isEmpty(array)) {
            return null;
        }

        return array[index];
    }

    /**
     * 从已有的list中抽取出一个没有重复元素的新的List
     *
     * @param list 列表
     * @param <E>  泛型参数
     * @return 去重后的元素
     */
    public static <E> List<E> getDistinctList(Collection<E> list) {
        ArrayList<E> newList = new ArrayList<>();

        if (isEmpty(list)) {
            return newList;
        }

        for (E e : list) {
            if (newList.contains(e)) {
                continue;
            }

            newList.add(e);
        }

        return newList;
    }

    /**
     * 移动列表只的元素,示例：
     * items = (0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
     * move(items, 0, 2)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     * move(items, 0, 9)->{2, 0, 3, 4, 5, 6, 7, 8, 9, 1}
     * move(items, 9, 0)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     * move(items, 4, 4)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     *
     * @param items     待移动的列表
     * @param fromIndex 待移动的元素的下标
     * @param toIndex   元素移动后的下标
     * @param <T>       元素的类型参数
     */
    public static <T> void move(List<T> items, int fromIndex, int toIndex) {
        if (!isValidIndex(items, fromIndex)) {
            throw new IllegalArgumentException("illegal index:" + fromIndex + ", size:" + items.size());
        }

        if (!isValidIndex(items, toIndex)) {
            throw new IllegalArgumentException("illegal index:" + toIndex + ", size:" + items.size());
        }

        if (fromIndex == toIndex) {
            return;
        }

        if (fromIndex > toIndex) {
            for (int i = fromIndex; i > toIndex; i--) {
                Collections.swap(items, i, i - 1);
            }
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                Collections.swap(items, i, i + 1);
            }
        }
    }

    /**
     * 合并两个List
     *
     * @param list1 列表1
     * @param list2 列表2
     * @param <T>   列表的泛型参数
     * @return 两个列表合并的新的列表
     */
    public static <T> List<T> merge(Collection<T> list1, Collection<T> list2) {
        ArrayList<T> list = new ArrayList<>();

        if (list1 != null) {
            for (T t : list1) {
                if (t == null) {
                    continue;
                }
                list.add(t);
            }
        }

        if (list2 != null) {
            for (T t : list2) {
                if (t == null) {
                    continue;
                }

                if (list.contains(t)) {
                    continue;
                }

                list.add(t);
            }
        }

        return list;
    }

    /**
     * 按照指定的条件，从数组中寻找满足条件的元素的下标，找不到则返回-1
     *
     * @param source    数组源
     * @param condition 匹配的条件
     * @param judge     判断是否匹配的回调
     * @param <T>       数组的类型参数
     * @param <C>       匹配条件的类型参数
     * @return 匹配元素的下标，没有匹配的元素返回{@code -1}
     */
    public static <T, C> int getIndex(T[] source, C condition, TargetJudge<T, C> judge) {
        return getIndex(source, 0, condition, judge);
    }

    /**
     * 按照指定的条件，从指定的起始位置开始从数组中寻找满足条件的元素的下标，找不到则返回-1
     *
     * @param source     数组源
     * @param firstIndex 进行匹配的初始位置
     * @param condition  匹配的条件
     * @param judge      判断是否匹配的回调
     * @param <T>        数组的类型参数
     * @param <C>        匹配条件的类型参数
     * @return 匹配元素的下标，没有匹配的元素返回{@code -1}
     */
    public static <T, C> int getIndex(T[] source, int firstIndex, C condition, TargetJudge<T, C> judge) {
        if (isEmpty(source) || firstIndex >= source.length || condition == null) {
            return -1;
        }

        for (int i = firstIndex; i < source.length; i++) {
            if (source[i] == null) {
                continue;
            }

            if (judge.isFound(source[i], condition)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * 按照指定的条件，从列表中寻找满足条件的元素的下标，找不到则返回-1
     *
     * @param source    数据源列表
     * @param condition 条件参数
     * @param judge     判断匹配的回调
     * @param <T>       数据源的参数类型
     * @param <C>       匹配条件的参数类型
     * @return 第一个匹配元素的下标
     */
    public static <T, C> int getIndex(List<T> source, C condition, TargetJudge<T, C> judge) {
        return getIndex(source, 0, condition, judge);
    }

    /**
     * 按照指定的条件，从指定的起始位置开始从列表中寻找满足条件的元素的下标，找不到则返回-1
     *
     * @param source     数据源列表
     * @param firstIndex 第一次进行匹配的元素的下标
     * @param condition  匹配的条件
     * @param judge      判断匹配的条件
     * @param <T>        列表参数的类型
     * @param <C>        匹配条件的类型
     * @return 第一个匹配元素的下标
     */
    public static <T, C> int getIndex(List<T> source, int firstIndex, C condition, TargetJudge<T, C> judge) {
        if (isEmpty(source) || firstIndex >= source.size() || condition == null) {
            return -1;
        }

        for (int i = firstIndex; i < source.size(); i++) {
            if (source.get(i) == null) {
                continue;
            }

            if (judge.isFound(source.get(i), condition)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 判断两个集合所包含的元素是否一样
     * A包含B B包含A 则 A==B
     *
     * @param set1 集合1
     * @param set2 集合2
     * @param <T>  集合类型参数
     * @return 两个集合的元素是否相同
     */
    public static <T> boolean equals(Set<T> set1, Set<T> set2) {
        if (set1 == set2) {
            return true;
        }
        if (set1 == null || set2 == null) {
            return false;
        }

        if (set1.size() != set2.size()) {
            return false;
        }

        //
        for (T t : set1) {
            if (t == null) {
                continue;
            }

            if (!set2.contains(t)) {
                return false;
            }
        }

        for (T t : set2) {
            if (t == null) {
                continue;
            }

            if (!set1.contains(t)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 过滤list中的重复Key值的元素，并且保持原有的顺序不变
     *
     * @param list      列表
     * @param keyGetter key获取器
     * @param <T>       元素类型参数
     * @param <K>       Key的类型参数
     * @return 没有重复Key值的列表
     */
    public static <T, K> List<T> distinct(List<T> list, KeyGetter<T, K> keyGetter) {
        List<T> distinct = new ArrayList<>();

        if (isEmpty(list)) {
            return distinct;
        }

        Set<K> set = new HashSet<>();
        for (T t : list) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(t);
            if (key == null) {
                continue;
            }

            if (set.contains(key)) {
                continue;
            }

            set.add(key);
            distinct.add(t);
        }
        set.clear();
        return distinct;
    }

    /**
     * 从大集合中获取包含指定主键的子集
     *
     * @param list       列表
     * @param collection 键值的集合
     * @param keyGetter  键值获取器
     * @param <T>        列表元素的类型参数
     * @param <K>        键值的类型参数
     * @return 子集
     */
    public static <T, K> List<T> subCollectionInclude(List<T> list, Collection<K> collection, KeyGetter<T, K> keyGetter) {
        List<T> subList = new ArrayList<>();
        if (isEmpty(list)) {
            return subList;
        }

        for (T t : list) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(t);
            if (key == null) {
                continue;
            }
            if (collection.contains(key)) {
                subList.add(t);
            }
        }

        return subList;
    }

    /**
     * 从大集合中获取不包含指定主键的子集
     *
     * @param list       列表
     * @param collection 键值集合
     * @param keyGetter  键值获取器
     * @param <T>        元素类型参数
     * @param <K>        键值类型参数
     * @return 子集
     */
    public static <T, K> List<T> subCollectionExclude(List<T> list, Collection<K> collection, KeyGetter<T, K> keyGetter) {
        List<T> subList = new ArrayList<>();
        if (isEmpty(list)) {
            return subList;
        }

        for (T t : list) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(t);
            if (key == null) {
                continue;
            }
            if (collection.contains(key)) {
                continue;
            }

            subList.add(t);
        }

        return subList;
    }

    /**
     * 对集合中的每个元素遍历并进行操作
     *
     * @param collection 集合
     * @param operator   操作器
     * @param <T>        集合元素的参数类型
     * @param <C>        集合类型
     */
    public static <T, C extends Collection<T>> void handle(C collection, Operator<T> operator) {
        if (isEmpty(collection)) {
            return;
        }

        int index = -1;
        for (T t : collection) {
            index++;
            if (t == null) {
                continue;
            }

            operator.operate(t, index);
        }
    }

    /**
     * 对列表中的每个元素遍历并进行操作
     *
     * @param list     列表
     * @param operator 操作器
     * @param <T>      元素类型
     */
    public static <T> void handle(List<T> list, Operator<T> operator) {
        if (isEmpty(list)) {
            return;
        }

        for (int index = 0, size = list.size(); index < size; index++) {
            T t = list.get(index);
            if (t == null) {
                continue;
            }

            operator.operate(t, index);
        }
    }

    /**
     * 从集合中抽取指定的Key的集合
     *
     * @param collection 集合
     * @param keyGetter  键值获取器
     * @param <T>        集合元素类型参数
     * @param <K>        键值类型参数
     * @return 键值的集合
     */
    public static <T, K> Set<K> getKeySet(Collection<T> collection, KeyGetter<T, K> keyGetter) {
        HashSet<K> keySet = new HashSet<>();

        if (isEmpty(collection)) {
            return keySet;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(t);
            if (key == null) {
                continue;
            }

            keySet.add(key);
        }

        return keySet;
    }

    /**
     * 从集合中抽取指定的Key的集合
     *
     * @param collection
     * @param keyGetter
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> ArrayList<K> getKeyList(Collection<T> collection, KeyGetter<T, K> keyGetter) {
        ArrayList<K> keyList = new ArrayList<>();

        if (isEmpty(collection)) {
            return keyList;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(t);
            if (key == null) {
                continue;
            }

            keyList.add(key);
        }

        return keyList;
    }

    /**
     * 对集合进行统计，取出满足筛选条件的子集
     *
     * @param collection
     * @param filter
     * @param <T>
     * @return
     */
    public static <T> List<T> subCollection(Collection<T> collection, Filter<T> filter) {
        List<T> list = new ArrayList<>();

        if (isEmpty(collection)) {
            return list;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            if (filter.isFit(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T, K> T find(Collection<T> collection, K key, Finder<T, K> finder) {
        if (isEmpty(collection)) {
            return null;
        }

        if (key == null) {
            return null;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            if (finder.isFound(t, key)) {
                return t;
            }
        }

        return null;
    }

    public static <T, K> List<T> findAll(Collection<T> collection, K key, Finder<T, K> finder) {
        List<T> list = new ArrayList<>();

        if (isEmpty(collection)) {
            return list;
        }

        if (key == null) {
            return list;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            if (finder.isFound(t, key)) {
                list.add(t);
            }
        }

        return list;
    }

    /**
     * 获取最大最小长度对
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> Pair getSizePair(T[][] arrays) {
        if (arrays == null) {
            throw new NullPointerException("arrays can not be null");
        }

        boolean initialized = false;
        int min = 0;
        int max = 0;


        for (T[] array : arrays) {
            if (initialized) {
                max = Math.max(array.length, max);
                min = Math.min(array.length, min);
            } else {
                max = array.length;
                min = array.length;
                initialized = true;
            }
        }

        if (!initialized) {
            throw new IllegalStateException("no array found");
        }

        return new Pair(max, min);
    }

    /**
     * 获取最小的长度
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> int getMinSize(T[][] arrays) {
        return getSizePair(arrays).getMin();
    }

    /**
     * 获取最大的长度
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> int getMaxSize(T[][] arrays) {
        return getSizePair(arrays).getMax();
    }

    /**
     * 获取
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> T getLastCommonElement(T[][] arrays) {
        if (arrays == null) {
            throw new IllegalArgumentException("can not get common element form null");
        }

        int minSize = getMinSize(arrays);
        T commonElement = null;

        for (int elementIndex = 0; elementIndex < minSize; elementIndex++) {
            T tmpElement = null;
            for (T[] array : arrays) {
                if (array == null) {
                    continue;
                }

                T t = array[elementIndex];
                if (t == null) {
                    return commonElement;
                }

                if (tmpElement == null) {
                    tmpElement = t;
                } else if (!tmpElement.equals(t)) {
                    return commonElement;
                }
            }

            commonElement = tmpElement;
        }

        return commonElement;
    }

    public static <T> List<T> clone(List<T> src, CloneFactory<T> factory) {
        if (factory == null) {
            throw new IllegalArgumentException("factory can not be null");
        }

        List<T> cloneList = new ArrayList<>();

        if (isEmpty(src)) {
            return cloneList;
        }

        for (T t : src) {
            if (t == null) {
                continue;
            }

            T clone = factory.clone(t);
            if (clone == null) {
                continue;
            }
            cloneList.add(clone);
        }

        return cloneList;
    }

    /**
     * 判断遍历到的元素是否匹配条件的接口
     */
    public interface TargetJudge<E, C> {
        /**
         * 该元素是否与指定的条件匹配
         *
         * @param elem      元素
         * @param condition 匹配条件
         * @return 是否找到匹配的元素
         */
        boolean isFound(E elem, C condition);
    }

    public interface Operator<T> {
        void operate(T t, int index);
    }

    public interface KeyGetter<T, K> {
        K getKey(T t);
    }

    public interface Filter<T> {
        boolean isFit(T t);
    }

    /**
     * 查找器接口
     *
     * @param <T>
     * @param <K>
     */
    public interface Finder<T, K> {
        boolean isFound(T t, K k);
    }

    public interface CloneFactory<T> {
        T clone(T t);
    }

    public static class Pair {
        private int max;
        private int min;

        public Pair(int max, int min) {
            this.max = max;
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "max=" + max +
                    ", min=" + min +
                    '}';
        }
    }
}