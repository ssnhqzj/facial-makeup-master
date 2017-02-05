package com.qzj.facial.base;

/**
 * base presenter
 */
public abstract class BasePresenter<T extends IBaseView> {

    public T mvpView;

    void attach(T mView) {
        this.mvpView = mView;
    }

    void disAttach(){
        this.mvpView = null;
    }

}
