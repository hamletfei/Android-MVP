package com.stillfly.mvplibrary.base.http;

import android.arch.core.util.Function;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class ResponseTransform {

    /**
     * 非服务器产生的异常，比如本地无网络请求，Json数据解析错误等
     */
    private static class ErrorResumeException<T> implements Function<Throwable, ObservableSource<? extends BaseHttpResult<T>>> {

        @Override
        public ObservableSource<? extends BaseHttpResult<T>> apply(Throwable input) {
            return Observable.error(APIException.handleAPIException(input));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseHttpResult<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseHttpResult<T> result) {
            int code = result.getCode();
            String msg = result.getMessage();
            if (code == 0) {
                return Observable.just(result.getData());
            } else {
                return Observable.error(new APIException(code, msg));
            }
        }
    }

}
