package com.stillfly.mvplibrary.base.event;

/**
 * 注册广播或者类似于 EventBus 之类的总线事件
 */
public interface IBroadcastReg {

    /**
     * 注册广播（事件）
     */
    void registerBroadcast();

    /**
     * 注销广播（事件）
     */
    void unRegisterBroadcast();
}
