package com.ray.http.param;


/**
 * 网络请求参数构造器的构建者接口
 * Created by leixing on 2016/6/14.
 */
public interface IParamBuilder<Param> {

    /**
     * 传入对象作为请求的参数
     *
     * @param param
     * @return
     */
    IParamBuilder setParam(Param param);

    /**
     * 构建出 RequestParams
     *
     * @return
     */
    //RequestParams build();

}
