package com.lte.admin.common.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by wangfugui on 2017/5/15.
 */
public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = 5537309731112503514L;
    /**
     * 状态 1：成功 0：失败
     */
    private int status=1;

    /**
     * 提示信息
     */
    private String info;

    private T data;

    private long Count;

    public ServiceResponse() {
        this(0, "", null);
    }

    public ServiceResponse( int status,  String info, T data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCount() {
        return Count;
    }

    public void setCount(long Count) {
        this.Count = Count;
    }

    public String objectToJson(){
        return JSON.toJSONString(this);
    }

}
