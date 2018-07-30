package com.ray.lib.java.base;

/**
 * 数据回调，一般用于io异步操作的回调
 *
 * @author : leixing
 * @date : 2018/5/17
 * Version : 0.0.1
 */
@SuppressWarnings("unused")
public interface DataCallback<T> {
    /**
     * 成功回调
     *
     * @param data 返回的数据
     */
    void onSuccess(T data);

    /**
     * 失败回调
     *
     * @param errorCode 错误码
     * @param msg       错误信息
     */
    void onFailure(int errorCode, String msg);
}
