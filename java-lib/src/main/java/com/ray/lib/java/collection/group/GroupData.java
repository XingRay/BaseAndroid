package com.ray.lib.java.collection.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-05-09
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class GroupData<Tag, Item, Data extends IGroupableData<Tag, Item>, Team extends IGroupedTeam<Tag, Item>> {
    private List<Team> teams;

    public GroupData() {
        this.teams = new ArrayList<>();
    }

    public GroupData(List<Team> teams) {
        this.teams = teams;
    }

    /**
     */
    public void add(Item item, Tag tag) {
        if (tag == null || item == null) {
            return;
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
    }

    /**
     *
     */
    public void addData(Data data) {
        if (data == null) {
            return;
        }

        add(data.getItem(), data.getTag());
    }

    /**
     *
     */
    public void addData(List<Data> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        for (Data data : list) {
            if (data == null) {
                continue;
            }

            addData(data);
        }
    }

    /**
     */
    public void addTeam(Team team) {
        if (team == null) {
            return;
        }

        Tag tag = team.getTag();
        if (tag == null) {
            return;
        }

        List<Item> items = team.getItems();
        if (items == null || items.size() == 0) {
            return;
        }

        for (Item item : items) {
            if (item == null) {
                continue;
            }

            add(item, tag);
        }
    }

    /**
     */
    public void addAll(List<Team> teams) {
        if (teams == null || teams.size() == 0) {
            return;
        }

        for (Team team : teams) {
            if (team == null) {
                continue;
            }

            addTeam(team);
        }
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
    public void sort(Comparator<Team> teamComparator, Comparator<Item> itemComparator) {
        if (teams == null) {
            return;
        }

        if (teamComparator != null) {
            Collections.sort(teams, teamComparator);
        }

        if (itemComparator != null) {
            for (IGroupedTeam<Tag, Item> team : teams) {
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
    }

    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    public abstract Team newTeam(Tag tag);
}