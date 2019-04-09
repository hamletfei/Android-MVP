package com.stillfly.mvplibrary.base.mvp;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public interface IPresenter<V extends IView, M extends IModel> {


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
     * detached
     */
    void onDetached();

    /**
     * 将网络请求的每一个disposable添加进入CompositeDisposable，在退出时候一并注销
     * @param disposable
     */
    void addSubscribe(Disposable disposable);
    /**
     * 注销所有请求
     */
    void clearSubscribe();

    void removeSubscribe(Disposable disposable);

    /**
     * 初始化数据
     * Data数据方法(存在数据获取或处理代码，但不存在事件监听代码)
     * @must Activity-在子类onCreate方法内初始化View(setContentView)后调用；Fragment-在子类onCreateView方法内初始化View后调用
     */
    default void initData() {

    }



}
