package com.ray.lib.java.collection.group.test;

import com.ray.lib.java.collection.group.Team;
import com.ray.lib.java.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leixi on 2016/11/5.
 * leixing1012@qq.com
 */

public class Test {
    public static void main(String[] args) {
        List<OrgStudent> orgStudents = createData();

        StudentGroup studentGroupData = new StudentGroup();
        studentGroupData.addAll(orgStudents);


        List<Team<StudentTag, Student>> teams = studentGroupData.getTeams();
        System.out.println(teams);
    }


    private static List<OrgStudent> createData() {
        int count = 100;
        String[] orgStudentTeams = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        List<OrgStudent> orgStudents = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String team = orgStudentTeams[i % orgStudentTeams.length];
            String name = RandomUtil.getRandomString(5);
            int age = RandomUtil.getRandomInt(18, 30);
            OrgStudent orgStudent = new OrgStudent(team, name, age);

            orgStudents.add(orgStudent);
        }

        return orgStudents;
    }
}
