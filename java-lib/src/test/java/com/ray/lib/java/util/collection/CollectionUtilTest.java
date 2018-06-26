package com.ray.lib.java.util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author : leixing
 * @date : 2018/6/25 14:36
 * <p>
 * description : xxx
 */
public class CollectionUtilTest {

    @Test
    public void toList() {
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6
        };
        List<Integer> integerList = CollectionUtil.toList(integers);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        assert CollectionUtil.equals(integerList, list);

        List<Object> objectList;

        objectList = CollectionUtil.toList();
        ArrayList<Object> list2 = new ArrayList<>();
        assert CollectionUtil.equals(objectList, list2);

        objectList = CollectionUtil.toList((Object) null);
        list2.add(null);
        assert CollectionUtil.equals(objectList, list2);
    }

    @Test
    public void toArray() {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        Integer[] integers = CollectionUtil.toArray(1, 2, 3, 4);
        assert CollectionUtil.equals(array, integers);

        Integer[] nulls = CollectionUtil.toArray(null, Integer.class);
        assert CollectionUtil.equals(nulls, new Integer[]{});
    }

    @Test
    public void concat() {
        Integer[] integers = new Integer[]{
                1, 2, 3, 4
        };

        Integer[] integers2 = new Integer[]{
                5, 6, 7, 8
        };
        Integer[] concat = CollectionUtil.concat(Integer.class, null, integers, null, integers2, new Integer[]{13, 14});
        System.out.println(CollectionUtil.toList(concat));
    }

    @Test
    public void swap() {
        Integer[] array;

        array = CollectionUtil.toArray(1, 2, 3, 4, 5, 6);
        CollectionUtil.swap(array, 0, 4);
        System.out.println(CollectionUtil.toList(array));

        array = CollectionUtil.toArray(1, 2, 3, 4, 5, 6);
        CollectionUtil.swap(array, 5, 2);
        System.out.println(CollectionUtil.toList(array));
    }

    @Test
    public void isOutOfIndex() {
        List<Integer> list = CollectionUtil.toList(1, 2, 3);
        System.out.println(CollectionUtil.isOutOfIndex(list, 4));
        System.out.println(CollectionUtil.isOutOfIndex((Collection<Integer>) list, 4));
        System.out.println(CollectionUtil.isOutOfIndex((Iterable<Integer>) list, 4));

        System.out.println(CollectionUtil.isValidIndex(list, 4));
        System.out.println(CollectionUtil.isValidIndex((Collection<Integer>) list, 4));
        System.out.println(CollectionUtil.isValidIndex((Iterable<Integer>) list, 4));
    }

    @Test
    public void hasElementAt() {
        List<Integer> list = CollectionUtil.toList(1, 2, 3, 4, 5);
        System.out.println(CollectionUtil.hasElementAt(list, 1));
    }

    @Test
    public void move() {
        List<Integer> list;

        list = CollectionUtil.toList(1, 2, 3, 4, 5);
        CollectionUtil.move(list, 1, 4);

        list = CollectionUtil.toList(1, 2, 3, 4, 5);
        CollectionUtil.move(list, 3, 0);
        System.out.println(list);


        Integer[] array;

        array = CollectionUtil.toArray(1, 2, 3, 4, 5);
        CollectionUtil.move(array, 1, 4);
        System.out.println(CollectionUtil.toList(array));

        array = CollectionUtil.toArray(1, 2, 3, 4, 5);
        CollectionUtil.move(array, 3, 0);
        System.out.println(CollectionUtil.toList(array));
    }

    @Test
    public void equals() {
        List<Integer> list1 = CollectionUtil.toList(1, 2, 3);
        List<Integer> list2 = CollectionUtil.toList(1, 2, 3);
        List<Integer> list3 = CollectionUtil.toList(1, 2, 4);

        assert CollectionUtil.equals(list1, list2);
        assert !CollectionUtil.equals(list1, list3);

    }
}