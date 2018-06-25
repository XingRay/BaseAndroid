package com.ray.lib.java.util.collection;

/**
 * @author : leixing
 * <p>
 * Date        : 2018/6/23
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * Description : 范围
 */
public class Range {
    private int min;
    private int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "\"Range\": {"
                + "\"min\": \"" + min
                + ", \"max\": \"" + max
                + '}';
    }


}
