package com.qzj.facial.base;

/**
 * base view
 */
public interface IBaseView {
    void setRefreshing(boolean isRefreshing);
    void refreshEmptyLayout(Throwable throwable,boolean isShade);
    void refreshEmptyLayoutToNoData(boolean isShade);
    void refreshEmptyLayout(int emptyStateId);
    void closeEmptyLayout();
    boolean checkHaveData();
}
