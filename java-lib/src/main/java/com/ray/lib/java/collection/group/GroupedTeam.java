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

public abstract class GroupedTeam<Tag, Item> implements IGroupedTeam<Tag, Item> {
    private Tag tag;
    private List<Item> items;

    public GroupedTeam(Tag tag, List<Item> items) {
        this.tag = tag;
        this.items = items;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public void addItem(Item item) {
        if (items == null) {
            return;
        }

        items.add(item);
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "group=" + tag +
                ", items=" + items +
                '}';
    }
}

