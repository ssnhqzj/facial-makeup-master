package com.qzj.facial.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chinamobile.streetlight.R;
import com.qzj.facial.common.http.retrofit.adapter.rxjava.HttpException;
import com.qzj.facial.common.utils.DensityUtils;
import com.qzj.facial.widget.EmptyLayout;

import java.net.ConnectException;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * base MVP fragment
 */
public abstract class BaseMvpFragment<V extends IBaseView,T extends BasePresenter<V>> extends BaseFragment
        implements PtrHandler {

    private static final String TAG = "BaseMvpActivity";

    public T presenter;
    protected PtrFrameLayout refreshLayout;
    protected EmptyLayout emptyLayout;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        if (presenter != null){
            presenter.attach((V)this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (presenter != null){
            presenter.disAttach();
        }
        super.onDestroy();
    }

    /**
     * 初始化EmptyLayout
     */
    protected void initEmptyLayout(View view) {
        if (view != null){
            emptyLayout = (EmptyLayout) view.findViewById(R.id.empty_layout);
        }
    }

    /**
     * 刷新空视图状态
     * @param throwable 异常
     */
    public void refreshEmptyLayout(Throwable throwable, boolean isShade) {
        if (throwable != null) {
            if (throwable instanceof ConnectException) {
                ConnectException e = (ConnectException) throwable;
                if (e.getMessage() != null) {
                    Log.e(TAG,"==========Exception============" + e.getMessage());
                    // 连接失败
                    if (e.getMessage().contains("Failed to connect to")) {
                        if (emptyLayout != null) {
                            emptyLayout.setEmptyState(EmptyLayout.NO_NETWORK, isShade);
                        }
                    }
                }
            }else if(throwable instanceof HttpException) {
                HttpException e = (HttpException) throwable;
                Log.e(TAG,"==========Exception============" + e.code());
                if (emptyLayout != null) {
                    emptyLayout.setEmptyState(EmptyLayout.ERROR, isShade);
                }
            }
        }
    }

    /**
     * 刷新空视图状态
     * 设置成无数据状态
     * @param isShade 是否覆盖
     */
    public void refreshEmptyLayoutToNoData(boolean isShade) {
        if (emptyLayout != null) {
            emptyLayout.setEmptyState(EmptyLayout.NO_DATA, isShade);
        }
    }

    /**
     * 刷新空视图状态
     * @param emptyStateId 状态
     */
    public void refreshEmptyLayout(int emptyStateId){
        if (emptyLayout != null) {
            emptyLayout.setEmptyState(emptyStateId,true);
        }
    }

    /**
     * 关闭空视图
     */
    public void closeEmptyLayout(){
        if (emptyLayout != null){
            emptyLayout.close();
        }
    }

    // 初始化presenter
    public abstract T initPresenter();

}
