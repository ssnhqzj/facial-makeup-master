package com.qzj.facial.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 *
 * Created by qzj on 2016/3/14.
 */
public class DeviceUtil {

    /**
     * 隐藏软键盘
     */
    public static void hideSoftKeyBoard(Activity context,View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    @SuppressWarnings("unused")
    public static void showSoftKeyBoard(Activity context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

}
