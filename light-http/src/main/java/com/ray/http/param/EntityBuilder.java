package com.ray.http.param;

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

//	@Override
//	public HttpEntity build() {
//		String json = new Gson().toJson(param);
//		StringEntity stringEntity = null;
//		try {
//			stringEntity = new StringEntity(json);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return stringEntity;
//	}
}
