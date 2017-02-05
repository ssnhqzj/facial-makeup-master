package com.qzj.facial.common;

/**
 *
 * Created by Administrator on 2016/5/30.
 */

import android.app.Activity;
import android.graphics.Rect;
import android.util.Log;
import android.view.ViewTreeObserver;

/**
 * 监听软键盘高度变化
 */
@SuppressWarnings("unused")
public class SoftBroadChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private Activity activity = null;
    private OnSoftBroadStateListener listener = null;

    public SoftBroadChangeListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
        int heightDifference = screenHeight - r.bottom;
        Log.e("qzj", "----------获取到键盘高度------------: " + heightDifference);
        if (heightDifference > 0) {
            Log.e("qzj", "----------show------------: ");
            if (listener != null){
                listener.isBroadShow(true);
            }
        }else{
            Log.e("qzj", "----------hide------------: ");
            if (listener != null){
                listener.isBroadShow(false);
            }
        }
    }

    public void setListener(OnSoftBroadStateListener listener){
        this.listener = listener;
    }

    public interface OnSoftBroadStateListener {
        void isBroadShow(boolean isShow);
    }
}
