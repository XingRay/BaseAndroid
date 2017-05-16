package com.ray.http.request;

import android.content.Context;

import com.hecom.application.SOSApplication;
import com.hecom.base.http.listener.NetRequestListener;
import com.hecom.base.http.param.DefaultIParamBuilder;
import com.hecom.base.http.param.EntityBuilder;
import com.hecom.base.http.param.EntityBuilderInterface;
import com.hecom.base.http.param.IParamBuilder;
import com.hecom.config.Config;
import com.hecom.system.SystemMaintenanceDisplayer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * 默认的网络管理器
 * Created by leixing on 2016/6/13.
 */
public abstract class DefaultNetRequest<Param, Entity> implements INetRequest<Param, Entity> {
    private String mUrl;
    private Context mContext;
    private Param mParam;
    private IParamBuilder<Param> mParamBuilder;
    private EntityBuilderInterface<Param> mEntityBuilder;
    private NetRequestListener<Entity> mNetRequestListener;
    private int mRequestType;
    private RequestHandle mHandle;
    private int mCommunicationType;
    private AsyncHttpClient mHttpClient;

    public DefaultNetRequest() {
        //使用默认的参数构造器
        mParamBuilder = new DefaultIParamBuilder<>();
        mEntityBuilder = new EntityBuilder<>();

        mContext = SOSApplication.getAppContext();

        //默认的请求类型为 get 请求
        mRequestType = REQUEST_TYPE_GET;
    }

    /**
     * 将接口参数封装成对象传入
     *
     * @param param
     * @return
     */
    @Override
    public INetRequest<Param, Entity> setParam(Param param) {
        this.mParam = param;
        return this;
    }

    public RequestParams getParam() {
        return mParamBuilder.build();
    }

    /**
     * 设置参数构造器, 由于服务器接口传入的参数不能统一, 因此需要根据具体的情况来构造传入的参数
     *
     * @param paramBuilder
     * @return
     */
    @Override
    public INetRequest<Param, Entity> setParamBuilder(IParamBuilder<Param> paramBuilder) {
        this.mParamBuilder = paramBuilder;
        return this;
    }

    /**
     * 设置网络请求的url
     *
     * @param url
     * @return
     */
    @Override
    public DefaultNetRequest<Param, Entity> setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    /**
     * 设置网络请求的类型
     *
     * @param requestType
     * @return
     */
    @Override
    public INetRequest<Param, Entity> setRequestType(@RequestType int requestType) {
        this.mRequestType = requestType;
        return this;
    }

    @Override
    public INetRequest<Param, Entity> setCommunicationType(@CommunicationType int communicationType) {
        this.mCommunicationType = communicationType;
        return this;
    }

    /**
     * 设置网络请求的监听器
     *
     * @param listener
     * @return
     */
    @Override
    public INetRequest<Param, Entity> setNetRequestListener(NetRequestListener<Entity> listener) {
        this.mNetRequestListener = listener;
        return this;
    }

    /**
     * 根据消息消息通信的方式来构建HttpClient
     * 0-异步请求
     * 1-同步请求
     *
     * @return
     */
    private AsyncHttpClient buildHttpClient(int communicationType) {
        AsyncHttpClient httpClient = null;
        //根据消息通信的类型选择同步请求还是异步请求
        switch (communicationType) {
            case COMMUNICATION_TYPE_ASYNC:
                //异步请求
                httpClient = SOSApplication.getInstance().getHttpClient();
                break;
            case COMMUNICATION_TYPE_SYNC:
                //同步请求
                httpClient = SOSApplication.getInstance().getSyncHttpClient();
                break;
            default:
                //默认为异步
                httpClient = SOSApplication.getInstance().getHttpClient();
        }

        return httpClient;
    }

    /**
     * 执行网络请求, 返回RequestHandle, 用于操作网络请求(如 取消请求)
     *
     * @return
     */
    @Override
    public RequestHandle execute() {
        mParamBuilder.setParam(mParam);
        mEntityBuilder.setParam(mParam);
        mHttpClient = buildHttpClient(mCommunicationType);

        switch (mRequestType) {
            case REQUEST_TYPE_GET:
                mHandle = doGet();
                break;
            case REQUEST_TYPE_POST:
                mHandle = doPost();
                break;
            default:
        }
        return mHandle;
    }

    /**
     * 以 post 的形式发出请求
     *
     * @return
     */
    private RequestHandle doPost() {
        return mHttpClient.post(mContext, mUrl, mEntityBuilder.build(), "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DefaultNetRequest.this.onFailure(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                DefaultNetRequest.this.onSuccess(statusCode, responseString);
            }
        });
    }

    /**
     * 以 get 的形式发出请求
     *
     * @return
     */
    private RequestHandle doGet() {
        //红圈通中只使用post,不使用get
        return mHttpClient.post(mContext, mUrl, mParamBuilder.build(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DefaultNetRequest.this.onFailure(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                DefaultNetRequest.this.onSuccess(statusCode, responseString);
            }
        });
    }

    /**
     * 网络请求成功时的回调
     *
     * @param statusCode
     * @param responseString
     */
    protected void onSuccess(final int statusCode, final String responseString) {
        if (mNetRequestListener != null) {
            Entity entity = parseRawJsonData(responseString);
            mNetRequestListener.onSuccess(statusCode, entity);
        }
    }

    /**
     * 网络请求失败时的回调
     *
     * @param statusCode
     * @param responseString
     */
    protected void onFailure(int statusCode, String responseString) {
        handleSystemMaintenance(statusCode);
        if (mNetRequestListener != null) {
            mNetRequestListener.onFailure(statusCode, responseString);
        }
    }

    /**
     * 处理系统维护
     *
     * @param statusCode
     */
    private void handleSystemMaintenance(int statusCode) {
        if (statusCode == Config.RESPONSE_SYSTEM_SOFTWARE_MAINTENANCE) {
            SystemMaintenanceDisplayer.showFullScreenView();
        }
    }

    /**
     * 取消网络请求
     */
    @Override
    public void cancel() {
        if (mHandle != null && !mHandle.isCancelled()) {
            mHandle.cancel(true);
        }
    }

    /**
     * 将服务器返回的json对象转换成接口对应的返回数据对象
     *
     * @param responseString
     * @return
     */
    @Override
    public abstract Entity parseRawJsonData(String responseString);

    /**
     * 发起网络请求
     *
     * @param param    参数对象
     * @param listener 监听回调
     * @return
     */
    public abstract void launch(Param param, NetRequestListener<Entity> listener);
}
