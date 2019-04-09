package com.stillfly.mvplibrary.base.mvp;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V, M> {


    /**
     * 绑定的 view
     */
    private WeakReference<V> mAttachedView;

    /**
     * model
     */
    private M mModel;

    private CompositeDisposable mCompositeDisposable;

    public BasePresenter() {
        mModel = getModel();
    }

    /**
     * 绑定 view
     * 调用时机为 view 层的 onCreate()
     * 设置弱引用，防止内存泄漏
     */
    @Override
    public void attachView(V view) {
        mAttachedView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mAttachedView != null) {
            mAttachedView.clear();
            mAttachedView = null;
        }

    }

    @Override
    public void onDetached() {
        detachView();
        mModel = null;
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
        return mAttachedView.get();
    }


    @Override
    public void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
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

    protected abstract M getModel();
}
