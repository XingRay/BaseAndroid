package com.ray.lib.java.collection.group;

import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-05-09
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface IGroupedTeam<Tag, Item> {
    Tag getTag();

    void addItem(Item item);

    List<Item> getItems();
}
