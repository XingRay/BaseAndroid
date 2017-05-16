package com.ray.http.request;

import android.support.annotation.IntDef;

import com.hecom.base.http.listener.NetRequestListener;
import com.hecom.base.http.param.IParamBuilder;
import com.loopj.android.http.RequestHandle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络请求的接口
 * Created by leixing on 2016/6/14.
 */
public interface INetRequest<Param, Entity> {

    /**
     * 请求类型 get
     */
    int REQUEST_TYPE_GET = 0;
    /**
     * 请求类型 post
     */
    int REQUEST_TYPE_POST = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REQUEST_TYPE_GET, REQUEST_TYPE_POST})
    @interface RequestType {
    }

    /**
     * 消息通信机制 - 异步
     */
    int COMMUNICATION_TYPE_ASYNC = 0;
    /**
     * 消息通信机制 - 同步
     */
    int COMMUNICATION_TYPE_SYNC = 1;

    @IntDef({COMMUNICATION_TYPE_ASYNC, COMMUNICATION_TYPE_SYNC})
    @Retention(RetentionPolicy.SOURCE)
    @interface CommunicationType {
    }

    /**
     * 以键值对的形式传入网络请求参数
     *
     * @param param
     * @return
     */
    INetRequest<Param, Entity> setParam(Param param);

    /**
     * 设置参数构造器, 由于服务器接口传入的参数不能统一, 因此需要根据具体的情况来构造传入的参数
     *
     * @param builder
     * @return
     */
    INetRequest<Param, Entity> setParamBuilder(IParamBuilder<Param> builder);

    /**
     * 获取网络请求的 url
     *
     * @param url
     * @return
     */
    INetRequest<Param, Entity> setUrl(String url);

    /**
     * 设置网络请求的类型
     * 0 get
     * 1 post
     *
     * @param requestType
     * @return
     */
    INetRequest<Param, Entity> setRequestType(@RequestType int requestType);

    /**
     * 设置通信方式
     * 0 - 异步
     * 1 - 同步
     *
     * @param communicationType
     * @return
     */
    INetRequest<Param, Entity> setCommunicationType(@CommunicationType int communicationType);

    /**
     * 设置网络请求的监听器
     *
     * @param netRequestListener
     * @return
     */
    INetRequest<Param, Entity> setNetRequestListener(NetRequestListener<Entity> netRequestListener);

    /**
     * 将服务器返回的json对象转换成接口对应的返回数据对象
     *
     * @param responseString
     * @return
     */
    Entity parseRawJsonData(String responseString);

    /**
     * 执行网络请求
     *
     * @return
     */
    RequestHandle execute();

    /**
     * 发起网络请求
     *
     * @param param
     * @param listener
     * @return
     */
    void launch(Param param, NetRequestListener<Entity> listener);

    /**
     * 取消网络请求
     */
    void cancel();
}
