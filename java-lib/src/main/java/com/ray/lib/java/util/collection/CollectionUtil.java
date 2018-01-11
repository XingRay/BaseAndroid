package com.ray.lib.java.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : leixing
 * @date : 2018-01-08
 * <p>
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue", "SameParameterValue"})
public class CollectionUtil {
    /**
     * 判断集合、map、数组是否为空
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty() || collection.size() == 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty() || map.size() == 0;
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

//    public static boolean isEmpty(JSONArray array) {
//        return array == null || array.length() == 0;
//    }

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

//    public static int getSize(JSONArray array) {
//        return isEmpty(array) ? 0 : array.length();
//    }

    /**
     * 能否指定位置获取非空元素
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

    public static <T> int indexOf(T[] array, T t) {
        if (isEmpty(array)) {
            return -1;
        }
        for (int i = 0, size = array.length; i < size; i++) {
            T elem = array[i];
            if (elem.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 安全的从指定位置获取元素，无法取到则返回空
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

    public static <T> boolean safetyAdd(Collection<T> collection, T item) {
        return item != null && collection.add(item);
    }

    public static <T> boolean safetyAddAll(Collection<T> collection, Collection<? extends T> items) {
        return !isEmpty(items) && collection.addAll(items);
    }

    /**
     * 查找元素在数组中的下标, 如果数组为空或者没有找到元素则返回-1
     *
     * @param array 数组
     * @param e     待查找的元素
     * @param <E>   元素的类型
     * @return 元素的下标，没有找到返回-1
     */
    public static <E> int getIndexInArray(E[] array, E e) {
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
     */
    public static <E> E getElementInArray(int index, E[] array) {
        if (array == null || isEmpty(array)) {
            return null;
        }

        return array[index];
    }

    /**
     * 从已有的list中抽取出一个没有重复元素的新的List
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
     */
    public static <T, C> int getIndex(T[] source, C condition, TargetJudge<T, C> judge) {
        return getIndex(source, 0, condition, judge);
    }

    /**
     * 按照指定的条件，从指定的起始位置开始从数组中寻找满足条件的元素的下标，找不到则返回-1
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
     */
    public static <T, C> int getIndex(List<T> source, C condition, TargetJudge<T, C> judge) {
        return getIndex(source, 0, condition, judge);
    }

    /**
     * 按照指定的条件，从指定的起始位置开始从列表中寻找满足条件的元素的下标，找不到则返回-1
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
     */
    public static <T> boolean contentEquals(Set<T> set1, Set<T> set2) {
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

    public static boolean hasCommonElem(Collection<?> collection1, Collection<?> collection2) {
        if (isEmpty(collection1) || isEmpty(collection2)) {
            return false;
        }
        HashSet<?> set1 = new HashSet<>(collection1);
        HashSet<?> set2 = new HashSet<>(collection2);

        set1.retainAll(set2);
        return set1.size() > 0;
    }

    /**
     * 判断遍历到的元素是否匹配条件的接口
     */
    public interface TargetJudge<E, C> {
        /**
         * 该元素是否与指定的条件匹配
         *
         * @param elem      元素
         * @param condition 条件
         * @return 是否匹配
         */
        boolean isFound(E elem, C condition);
    }

    /**
     * 过滤list中的重复元素，并且保持原有的顺序不变
     */
    public static <T, K> List<T> distinct(Iterable<T> iterable, KeyGetter<T, K> keyGetter) {

        List<T> distinct = new ArrayList<>();

        if (iterable == null) {
            return distinct;
        }

        Set<K> set = new HashSet<>();
        int index = 0;

        for (T t : iterable) {
            K key = keyGetter.getKey(index, t);
            if (key != null && !set.contains(key)) {
                set.add(key);
                distinct.add(t);
            }
            index++;
        }

        return distinct;
    }

    /**
     * 从大集合中获取包含指定主键的子集
     */
    public static <T, K> List<T> subCollectionInclude(List<T> list, Collection<K> collection, KeyGetter<T, K> keyGetter) {
        List<T> subList = new ArrayList<>();
        if (isEmpty(list)) {
            return subList;
        }
        int index = 0;

        for (T t : list) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(index, t);
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
     */
    public static <T, K> List<T> subCollectionExclude(List<T> list, Collection<K> collection, KeyGetter<T, K> keyGetter) {
        List<T> subList = new ArrayList<>();
        if (isEmpty(list)) {
            return subList;
        }
        int index = 0;
        for (T t : list) {
            if (t == null) {
                continue;
            }

            K key = keyGetter.getKey(index, t);
            if (key != null && !collection.contains(key)) {
                subList.add(t);
            }
            index++;
        }

        return subList;
    }

    /**
     * 对集合中的每个元素遍历并进行操作
     */
    public static <T> void handle(Collection<T> collection, Operation<T> operation) {
        if (isEmpty(collection)) {
            return;
        }

        int index = -1;
        for (T t : collection) {
            index++;
            if (t == null) {
                continue;
            }

            operation.operate(index, t);
        }
    }

    /**
     * 对列表中的每个元素遍历并进行操作
     */
    public static <T> void traversal(Iterable<T> iterable, Operation<T> operation) {
        if (iterable == null) {
            return;
        }

        int index = 0;
        for (T t : iterable) {
            if (t != null) {
                operation.operate(index, t);
            }
            index++;
        }
    }

    /**
     * 从集合中抽取指定的Key的集合
     */
    public static <T, K> Set<K> getKeySet(Iterable<T> iterable, KeyGetter<T, K> keyGetter) {
        HashSet<K> keySet = new HashSet<>();

        if (iterable == null) {
            return keySet;
        }

        Iterator<T> iterator = iterable.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t != null) {
                K key = keyGetter.getKey(index, t);
                if (key == null) {
                    continue;
                }
                keySet.add(key);
            }
            index++;
        }

        return keySet;
    }

    /**
     * 从集合中抽取指定的Key的集合
     */
    public static <T, K> ArrayList<K> getKeyList(Iterable<T> iterable, KeyGetter<T, K> keyGetter) {
        ArrayList<K> keyList = new ArrayList<>();

        if (iterable == null) {
            return keyList;
        }

        Iterator<T> iterator = iterable.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t != null) {
                K key = keyGetter.getKey(index, t);
                if (key != null) {
                    keyList.add(key);
                }
            }
            index++;
        }

        return keyList;
    }

    public interface Operation<T> {
        /**
         * 遍历操作
         *
         * @param index 下标
         * @param t     对象
         */
        void operate(int index, T t);
    }

    public interface KeyGetter<T, K> {
        /**
         * 获取Key
         *
         * @param index 下标
         * @param t     对象
         * @return Key
         */
        K getKey(int index, T t);
    }

    /**
     * 对集合进行统计，取出满足筛选条件的子集
     */
    public static <T> List<T> subCollection(Iterable<T> iterable, Filter<T> filter) {
        List<T> list = new ArrayList<>();

        if (iterable == null) {
            return list;
        }

        Iterator<T> iterator = iterable.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.isFit(index, t)) {
                list.add(t);
            }
            index++;
        }
        return list;
    }

    public interface Filter<T> {
        /**
         * 匹配器
         *
         * @param index 下标
         * @param t     元素
         * @return 是否匹配
         */
        boolean isFit(int index, T t);
    }

    /**
     * 查找器接口
     *
     * @param <T> 元素类型
     */
    public interface Finder<T> {
        /**
         * 查找器
         *
         * @param index 下标
         * @param t     元素
         * @return 是否找到
         */
        boolean isFound(int index, T t);
    }

    public static <T> T find(Iterable<T> iterable, Finder<T> finder) {
        if (iterable == null) {
            return null;
        }

        Iterator<T> iterator = iterable.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T t = iterator.next();
            if (finder.isFound(index, t)) {
                return t;
            }
            index++;
        }

        return null;
    }

    public static <T> T find(T[] array, Finder<T> finder) {
        if (isEmpty(array)) {
            return null;
        }

        for (int i = 0, size = array.length; i < size; i++) {
            T t = array[i];
            if (finder.isFound(i, t)) {
                return t;
            }
        }

        return null;
    }


    public static <T> int indexOf(Iterable<T> iterable, Finder<T> finder) {
        if (iterable == null) {
            return -1;
        }

        int index = 0;
        for (T t : iterable) {
            if (t != null) {
                if (finder.isFound(index, t)) {
                    return index;
                }
            }
        }

        return -1;
    }

    public static <T> List<T> findAll(Iterable<T> iterable, Finder<T> finder) {
        List<T> list = new ArrayList<>();

        if (iterable == null) {
            return list;
        }

        int index = 0;
        for (T t : iterable) {
            if (t != null) {
                if (finder.isFound(index, t)) {
                    list.add(t);
                }
            }

            index++;
        }

        return list;
    }

    /**
     * 获取最大最小长度对
     */
    public static <T> Pair getSizePair(T[][] arrays) {
        if (arrays == null) {
            throw new NullPointerException("arrays can not be null");
        }

        boolean initialized = false;
        int min = 0;
        int max = 0;


        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
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
     */
    public static <T> int getMinSize(T[][] arrays) {
        return getSizePair(arrays).getMin();
    }

    /**
     * 获取最大的长度
     */
    public static <T> int getMaxSize(T[][] arrays) {
        return getSizePair(arrays).getMax();
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

    /**
     * 获取
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

    public interface CloneFactory<T> {
        /**
         * 复制
         *
         * @param t 元素
         * @return 复制的对象
         */
        T clone(T t);
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

    public static int compare(boolean x, boolean y) {
        return (x == y) ? 0 : (x ? 1 : -1);
    }

    public static int compare(byte x, byte y) {
        return x - y;
    }

    public static int compare(char x, char y) {
        return x - y;
    }

    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public static int compare(long x, long y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public static int compare(float f1, float f2) {
        if (f1 < f2) {
            return -1;
        }

        if (f1 > f2) {
            return 1;
        }


        int thisBits = Float.floatToIntBits(f1);
        int anotherBits = Float.floatToIntBits(f2);

        return (thisBits == anotherBits ? 0 : (thisBits < anotherBits ? -1 : 1));
    }

    public static int compare(double d1, double d2) {
        if (d1 < d2) {
            return -1;
        }

        if (d1 > d2) {
            return 1;
        }

        long thisBits = Double.doubleToLongBits(d1);
        long anotherBits = Double.doubleToLongBits(d2);

        return (thisBits == anotherBits ? 0 : (thisBits < anotherBits ? -1 : 1));
    }

    public static <K, V> List<Map.Entry<K, V>> getEntryList(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new ArrayList<>();
        if (isEmpty(map)) {
            return entries;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry == null) {
                continue;
            }
            entries.add(entry);
        }

        return entries;
    }

    /**
     * 从一个列表数据转化为另一个列表数据
     *
     * @param srcList 源列表数据
     * @return 转化结果列表数据
     */
    public static <T, E> List<E> convert(Iterable<T> srcList, Converter<T, E> converter) {
        List<E> dstList = new ArrayList<>();
        if (srcList == null) {
            return dstList;
        }
        int i = 0;
        for (T t : srcList) {
            dstList.add(converter.convert(i, t));
            i++;
        }

        return dstList;
    }

    /**
     * 从一个数组数据转化为另一个数组数据
     *
     * @param srcArray 源列表数据
     * @param dstArray 转化结果列表数据
     */
    public static <T, E> void convert(T[] srcArray, E[] dstArray, Converter<T, E> converter) {
        if (srcArray == null || dstArray == null) {
            return;
        }

        for (int i = 0, size = Math.min(srcArray.length, dstArray.length); i < size; i++) {
            T t = srcArray[i];
            dstArray[i] = converter.convert(i, t);
        }
    }

    public interface Converter<T, E> {
        /**
         * 转换器
         *
         * @param index 下标
         * @param t     元素
         * @return 转换结果
         */
        E convert(int index, T t);
    }

    /**
     * 合并多个数组
     */
    @SafeVarargs
    public static <T> T[] concat(T[] first, T[]... others) {
        T[] result;
        int otherSize = others.length;

        if (otherSize > 0) {
            int nLen = first.length;
            int temLen = first.length;

            for (T[] other : others) {
                if (null == other) {
                    continue;
                }
                nLen += other.length;
            }

            result = Arrays.copyOf(first, nLen);

            for (T[] other : others) {
                if (null == other || other.length == 0) {
                    continue;
                }
                System.arraycopy(other, 0, result, temLen, other.length);
                temLen += other.length;
            }
        } else {
            result = first;
        }

        return result;
    }

    public static <T> boolean contains(T[] container, T[] elements) {
        if (isEmpty(elements)) {
            return true;
        }
        if (isEmpty(container)) {
            return false;
        }
        for (int i = 0, elementSize = getSize(elements); i < elementSize; i++) {
            T element = elements[i];
            if (!contains(container, element)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean contains(T[] container, T element) {
        if (element == null) {
            return true;
        }
        if (isEmpty(container)) {
            return false;
        }
        for (int i = 0, size = getSize(container); i < size; i++) {
            T t = container[i];
            if (t == null) {
                continue;
            }
            if (t.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public static <T> List<T> toList(T[] array) {
        if (isEmpty(array)) {
            return new ArrayList<>();
        }

        return Arrays.asList(array);
    }
}