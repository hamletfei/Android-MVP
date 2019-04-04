package com.stillfly.mvplibrary.base.mvp;

public interface IView {
    void showLoading(String tip);
    void hideLoading();
    void showErrorTip(String tip);
}
