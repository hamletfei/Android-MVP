package com.stillfly.mvplibrary.base.http;

import com.google.gson.annotations.SerializedName;

public class BaseHttpResult<T> {
    /**
     * 返回的 code ，根据 code API 返回情况
     */
    private int code;
    /**
     * message 信息
     */
    @SerializedName("msg")
    private String message;
    /**
     * 返回的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
