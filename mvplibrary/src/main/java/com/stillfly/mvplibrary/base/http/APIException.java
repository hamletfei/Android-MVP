package com.stillfly.mvplibrary.base.http;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class APIException extends Exception{

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 1000;

    /**
     * 数据解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int SCHEME_ERROR =  1003;


    private int code;
    private String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static APIException handleAPIException(Throwable e) {
        APIException apiException;
        if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            apiException = new APIException(PARSE_ERROR, e.getMessage());
        } else if (e instanceof ConnectException) {
            apiException = new APIException(NETWORK_ERROR, e.getMessage());
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            apiException = new APIException(NETWORK_ERROR, e.getMessage());
        } else {
            apiException = new APIException(UNKNOWN_ERROR, e.getMessage());
        }

        return apiException;
    }
}
