package com.rxjava.mvp.httputil;

import rx.Subscriber;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 15:12.
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> {

    /**
     * 请求标示
     */
    private int tag;

    public HttpSubscriber(int tag) {
        this.tag = tag;
    }

    @Override
    public void onCompleted() {
        _complete();
    }

    @Override
    public void onError(Throwable e) {
        _complete();
        onError(e.getMessage(), tag);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t, tag);
    }

    public abstract void onSuccess(T t, int tag);

    public abstract void onError(String msg, int tag);

    public abstract void _complete();

}
