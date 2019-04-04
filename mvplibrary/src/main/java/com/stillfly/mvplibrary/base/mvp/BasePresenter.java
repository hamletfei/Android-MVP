package com.stillfly.mvplibrary.base.mvp;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    /**
     * 绑定的 view
     * 设置弱引用，防止内存泄露
     */
    private WeakReference<V> mAttachedView;

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V view) {
        mAttachedView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mAttachedView != null) {
            mAttachedView.clear();
            mAttachedView = null;
        }
        clearSubscribe();
    }

    /**
     * 是否已经绑定View
     * @return
     */
    public boolean isViewAttached() {
        return mAttachedView != null && mAttachedView.get() != null;
    }

    /**
     * 获取绑定的 View
     * @return
     */
    public IView getAttachedView() {
        if (mAttachedView != null) {
            return mAttachedView.get();
        }
        return null;
    }

    @Override
    public <T> void addSubscribe(Observable<T> observable, DisposableObserver<T> observer) {
        addSubscribe(observer);
        observable.observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public <T> void addSubscribe(DisposableObserver<T> observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
    }

    @Override
    public void clearSubscribe() {
        if (mCompositeDisposable == null) {
            return;
        }
        mCompositeDisposable.clear();
    }

    @Override
    public void removeSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            return;
        }
        mCompositeDisposable.remove(disposable);
    }
}
