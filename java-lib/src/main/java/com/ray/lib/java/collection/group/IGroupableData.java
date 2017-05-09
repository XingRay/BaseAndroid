package com.ray.lib.java.collection.group;

/**
 * Author      : leixing
 * Date        : 2017-05-09
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface IGroupableData<Tag, Item> {
    Tag getTag();

    Item getItem();
}