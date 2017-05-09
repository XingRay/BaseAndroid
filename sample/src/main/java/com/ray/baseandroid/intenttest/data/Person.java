package com.ray.baseandroid.intenttest.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class Person implements Parcelable {
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    private String name;
    private int age;
    private List<Follower> followers;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.followers = new ArrayList<Follower>();
        in.readList(this.followers, Follower.class.getClassLoader());
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

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", followers=" + followers +
                '}';
    }

    public Person addFollower(Follower follower) {
        if (followers == null) {
            followers = new ArrayList<>();
        }

        followers.add(follower);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeList(this.followers);
    }
}
