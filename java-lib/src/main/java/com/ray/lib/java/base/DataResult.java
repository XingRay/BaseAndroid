package com.ray.lib.java.base;

/**
 * 数据结果，一般用于io同步操作的结果
 *
 * @author : leixing
 * @date : 2018/5/14
 * Version : 0.0.1
 */
public class DataResult<T> {
    private boolean isSucceed;
    private T data;
    private int errorCode;
    private String errorMsg;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "\"DataResult\": {"
                + "\"isSucceed\": \"" + isSucceed
                + ", \"data\": \"" + data
                + ", \"errorCode\": \"" + errorCode
                + ", \"errorMsg\": \"" + errorMsg + '\"'
                + '}';
    }
}
