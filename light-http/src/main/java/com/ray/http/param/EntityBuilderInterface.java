package com.ray.http.param;

import org.apache.http.HttpEntity;

/**
 * Created by leixing on 2016/6/15.
 */
public interface EntityBuilderInterface<Param> {


	EntityBuilderInterface<Param> setParam(Param param);

	HttpEntity build();
}
