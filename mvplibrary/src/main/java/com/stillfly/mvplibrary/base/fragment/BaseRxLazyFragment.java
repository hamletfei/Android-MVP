package com.stillfly.mvplibrary.base.fragment;

import com.stillfly.mvplibrary.base.mvp.IPresenter;
import com.stillfly.mvplibrary.base.mvp.IView;

public abstract class BaseRxLazyFragment<P extends IPresenter, V extends IView> extends BaseRxFragment<P, V> {

    /**
     * 是否可见
     */
    protected boolean mIsVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInVisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        loadData();
    }

    /**
     * 不可见
     */
    protected void onInVisible() {

    }

    @Override
    final protected void loadData() {
        if (mIsPrepared && mIsVisible) {
            lazyLoadData();
        }
    }

    protected abstract void lazyLoadData();
}
