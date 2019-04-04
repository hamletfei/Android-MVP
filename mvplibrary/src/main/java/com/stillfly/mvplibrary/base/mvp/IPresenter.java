package com.stillfly.mvplibrary.base.mvp;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public interface IPresenter<V extends IView> {


    /**
     * bind view
     * @param view
     */
    void attachView(V view);

    /**
     * unbind view
     */
    void detachView();

    /**
     * 将网络请求的每一个disposable添加进入CompositeDisposable，在退出时候一并注销
     * @param observable
     * @param observer
     * @param <T>
     */
    <T> void addSubscribe(Observable<T> observable, DisposableObserver<T> observer);

    /**
     * 将网络请求的每一个disposable添加进入CompoØsiteDisposable，在退出时候一并注销
     * @param observer
     * @param <T>
     */
    <T> void addSubscribe(DisposableObserver<T> observer);
    /**
     * 注销所有请求
     */
    void clearSubscribe();

    void removeSubscribe(Disposable disposable);


}
