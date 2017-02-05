package com.qzj.facial.common.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * PopupWindowUtil
 * Created by qzj on 2016/6/17.
 */
@SuppressWarnings("unused")
public class PopupWindowUtil {

    private Context context;
    private PopupWindow popupWindow;
    private View contentView;

    private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;

    // 位置偏移量
    private int offsetX = 0;
    private int offsetY = 0;
    // 对齐方式
    private int gravity;
    // 背景
    private int bgResId;

    public PopupWindowUtil(Context context){
        this.context = context;
        popupWindow = new PopupWindow();
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public PopupWindowUtil setContentView(View view){
        this.contentView = view;
        return this;
    }

    public PopupWindowUtil setSize(int width, int height){
        this.width = width;
        this.height = height;
        return this;
    }

    public PopupWindowUtil setOffsetXY(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    public PopupWindowUtil setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public PopupWindowUtil setBackgroundResId(int bgResId) {
        this.bgResId = bgResId;
        return this;
    }

    private void build(){
        if (contentView == null) return;
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        popupWindow.setBackgroundDrawable(ResourcesCompat.getDrawable(context.getResources(),bgResId, null));
    }

    public void open(View parent) {
        build();
        popupWindow.showAtLocation(parent, Gravity.CENTER, offsetX, offsetY);
    }

    public void openAsDropDown(View anchor){
        build();
        if (Build.VERSION.SDK_INT >= 19){
            popupWindow.showAsDropDown(anchor,offsetX,offsetY,gravity);
        }else{
            popupWindow.showAsDropDown(anchor,offsetX,offsetY);
        }
    }

    public void close(){
        if (popupWindow != null){
            popupWindow.dismiss();
        }
    }

}
