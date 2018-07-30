package com.ray.lib.java.container.group;

import java.util.ArrayList;
import java.util.List;

public class Team<Tag, Item> implements ITeam<Tag, Item> {
        private Tag tag;
        private List<Item> items;

        public Team(Tag tag, List<Item> items) {
            this.tag = tag;
            this.items = items;
        }

        public Team(Tag tag) {
            this.tag = tag;
            this.items = new ArrayList<>();
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