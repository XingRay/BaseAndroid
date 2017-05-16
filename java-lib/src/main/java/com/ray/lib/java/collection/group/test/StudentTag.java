package com.ray.lib.java.collection.group.test;

/**
 * Created by leixi on 2016/11/5.
 * leixing1012@qq.com
 */

public class StudentTag {
    private String tag;

    public StudentTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "StudentTag{" +
                "tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentTag that = (StudentTag) o;

        return tag.equals(that.tag);

    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}
