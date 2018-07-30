package com.ray.lib.java.container.group;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by leixing
 * on 2016-10-25.
 * Email : leixing1012@qq.com
 */

public abstract class GroupData<Tag, Item, Data extends GroupData.IData<Tag, Item>, Team extends ITeam<Tag, Item>> {
    /**
     */
    private boolean isAutoSort = false;

    /**
     */
    private boolean isSorted = true;

    /**
     */
    private List<Team> teams;

    /**
     */
    private Comparator<Team> teamComparator;

    /**
     */
    private Comparator<Item> itemComparator;

    public GroupData() {
        this.teams = new ArrayList<>();
        this.teamComparator = getTeamComparator();
        this.itemComparator = getItemComparator();
    }

    /**
     *
     * @param autoSort
     */
    public GroupData<Tag, Item, Data, Team> setAutoSort(boolean autoSort) {
        isAutoSort = autoSort;
        return this;
    }

    /**
     */
    protected Comparator<Team> getTeamComparator() {
        return null;
    }

    /**
     */
    protected Comparator<Item> getItemComparator() {
        return null;
    }

    /**
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
     */
    public GroupData<Tag, Item, Data, Team> add(Data data) {
        if (data == null) {
            return this;
        }

        addItem(data.getItem(), data.getTag());
        return this;
    }

    /**
     *
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
     */
    private void trySort() {
        if (isAutoSort && !isSorted) {
            sort();
            isSorted = true;
        }
    }

    /**
     */
    public abstract Team newTeam(Tag tag);

    /**
     */

    public interface IData<Tag, Item> {
        Tag getTag();

        Item getItem();
    }
}