package com.ray.baseandroid.multitype;

/**
 * @author      : leixing
 * @date        : 2017-05-23
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */
public class Song {
    private String name;
    private String lyric;

    public Song(String name, String lyric) {
        this.name = name;
        this.lyric = lyric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", lyric='" + lyric + '\'' +
                '}';
    }
}