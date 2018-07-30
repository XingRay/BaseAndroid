package com.ray.lib.java.container.group.test;


import com.ray.lib.java.container.group.GroupData;

/**
 * Created by leixi on 2016/11/5.
 * leixing1012@qq.com
 */

public class OrgStudent implements GroupData.IData<StudentTag, Student> {
    private String team;
    private String name;
    private int age;

    public OrgStudent(String team, String name, int age) {
        this.team = team;
        this.name = name;
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "OrgStudent{" +
                "team='" + team + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public StudentTag getTag() {
        return new StudentTag(team);
    }

    @Override
    public Student getItem() {
        return new Student(name, age);
    }
}
