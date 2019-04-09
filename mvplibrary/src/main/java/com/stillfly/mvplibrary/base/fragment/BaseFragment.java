package com.stillfly.mvplibrary.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stillfly.mvplibrary.base.event.IBroadcastReg;
import com.stillfly.mvplibrary.utils.CommonLoger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, IBroadcastReg {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    protected View mFragmentRootView;
    private Unbinder mUnBinder;
    protected boolean mIsPrepared;//View 是否初始化完成

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CommonLoger.state(this.getClass().getName(), "----------onCreateView----------");
        if (mFragmentRootView == null) {
            mFragmentRootView = inflater.inflate(getLayoutId(), container, false);
            mUnBinder = ButterKnife.bind(this, mFragmentRootView);
            parseArguments(getArguments());
            registerBroadcast();
            initData();
            initView();
            mIsPrepared = true;
            loadData();
        }
        //是否已经加载过mFragmentRootView，如果加载过需要先移除
        ViewGroup parent = (ViewGroup) mFragmentRootView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentRootView);
        }

        return mFragmentRootView;
    }

    /**
     * 获取Layout id
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     * @must 此方法在初始化视图之前调用
     */
    protected void initData() {

    }

    /**
     * 初始化视图
     */
    protected abstract void initView();


    /**
     * 解析传递过来的参数
     * @param bundle
     */
    protected void parseArguments(Bundle bundle) {

    }

    /**
     * 加载数据（网络或者本地）
     */
    protected abstract void loadData();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void registerBroadcast() {

    }

    @Override
    public void unRegisterBroadcast() {

    }

    /**
     * UnBind ButterKnife
     */
    private void unbindButterKnife() {
        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
        mUnBinder = null;
    }


    /**************************************************************************************************
     *
     * 打印生命周期函数
     *
     ***************************************************************************************************/


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CommonLoger.state(this.getClass().getName(), "----------onAttach----------");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonLoger.state(this.getClass().getName(), "----------onCreate----------");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonLoger.state(this.getClass().getName(), "----------onViewCreated----------");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommonLoger.state(this.getClass().getName(), "----------onActivityCreated----------");
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonLoger.state(this.getClass().getName(), "----------onStart----------");
    }

    @Override
    public void onResume() {
        super.onResume();
        CommonLoger.state(this.getClass().getName(), "----------onResume----------");
    }

    @Override
    public void onPause() {
        super.onPause();
        CommonLoger.state(this.getClass().getName(), "----------onPause----------");
    }

    @Override
    public void onStop() {
        super.onStop();
        CommonLoger.state(this.getClass().getName(), "----------onStop----------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unRegisterBroadcast();
        mIsPrepared = false;//视图已被移除
        CommonLoger.state(this.getClass().getName(), "----------onDestroyView----------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindButterKnife();
        CommonLoger.state(this.getClass().getName(), "----------onDestroy----------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CommonLoger.state(this.getClass().getName(), "----------onDetach----------");
    }
}
