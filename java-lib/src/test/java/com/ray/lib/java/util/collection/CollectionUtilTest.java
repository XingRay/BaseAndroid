package com.ray.lib.java.util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Integer> integerList = CollectionUtil.listOf(integers);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        assert CollectionUtil.equals(integerList, list);

        List<Object> objectList;

        objectList = CollectionUtil.listOf();
        ArrayList<Object> list2 = new ArrayList<>();
        assert CollectionUtil.equals(objectList, list2);

        objectList = CollectionUtil.listOf((Object) null);
        list2.add(null);
        assert CollectionUtil.equals(objectList, list2);
    }

    @Test
    public void toArray() {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        Integer[] integers = CollectionUtil.arrayOf(1, 2, 3, 4);
        assert CollectionUtil.equals(array, integers);

        Integer[] nulls = CollectionUtil.toArray(null, Integer.class);
        assert CollectionUtil.equals(nulls, new Integer[]{});
    }

    @Test
    public void concat() {
        Integer[] integers = {
                1, 2, 3, 4
        };

        Integer[] integers2 = {
                5, 6, 7, 8
        };
        Integer[] integers1 = {
                13, 14
        };

        Integer[] array = CollectionUtil.concat(Integer.class, null, integers, null, integers2, integers1);
        assert CollectionUtil.equals(CollectionUtil.arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 13, 14), array);
    }

    @Test
    public void swap() {
        Integer[] array;

        array = CollectionUtil.arrayOf(1, 2, 3, 4, 5, 6);
        CollectionUtil.swap(array, 0, 4);
        assert CollectionUtil.equals(CollectionUtil.arrayOf(5, 2, 3, 4, 1, 6), array);

        array = CollectionUtil.arrayOf(1, 2, 3, 4, 5, 6);
        CollectionUtil.swap(array, 5, 2);
        assert CollectionUtil.equals(CollectionUtil.arrayOf(1, 2, 6, 4, 5, 3), array);
    }

    @Test
    public void isOutOfIndex() {
        List<Integer> list = CollectionUtil.listOf(1, 2, 3);

        assert !CollectionUtil.isValidIndex(list, -1);
        assert CollectionUtil.isValidIndex(list, 0);
        assert CollectionUtil.isValidIndex(list, 1);
        assert CollectionUtil.isValidIndex(list, 2);
        assert !CollectionUtil.isValidIndex(list, 3);

        assert CollectionUtil.isOutOfIndex(list, -1);
        assert !CollectionUtil.isOutOfIndex(list, 0);
        assert !CollectionUtil.isOutOfIndex(list, 1);
        assert !CollectionUtil.isOutOfIndex(list, 2);
        assert CollectionUtil.isOutOfIndex(list, 3);

    }

    @Test
    public void hasElementAt() {
        List<Integer> list = CollectionUtil.listOf(1, 2, 3, 4, 5);
        assert CollectionUtil.hasElementAt(list, 1);
    }

    @Test
    public void move() {
        List<Integer> list;

        list = CollectionUtil.listOf(1, 2, 3, 4, 5);
        CollectionUtil.move(list, 1, 4);
        assert CollectionUtil.equals(CollectionUtil.listOf(1, 3, 4, 5, 2), list);

        list = CollectionUtil.listOf(1, 2, 3, 4, 5);
        CollectionUtil.move(list, 3, 0);
        assert CollectionUtil.equals(CollectionUtil.listOf(4, 1, 2, 3, 5), list);


        Integer[] array;

        array = CollectionUtil.arrayOf(1, 2, 3, 4, 5);
        CollectionUtil.move(array, 1, 4);
        assert CollectionUtil.equals(CollectionUtil.arrayOf(1, 3, 4, 5, 2), array);

        array = CollectionUtil.arrayOf(1, 2, 3, 4, 5);
        CollectionUtil.move(array, 3, 0);
        assert CollectionUtil.equals(CollectionUtil.arrayOf(4, 1, 2, 3, 5), array);
    }

    @Test
    public void equals() {
        List<Integer> list1 = CollectionUtil.listOf(1, 2, 3);
        List<Integer> list2 = CollectionUtil.listOf(1, 2, 3);
        List<Integer> list3 = CollectionUtil.listOf(1, 2, 4);

        assert CollectionUtil.equals(list1, list2);
        assert !CollectionUtil.equals(list1, list3);
    }
}