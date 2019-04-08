package com.stillfly.mvplibrary.base.scheduler;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements ISchedulersProvider{

    private static volatile SchedulerProvider instance;

    public static SchedulerProvider getInstance() {
        if (instance == null) {
            synchronized (SchedulerProvider.class) {
                if (instance == null) {
                    instance = new SchedulerProvider();
                }
            }
        }
        return instance;
    }


    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    public <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.observeOn(io()).subscribeOn(ui());
            }
        };
    }

}
