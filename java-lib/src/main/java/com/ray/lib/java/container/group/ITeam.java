package com.ray.lib.java.container.group;

import java.util.List;

/**
     */
    public interface ITeam<Tag, Item> {
        Tag getTag();

        void addItem(Item item);

        List<Item> getItems();
    }