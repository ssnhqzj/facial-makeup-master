package com.qzj.facial.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chinamobile.streetlight.R;
import com.qzj.facial.common.utils.DisplayUtils;

/**
 * base dialog
 */
public abstract class BaseDialog extends Dialog {

    protected Context context;

    public BaseDialog(Context context) {
        this(context, R.style.DialogTheme);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        getDialogView();

        // 设置内容
        setContentView(getDialogView());
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = getDialogWidth();
        params.height = getDialogHeight();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    protected int getDialogWidth() {
        return DisplayUtils.getScreenResolution(context).x * 4/5;
    }

    protected int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected abstract View getDialogView();

}
