package com.ray.http.listener;

/**
 * 网络的回调的接口
 * Created by leixing on 2016/6/13.
 */
public interface NetRequestListener<Entity> {

	/**
	 * 网络请求取消时的回调
	 */
	void onCancel();

	/**
	 * 网络请求成功时的回调
	 *
	 * @param statusCode
	 * @param result
	 */
	void onSuccess(int statusCode, Entity result);

	/**
	 * 网络请求失败时的回调
	 *
	 * @param statusCode
	 * @param responseString
	 */
	void onFailure(int statusCode, String responseString);
}
