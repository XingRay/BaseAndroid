package com.ray.http.param;

/**
 * Author      : leixing
 * Date        : 2016-12-08
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public abstract class ParamBuilder<Param> implements IParamBuilder<Param> {
    protected Param mParam;

    @Override
    public IParamBuilder setParam(Param param) {
        this.mParam = param;
        return this;
    }
}
