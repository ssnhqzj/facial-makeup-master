package com.qzj.facial.api;

import com.qzj.facial.common.http.retrofit.adapter.rxjava.HttpException;

import java.net.ConnectException;

import rx.Subscriber;

public abstract class SubscriberCallback<T> extends Subscriber<T> {

    protected abstract void response(T t);

    protected abstract void error(ApiException e);

    protected void finished(){}

    @Override
    public void onCompleted() {
        finished();
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable != null) throwable.printStackTrace();

        ApiException apiException = new ApiException();
        // 网络不可用
        if (throwable instanceof ConnectException) {
            ConnectException e = (ConnectException) throwable;
            if (e.getMessage() != null) {
                apiException.setCode(ApiException.NETWORK_NOT_AVAILABLE);
                apiException.setMessage(e.getMessage());
            }
        }
        // 非2XX网络错误
        else if(throwable instanceof HttpException) {
            HttpException e = (HttpException) throwable;
            apiException.setCode(e.code());
            apiException.setMessage(e.getMessage());
        }
        // 其他未知异常
        else {
            apiException.setCode(ApiException.NETWORK_UNKNOWN);
            if (throwable != null) apiException.setMessage(throwable.getMessage());
        }

        error(apiException);
        finished();
    }

    @Override
    public void onNext(T t) {
        response(t);
    }

}
