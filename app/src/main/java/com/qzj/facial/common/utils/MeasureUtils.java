package com.qzj.facial.common.utils;

import android.graphics.Point;
import android.view.View;

/**
 * Created by qzj.
 * Date: 2017/1/12
 */
public class MeasureUtils {

    /**
     * 测量view宽高
     */
    public static Point measureView(View view){
        if (view == null) return new Point(0,0);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return new Point(view.getMeasuredWidth(),view.getMeasuredHeight());
    }

}
