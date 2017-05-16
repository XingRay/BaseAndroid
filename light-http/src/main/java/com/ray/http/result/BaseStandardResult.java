package com.ray.http.result;

/**
 * 标准返回格式的基类
 * 标准返回格式:
 * {"data":"","result":"0","desc":"返回成功"}
 * 其中 data 由具体的子类指定
 * Created by leixing on 2016/6/15.
 */
public class BaseStandardResult<Entity> {
    protected String result;
    protected String desc;
    protected Entity data;
    protected String lastUpdateTime;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSuccess() {
        return "0".equals(result);
    }

    public Entity getData() {
        return data;
    }

    public void setData(Entity data) {
        this.data = data;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "BaseStandardResult{" +
                "result='" + result + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
