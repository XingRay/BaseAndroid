package com.ray.lib.android.helper;

/**
 * Author      : leixing
 * Date        : 2017-05-05
 * Email       : leixing@hecom.cn
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