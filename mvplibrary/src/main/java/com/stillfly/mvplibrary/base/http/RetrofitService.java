package com.stillfly.mvplibrary.base.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final long CONNECT_TIME_OUT = 60L;
    private static final long READ_TIME_OUT = 60L;
    private static final long WRITE_TIME_OUT = 60L;

    private static volatile Retrofit retrofit;


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitService.class) {
                if (retrofit == null) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(getHostInterceptor())//动态修改host
                            .addInterceptor(getHeaderInterceptor())//Header
                            .addInterceptor(getLoggingInterceptor())//日志
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return retrofit;
    }

    private static Interceptor getHostInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String host = HttpUtils.getBaseUrl();
                Request originRequest = chain.request();
                HttpUrl httpUrl = originRequest.url().newBuilder().host(host).build();
                Request request = originRequest.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        };
    }


    private static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    private static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request.Builder builder = originRequest.newBuilder();
                builder.addHeader(HttpConstant.HEAD_TOKEN_NAME, HttpUtils.getHttpToken());
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 释放 retrofit，以使得 retrofit 可以重新构建
     */
    public static void releaseRetrofit() {
        synchronized (RetrofitService.class) {
            retrofit = null;
        }
    }
}
