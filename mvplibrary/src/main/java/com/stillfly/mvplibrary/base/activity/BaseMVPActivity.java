package com.stillfly.mvplibrary.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stillfly.mvplibrary.base.mvp.IPresenter;
import com.stillfly.mvplibrary.base.mvp.IView;

public abstract class BaseMVPActivity<P extends IPresenter, V extends IView> extends BaseActivity implements IView {

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetached();
        }
    }

    protected abstract P createPresenter();
}
