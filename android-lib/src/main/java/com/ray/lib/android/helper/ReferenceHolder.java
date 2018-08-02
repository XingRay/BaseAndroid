package com.ray.lib.android.helper;

/**
 * @@author      : leixing
 * @@date        : 2017-05-05
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : 引用持有者
 */

public class ReferenceHolder<T> {
    /**
     * 持有的引用
     */
    private T reference;

    public ReferenceHolder() {
    }

    public ReferenceHolder(T reference) {
        this.reference = reference;
    }

    public T getReference() {
        return reference;
    }

    public void setReference(T reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ReferenceHolder{" +
                "reference=" + reference +
                '}';
    }
}
