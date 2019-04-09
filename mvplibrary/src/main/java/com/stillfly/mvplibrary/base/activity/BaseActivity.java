package com.stillfly.mvplibrary.base.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.stillfly.mvplibrary.base.event.IBroadcastReg;
import com.stillfly.mvplibrary.utils.CommonLoger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements IBroadcastReg {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonLoger.state(this.getClass().getName(), "----------onCreateView----------");
        setTranslucentStatus(true);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        parseIntentData(getIntent());
        initData();
        initView();
    }


    /**
     * layout id
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


    protected void parseIntentData(Intent intent) {

    }

    @Override
    public void registerBroadcast() {

    }

    @Override
    public void unRegisterBroadcast() {

    }


    /**
     * 沉浸式状态栏
     * @param on
     */
    public void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams winLayoutParam = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winLayoutParam.flags |= bits;
            } else {
                winLayoutParam.flags &= ~bits;
            }
            window.setAttributes(winLayoutParam);
        }
    }


    /**
     * 添加 Fragment
     * @param containerId
     * @param fragment
     */
    protected void addFragment(@IdRes int containerId, Fragment fragment) {
        addFragment(containerId, fragment, null);
    }

    /**
     * 添加 Fragment
     * @param containerId
     * @param fragment
     * @param tag
     */
    protected void addFragment(@IdRes int containerId, Fragment fragment, String tag) {
        addFragment(containerId, fragment, tag, false);

    }

    /**
     * 添加 Fragment
     * @param containerId
     * @param fragment
     * @param tag
     * @param addToBackStack
     */
    protected void addFragment(@IdRes int containerId, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!TextUtils.isEmpty(tag)) {
            fragmentTransaction.add(containerId, fragment);
        } else {
            fragmentTransaction.add(containerId, fragment, tag);
        }

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }


    protected void replaceFragment(@IdRes int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, null);
    }

    protected void replaceFragment(@IdRes int containerId, Fragment fragment, String tag) {
        replaceFragment(containerId, fragment, tag, false);
    }

    /**
     * replace Fragment
     * @param containerId
     * @param fragment
     * @param tag
     * @param addToBackStack
     */
    protected void replaceFragment(@IdRes int containerId, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(tag)) {
            if (fragmentManager.findFragmentByTag(tag) != null) {
                fragmentManager.popBackStack(tag, 0);
            } else {
                fragmentTransaction.replace(containerId, fragment);
            }
        } else {
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.replace(containerId, fragment, tag);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    /**
     * Unbind ButterKnife
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
    protected void onStart() {
        super.onStart();
        CommonLoger.state(this.getClass().getName(), "----------onStart----------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CommonLoger.state(this.getClass().getName(), "----------onRestart----------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonLoger.state(this.getClass().getName(), "----------onResume----------");
    }


    @Override
    protected void onPause() {
        super.onPause();
        CommonLoger.state(this.getClass().getName(), "----------onPause----------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CommonLoger.state(this.getClass().getName(), "----------onStop----------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindButterKnife();
        CommonLoger.state(this.getClass().getName(), "----------onDestroy----------");
    }
}
