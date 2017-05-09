package com.ray.baseandroid.intenttest.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class Follower implements Parcelable {
    public static final Creator<Follower> CREATOR = new Creator<Follower>() {
        @Override
        public Follower createFromParcel(Parcel source) {
            return new Follower(source);
        }

        @Override
        public Follower[] newArray(int size) {
            return new Follower[size];
        }
    };
    private int age;
    private String name;

    public Follower(String name, int age) {
        this.age = age;
        this.name = name;
    }

    protected Follower(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
    }
}
