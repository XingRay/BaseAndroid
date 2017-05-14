package com.ray.lib.java.collection.group;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by leixing
 * on 2016-10-25.
 * Email : leixing@hecom.cn
 */

public abstract class GroupData<Tag, Item, Data extends GroupData.IData<Tag, Item>, Team extends GroupData.ITeam<Tag, Item>> {
    /**
     * 是否开启自动排序
     * true - 在外部获取数据前会对数据进行排序
     * false - 外部获取的数据没有进行排序
     */
    private boolean isAutoSort = false;

    /**
     * 是否已经经过排序
     */
    private boolean isSorted = true;

    /**
     * 数据组
     */
    private List<Team> teams;

    /**
     * 组排序的比较器
     */
    private Comparator<Team> teamComparator;

    /**
     * 组内元素排序的比较器
     */
    private Comparator<Item> itemComparator;

    public GroupData() {
        this.teams = new ArrayList<>();
        this.teamComparator = getTeamComparator();
        this.itemComparator = getItemComparator();
    }

    /**
     * 设置是否自动进行排序
     *
     * @param autoSort
     */
    public GroupData<Tag, Item, Data, Team> setAutoSort(boolean autoSort) {
        isAutoSort = autoSort;
        return this;
    }

    /**
     * 设置组的比较器，用于对组进行排序,如需要对组进行排序则子类要复写此方法
     */
    protected Comparator<Team> getTeamComparator() {
        return null;
    }

    /**
     * 设置条目额比较器，用于组内条目的排序,如需要对组内条目进行排序则子类要复写此方法
     */
    protected Comparator<Item> getItemComparator() {
        return null;
    }

    /**
     * 根据tag将item加入到分组数据中
     *
     * @param tag
     * @param item
     */
    public GroupData<Tag, Item, Data, Team> addItem(Item item, Tag tag) {
        if (tag == null || item == null) {
            return this;
        }

        if (this.teams == null) {
            teams = new ArrayList<>();
        }

        Team team = findTeam(tag);
        if (team == null) {
            team = newTeam(tag);
            teams.add(team);
        }
        team.addItem(item);
        //添加数据后，标记为未排序
        isSorted = false;
        return this;
    }

    /**
     * 添加一个原始数据
     *
     * @param data 原始数据
     */
    public GroupData<Tag, Item, Data, Team> add(Data data) {
        if (data == null) {
            return this;
        }

        addItem(data.getItem(), data.getTag());
        return this;
    }

    /**
     * 加入一个原始数据的列表
     *
     * @param list 原始数据列表
     */
    public GroupData<Tag, Item, Data, Team> addAll(List<Data> list) {
        if (list == null || list.size() == 0) {
            return this;
        }

        for (Data data : list) {
            if (data == null) {
                continue;
            }

            add(data);
        }

        return this;
    }

    /**
     * 添加一个组
     *
     * @param team
     */
    public GroupData<Tag, Item, Data, Team> addTeam(Team team) {
        if (team == null) {
            return this;
        }

        Tag tag = team.getTag();
        if (tag == null) {
            return this;
        }

        List<Item> items = team.getItems();
        if (items == null || items.size() == 0) {
            return this;
        }

        for (Item item : items) {
            if (item == null) {
                continue;
            }

            addItem(item, tag);
        }

        return this;
    }

    /**
     * 加入
     *
     * @param teams
     */
    public GroupData<Tag, Item, Data, Team> addTeams(List<Team> teams) {
        if (teams == null || teams.size() == 0) {
            return this;
        }

        for (Team team : teams) {
            if (team == null) {
                continue;
            }

            addTeam(team);
        }

        return this;
    }


    public GroupData<Tag, Item, Data, Team> addGroup(GroupData<Tag, Item, Data, Team> data) {
        if (data == null) {
            return this;
        }

        List<Team> teams = data.getTeams();
        if (teams == null) {
            return this;
        }

        addTeams(teams);
        return this;
    }

    /**
     * 根据Tag找到指定的分组
     */
    public Team findTeam(Tag tag) {
        if (teams == null || teams.size() == 0) {
            return null;
        }

        for (Team data : teams) {
            if (data == null) {
                continue;
            }

            Tag groupTag = data.getTag();
            if (groupTag == null) {
                continue;
            }

            if (groupTag.equals(tag)) {
                return data;
            }
        }

        return null;
    }

    /**
     * 根据位置找到指定的分组
     */
    public Team findTeam(int index) {
        if (index < 0) {
            return null;
        }
        if (teams == null || teams.size() == 0) {
            return null;
        }

        if (index >= teams.size()) {
            return null;
        }

        return teams.get(index);
    }

    /**
     * 根据tag和item在组中位置来查找item
     *
     * @param tag
     * @param index
     * @return
     */
    public Item findItem(Tag tag, int index) {
        if (tag == null) {
            return null;
        }

        if (index < 0) {
            return null;
        }

        Team team = findTeam(tag);
        if (team == null) {
            return null;
        }

        List<Item> items = team.getItems();
        if (items == null) {
            return null;
        }

        if (index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

    /**
     * 根据team和item的位置来查找item
     *
     * @param teamIndex
     * @param itemIndex
     * @return
     */
    public Item findItem(int teamIndex, int itemIndex) {
        if (teamIndex < 0) {
            return null;
        }

        if (itemIndex < 0) {
            return null;
        }

        Team team = findTeam(teamIndex);
        if (team == null) {
            return null;
        }

        List<Item> items = team.getItems();
        if (items == null) {
            return null;
        }

        if (itemIndex >= items.size()) {
            return null;
        }

        return items.get(itemIndex);
    }

    /**
     * 对分组和组内元素进行排序
     * 注：此处不受标记位影响，也不设置标记位，keep simple
     */
    public GroupData<Tag, Item, Data, Team> sort() {
        if (teams == null || teams.size() <= 0) {
            return null;
        }

        if (this.teamComparator != null) {
            Collections.sort(teams, teamComparator);
        }

        if (this.itemComparator != null) {
            for (ITeam<Tag, Item> team : teams) {
                if (team == null) {
                    continue;
                }

                List<Item> items = team.getItems();
                if (items == null || items.size() == 0) {
                    continue;
                }

                Collections.sort(items, itemComparator);
            }
        }

        return this;
    }

    /**
     * 获取所有的组的列表
     *
     * @return
     */
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();

        trySort();

        if (this.teams == null) {
            return Collections.unmodifiableList(teams);
        }

        for (Team team : this.teams) {
            if (team == null) {
                continue;
            }

            teams.add(team);
        }

        return Collections.unmodifiableList(teams);
    }

    /**
     * 获取指定组的条目的列表
     *
     * @param teamIndex
     * @return 指定组的条目的列表
     */
    public List<Item> getItems(int teamIndex) {
        List<Item> items = new ArrayList<Item>();

        Team team = findTeam(teamIndex);
        if (team == null) {
            return Collections.unmodifiableList(items);
        }

        trySort();

        List<Item> items1 = team.getItems();
        if (items1 == null) {
            return Collections.unmodifiableList(items);
        }

        for (Item item : items1) {
            if (item == null) {
                continue;
            }

            items.add(item);
        }

        return Collections.unmodifiableList(items);
    }

    /**
     * 获取全部的条目数据
     *
     * @return
     */
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        trySort();

        if (teams == null) {
            return Collections.unmodifiableList(items);
        }

        for (Team team : teams) {
            if (team == null) {
                continue;
            }

            List<Item> items1 = team.getItems();
            if (items1 == null) {
                continue;
            }

            for (Item item : items1) {
                if (item == null) {
                    continue;
                }

                items.add(item);
            }
        }

        return Collections.unmodifiableList(items);
    }

    /**
     * 根据是否设置自动排序和是否已经排序来尝试进行排序
     */
    private void trySort() {
        if (isAutoSort && !isSorted) {
            sort();
            isSorted = true;
        }
    }

    /**
     * 由子类实现如何构造一个新的Team
     *
     * @param tag
     * @return
     */
    public abstract Team newTeam(Tag tag);

    /**
     * 可分租的原始数据的接口
     *
     * @param <Tag>
     * @param <Item>
     */

    public interface IData<Tag, Item> {
        Tag getTag();

        Item getItem();
    }

    /**
     * 可分租数据的组的接口
     *
     * @param <Tag>
     * @param <Item>
     */
    public interface ITeam<Tag, Item> {
        Tag getTag();

        void addItem(Item item);

        List<Item> getItems();
    }

    public static class Team<Tag, Item> implements GroupData.ITeam<Tag, Item> {
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

}