package com.qzj.facial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CheckBox;

/**
 * CheckBox不处理任何事件
 */
public class NoneCheckBox extends CheckBox {

    public NoneCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NoneCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoneCheckBox(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }
}
