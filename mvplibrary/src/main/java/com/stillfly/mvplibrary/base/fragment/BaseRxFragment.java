package com.stillfly.mvplibrary.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stillfly.mvplibrary.base.mvp.IPresenter;
import com.stillfly.mvplibrary.base.mvp.IView;

public abstract class BaseRxFragment<P extends IPresenter, V extends IView> extends BaseFragment implements IView {

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetached();
        }
    }

    protected abstract P createPresenter();
}
