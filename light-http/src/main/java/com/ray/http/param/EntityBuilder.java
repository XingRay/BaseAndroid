package com.ray.http.param;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by leixing on 2016/6/15.
 */
public class EntityBuilder<Param> implements EntityBuilderInterface<Param> {
	Param param;

	@Override
	public EntityBuilderInterface<Param> setParam(Param param) {
		this.param = param;
		return this;
	}

	@Override
	public HttpEntity build() {
		String json = new Gson().toJson(param);
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return stringEntity;
	}
}
