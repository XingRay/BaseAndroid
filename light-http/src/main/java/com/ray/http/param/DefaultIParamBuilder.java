package com.ray.http.param;


/**
 * 默认的参数构造器的实现
 * Created by leixing on 2016/6/14.
 */
public class DefaultIParamBuilder<Param> extends ParamBuilder<Param> {
    /**
     * 子类决定怎么将Param的Entity转换成具体的请求参数
     *
     * @return
     */
//    @Override
//    public RequestParams build() {
//        RequestParams requestParams = new RequestParams();
//        String json = new Gson().toJson(mParam);
//        requestParams.put("userStr", json);
//        return requestParams;
//    }
}