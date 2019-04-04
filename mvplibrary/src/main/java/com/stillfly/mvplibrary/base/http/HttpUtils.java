package com.stillfly.mvplibrary.base.http;

public class HttpUtils {

    /**
     * 获取 token
     * 可以根据需要重修改此方法
     * @return
     */
    public static String getHttpToken() {
        return "token";
    }

    /**
     * 获取 BaseUrl
     * 可以根据需要修改此方法
     * @return
     */
    public static String getBaseUrl() {
        return "";
    }
}
