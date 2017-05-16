package com.ray.lib.java.collection.group.test;

import com.ray.lib.java.collection.group.GroupData;
import com.ray.lib.java.collection.group.Team;

import java.util.Comparator;


/**
 * Created by leixing on 2017-01-09.
 */
public class StudentGroup extends GroupData<StudentTag, Student, OrgStudent, Team<StudentTag, Student>> {
    @Override
    public Team<StudentTag, Student> newTeam(StudentTag studentTag) {
        return new Team<StudentTag, Student>(studentTag);
    }

    @Override
    protected Comparator<Team<StudentTag, Student>> getTeamComparator() {
        return new Comparator<Team<StudentTag, Student>>() {
            @Override
            public int compare(Team<StudentTag, Student> o1, Team<StudentTag, Student> o2) {
                return o1.getTag().getTag().compareTo(o2.getTag().getTag());
            }
        };
    }

    @Override
    protected Comparator<Student> getItemComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }
}
