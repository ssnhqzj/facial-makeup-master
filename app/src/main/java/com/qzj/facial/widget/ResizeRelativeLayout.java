package com.qzj.facial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by qzj on 2016/6/3.
 * 监听布局变化的RelativeLayout
 */
public class ResizeRelativeLayout extends RelativeLayout {

    public ResizeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnResizeListener mListener;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mListener != null) {
            mListener.OnResize(w, h, oldw, oldh);
        }
    }

    public void setOnResizeListener(OnResizeListener l) {
        mListener = l;
    }

    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }
}
