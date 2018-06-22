package com.ray.lib.java.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : leixing
 * Date        : 2017-02-20
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : util for collection
 */

@SuppressWarnings({"WeakerAccess", "unused", "BooleanMethodIsAlwaysInverted", "unchecked"})
public class CollectionUtil {

    private CollectionUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
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

    public static boolean hasElement(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean hasElement(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static <T> boolean hasElement(T[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(boolean[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(byte[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(char[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(int[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(long[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(float[] array) {
        return array != null && array.length > 0;
    }

    public static boolean hasElement(double[] array) {
        return array != null && array.length > 0;
    }

    public static int getSize(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static int getSize(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    public static <T> int getSize(T[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(boolean[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(byte[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(char[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(int[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(long[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(float[] array) {
        return array == null ? 0 : array.length;
    }

    public static int getSize(double[] array) {
        return array == null ? 0 : array.length;
    }

    public static boolean isValidIndex(Collection<?> collection, int index) {
        return index < getSize(collection) && index >= 0;
    }

    public static boolean isValidIndex(List<?> list, int index) {
        return index < getSize(list) && index >= 0;
    }

    public static boolean isValidIndex(boolean[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static <T> boolean isValidIndex(T[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(byte[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(char[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(int[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(float[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(double[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isValidIndex(long[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean isOutOfIndex(Collection<?> collection, int index) {
        return index < 0 || index >= getSize(collection);
    }

    public static boolean isOutOfIndex(List<?> list, int index) {
        return index < 0 || index >= getSize(list);
    }

    public static <T> boolean isOutOfIndex(T[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(boolean[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(byte[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(char[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(int[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(float[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(double[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static boolean isOutOfIndex(long[] array, int index) {
        return index < 0 || index >= getSize(array);
    }

    public static <T> boolean hasElementByKey(Map<T, ?> map, T key) {
        return !(map == null || map.isEmpty()) && map.get(key) != null;
    }

    public static <T> boolean hasElementAt(List<T> list, int index) {
        return isValidIndex(list, index) && list.get(index) != null;
    }

    public static <T> boolean hasElementAt(T[] array, int index) {
        return isValidIndex(array, index) && array[index] != null;
    }

    public static boolean hasElementAt(boolean[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(byte[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(char[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(int[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(long[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(float[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static boolean hasElementAt(double[] array, int index) {
        return index < getSize(array) && index >= 0;
    }

    public static <T> T safetyGet(List<T> list, int position) {
        T t = null;
        if (isValidIndex(list, position)) {
            t = list.get(position);
        }

        return t;
    }

    public static <T> T safetyGet(T[] array, int position) {
        T t = null;
        if (isValidIndex(array, position)) {
            t = array[position];
        }

        return t;
    }

    public static <K, V> V safetyGet(Map<K, V> map, K key) {
        return map == null ? null : map.get(key);
    }

    public static <T> int getIndexOf(T t, T[] array) {
        return getIndexOf(t, array, 0);
    }

    public static <T> int getIndexOf(T t, T[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static <T> int getIndexOf(T t, T[] array, int startIndex, Matcher<T> matcher) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            T element = array[i];
            if (matcher.isMatch(t, element)) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(boolean t, boolean[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(boolean t, boolean[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(byte t, byte[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(byte t, byte[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(char t, char[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(char t, char[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(int t, int[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(int t, int[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(float t, float[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(float t, float[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(double t, double[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(double t, double[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static int getIndexOf(long t, long[] array) {
        return getIndexOf(t, array, 0);
    }

    public static int getIndexOf(long t, long[] array, int startIndex) {
        if (array == null) {
            return -1;
        }

        for (int i = startIndex, size = array.length; i < size; i++) {
            if (t == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static <E, T> int getIndexOf(E[] elements, T target, Matcher2<E, T> matcher) {
        return getIndexOf(elements, 0, target, matcher);
    }

    public static <E, T> int getIndexOf(E[] elements, int startIndex, T target, Matcher2<E, T> matcher) {
        if (isOutOfIndex(elements, startIndex)) {
            return -1;
        }

        for (int i = startIndex, size = elements.length; i < size; i++) {
            E element = elements[i];
            if (element == null) {
                continue;
            }

            if (matcher.isMatch(element, target)) {
                return i;
            }
        }

        return -1;
    }

    public static <E, T> int getIndexOf(List<E> elements, T target, Matcher2<E, T> matcher) {
        return getIndexOf(elements, 0, target, matcher);
    }

    public static <E, T> int getIndexOf(List<E> elements, int startIndex, T target, Matcher2<E, T> matcher) {
        if (isOutOfIndex(elements, startIndex)) {
            return -1;
        }

        for (int i = startIndex, size = elements.size(); i < size; i++) {
            E element = elements.get(i);
            if (element == null) {
                continue;
            }

            if (matcher.isMatch(element, target)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * items = (0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
     * move(items, 0, 2)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     * move(items, 0, 9)->{2, 0, 3, 4, 5, 6, 7, 8, 9, 1}
     * move(items, 9, 0)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     * move(items, 4, 4)->{1, 2, 0, 3, 4, 5, 6, 7, 8, 9}
     */
    public static <T> void move(List<T> items, int fromIndex, int toIndex) {
        if (isOutOfIndex(items, fromIndex)) {
            throw new IllegalArgumentException("illegal index:" + fromIndex + ", size:" + items.size());
        }

        if (isOutOfIndex(items, toIndex)) {
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

    public static <T> void removeDuplicates(Collection<T> collection) {
        if (isEmpty(collection)) {
            return;
        }
        Set<T> set = new HashSet<>();

        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t == null) {
                iterator.remove();
            }
            if (set.contains(t)) {
                iterator.remove();
                continue;
            }
            set.add(t);
        }
    }

    public static <T, K> void removeDuplicates(Collection<T> collection, Extractor<T, K> extractor) {
        if (isEmpty(collection)) {
            return;
        }
        HashSet<K> set = new HashSet<>();

        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (t == null) {
                iterator.remove();
            }
            K k = extractor.extract(t);
            if (k == null) {
                iterator.remove();
            }
            if (set.contains(k)) {
                iterator.remove();
                continue;
            }
            set.add(k);
        }
    }


    public static <T> ArrayList<T> createNoDuplicateList(Collection<T> collection) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }

        HashSet<T> set = new HashSet<>();

        for (T t : collection) {
            if (t == null) {
                continue;
            }
            if (set.contains(t)) {
                continue;
            }
            set.add(t);
            list.add(t);
        }

        return list;
    }

    public static <T, K> ArrayList<T> createNoDuplicateList(Collection<T> collection, Extractor<T, K> extractor) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }

        HashSet<K> set = new HashSet<>();

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            K key = extractor.extract(t);
            if (key == null) {
                continue;
            }

            if (set.contains(key)) {
                continue;
            }

            set.add(key);
            list.add(t);
        }
        set.clear();
        return list;
    }

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

    public static <T> ArrayList<T> merge(Collection<T>... collections) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collections)) {
            return list;
        }
        HashSet<T> set = new HashSet<>();

        for (Collection<T> c : collections) {
            if (c == null) {
                continue;
            }
            for (T t : c) {
                if (t == null) {
                    continue;
                }
                if (set.contains(t)) {
                    continue;
                }
                list.add(t);
                set.add(t);
            }
        }
        return list;
    }

    public static <T, K> ArrayList<T> merge(Extractor<T, K> extractor, Collection<T>... collections) {
        return merge(collections, extractor);
    }

    public static <T, K> ArrayList<T> merge(Collection<T>[] collections, Extractor<T, K> extractor) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collections)) {
            return list;
        }
        HashSet<K> set = new HashSet<>();

        for (Collection<T> c : collections) {
            if (c == null) {
                continue;
            }
            for (T t : c) {
                if (t == null) {
                    continue;
                }
                K k = extractor.extract(t);
                if (k == null) {
                    continue;
                }
                if (set.contains(k)) {
                    continue;
                }
                list.add(t);
                set.add(k);
            }
        }
        return list;
    }

    public static <T> ArrayList<T> subcollectionInclude(Collection<T> collection, Collection<T> target) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }
        if (isEmpty(target)) {
            return list;
        }

        HashSet<T> set;
        if (target instanceof HashSet) {
            set = (HashSet<T>) target;
        } else {
            set = new HashSet<>(target);
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }
            if (set.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T, K> ArrayList<T> subcollectionInclude(Collection<T> collection, Collection<K> target, Extractor<T, K> extractor) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }
        if (isEmpty(target)) {
            return list;
        }

        HashSet<K> set;
        if (target instanceof HashSet) {
            set = (HashSet<K>) target;
        } else {
            set = new HashSet<>(target);
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }
            K k = extractor.extract(t);
            if (k == null) {
                continue;
            }
            if (set.contains(k)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T> ArrayList<T> subcollectionExclude(Collection<T> collection, Collection<T> target) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }
        if (isEmpty(target)) {
            list.addAll(collection);
            return list;
        }

        HashSet<T> set;
        if (target instanceof HashSet) {
            set = (HashSet<T>) target;
        } else {
            set = new HashSet<>(target);
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }
            if (!set.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T, K> ArrayList<T> subCollectionExclude(Collection<T> collection, Collection<K> target, Extractor<T, K> extractor) {
        ArrayList<T> list = new ArrayList<>();
        if (isEmpty(collection)) {
            return list;
        }
        if (isEmpty(target)) {
            list.addAll(collection);
            return list;
        }

        HashSet<K> set;
        if (target instanceof HashSet) {
            set = (HashSet<K>) target;
        } else {
            set = new HashSet<>(target);
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }
            K k = extractor.extract(t);
            if (k == null) {
                continue;
            }
            if (!set.contains(k)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T> void traversal(Collection<T> collection, Processor<T> processor) {
        if (isEmpty(collection)) {
            return;
        }

        int index = -1;
        for (T t : collection) {
            index++;
            if (t == null) {
                continue;
            }

            processor.process(index, t);
        }
    }

    public static <T> void traversal(List<T> list, Processor<T> processor) {
        if (isEmpty(list)) {
            return;
        }

        for (int index = 0, size = list.size(); index < size; index++) {
            T t = list.get(index);
            if (t == null) {
                continue;
            }

            processor.process(index, t);
        }
    }

    public static <T, K> HashSet<K> extractSet(Collection<T> collection, Extractor<T, K> extractor) {
        HashSet<K> set = new HashSet<>();

        if (isEmpty(collection)) {
            return set;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            K key = extractor.extract(t);
            if (key == null) {
                continue;
            }

            set.add(key);
        }

        return set;
    }

    public static <T, K> ArrayList<K> extractList(Collection<T> collection, Extractor<T, K> extractor) {
        ArrayList<K> list = new ArrayList<>();

        if (isEmpty(collection)) {
            return list;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            K key = extractor.extract(t);
            if (key == null) {
                continue;
            }

            list.add(key);
        }

        return list;
    }

    public static <T> ArrayList<T> selectList(Collection<T> collection, Filter<T> filter) {
        ArrayList<T> list = new ArrayList<>();

        if (isEmpty(collection)) {
            return list;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            if (filter.isSelected(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T> HashSet<T> selectSet(Collection<T> collection, Filter<T> filter) {
        HashSet<T> set = new HashSet<>();

        if (isEmpty(collection)) {
            return set;
        }

        for (T t : collection) {
            if (t == null) {
                continue;
            }

            if (filter.isSelected(t)) {
                set.add(t);
            }
        }

        return set;
    }

    public static <T, K> T find(Collection<T> collection, K key, Matcher2<T, K> matcher) {
        return find(collection, 0, key, matcher);
    }

    public static <T, K> T find(Collection<T> collection, int startIndex, K key, Matcher2<T, K> matcher) {
        if (isEmpty(collection)) {
            return null;
        }
        if (isOutOfIndex(collection, startIndex)) {
            return null;
        }
        if (key == null) {
            return null;
        }

        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            for (int i = startIndex, size = list.size(); i < size; i++) {
                T t = list.get(i);
                if (t == null) {
                    continue;
                }

                if (matcher.isMatch(t, key)) {
                    return t;
                }
            }
        } else {
            int index = -1;
            for (T t : collection) {
                index++;
                if (index < startIndex) {
                    continue;
                }
                if (t == null) {
                    continue;
                }

                if (matcher.isMatch(t, key)) {
                    return t;
                }
            }
        }

        return null;
    }

    public static <T, K> List<T> findAll(Collection<T> collection, K key, Matcher2<T, K> matcher) {
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

            if (matcher.isMatch(t, key)) {
                list.add(t);
            }
        }

        return list;
    }

    /**
     *
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
     *
     */
    public static <T> int getMinSize(T[][] arrays) {
        return getSizePair(arrays).getMin();
    }

    /**
     */
    public static <T> int getMaxSize(T[][] arrays) {
        return getSizePair(arrays).getMax();
    }

    /**
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

        if (src == null || src.isEmpty()) {
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