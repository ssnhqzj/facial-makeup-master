package com.qzj.facial.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.streetlight.R;
import com.qzj.facial.common.AppManager;
import com.qzj.facial.common.SystemBarTintManager;
import com.qzj.facial.common.toolbar.OnTbTabCheckedListener;
import com.qzj.facial.common.toolbar.TabToolbar;
import com.qzj.facial.common.toolbar.Toolbar;
import com.qzj.facial.common.utils.TextViewDrawableUtils;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView tbCenterView;
    protected TextView tbRightView;
    protected TextView tbLeftView;

    // 是否给状态栏着色
    private boolean isStatusBarTint = true;
    private boolean isNavigationTint = false;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        tintBarColor();
    }

    /**
     * 初始化ToolBar
     */
    protected void initToolBar(Toolbar tb) {
        if (tb == null) return;
        RelativeLayout toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        tbLeftView = (TextView) findViewById(R.id.toolbar_left);
        tbCenterView = (TextView) findViewById(R.id.toolbar_center);
        tbRightView = (TextView) findViewById(R.id.toolbar_right);

        if (toolbar == null) return;

        if (tb.getBg() != 0) {
            toolbar.setBackgroundResource(tb.getBg());
        }

        if (tb.getLeftResId() != 0) {
            TextViewDrawableUtils.setDrawable(tbLeftView, ResourcesCompat.getDrawable(getResources(),tb.getLeftResId(),null), null, null, null);
            tbLeftView.setVisibility(View.VISIBLE);
        }

        if (tb.getCenterResId() != 0) {
            TextViewDrawableUtils.setDrawable(tbCenterView, ResourcesCompat.getDrawable(getResources(),tb.getCenterResId(),null), null, null, null);
            tbCenterView.setVisibility(View.VISIBLE);
        }

        if (tb.getRightResId() != 0) {
            TextViewDrawableUtils.setDrawable(tbRightView, ResourcesCompat.getDrawable(getResources(),tb.getRightResId(),null), null, null, null);
            tbRightView.setVisibility(View.VISIBLE);
        }

        if (tb.getLeftBgResId() != 0) {
            tbLeftView.setBackgroundResource(tb.getLeftBgResId());
        }

        if (tb.getRightBgResId() != 0) {
            tbRightView.setBackgroundResource(tb.getRightBgResId());
        }

        if (tb.getLeftText() != null && !"".equals(tb.getLeftText())) {
            tbLeftView.setText(tb.getLeftText());
            tbLeftView.setVisibility(View.VISIBLE);
        }

        if (tb.getCenterText() != null && !"".equals(tb.getCenterText())) {
            tbCenterView.setText(tb.getCenterText());
            tbCenterView.setVisibility(View.VISIBLE);
        }

        if (tb.getRightText() != null && !"".equals(tb.getRightText())) {
            tbRightView.setText(tb.getRightText());
            tbRightView.setVisibility(View.VISIBLE);
        }

        tbLeftView.setOnClickListener(this);
        tbCenterView.setOnClickListener(this);
        tbRightView.setOnClickListener(this);
    }

    /**
     * 改变状态栏颜色
     */
    @TargetApi(19)
    private void tintBarColor() {
        // 支持4.4以上
        if (Build.VERSION.SDK_INT  >=  Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if (isNavigationTint) {
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }

            if (isStatusBarTint) {
                // 创建状态栏的管理实例
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                // 激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                // 激活导航栏设置
                tintManager.setNavigationBarTintEnabled(true);
                tintManager.setTintResource(R.color.colorPrimary);
            }
        }
    }

    /**
     * 请求不着色状态栏
     */
    protected void requestDonotTintStatusBar() {
        isStatusBarTint = false;
    }

    protected void requestTintNavigationBar() {
        isNavigationTint = true;
    }

    /**
     * 设置toolbar标题
     * @param text 中间标题
     */
    @SuppressWarnings("unused")
    protected void setTbCenterView(String text) {
        if (tbCenterView != null)
            tbCenterView.setText(text);
    }

    /**
     * 设置tb左边标题可见状态
     * @param visibility 可见状态
     */
    @SuppressWarnings("unused")
    protected void setTbLeftViewVisibility(int visibility) {
        if (tbLeftView != null)
            tbLeftView.setVisibility(visibility);
    }

    /**
     * 设置tb中间标题可见状态
     * @param visibility 可见状态
     */
    protected void setTbCenterViewVisibility(int visibility) {
        if (tbCenterView != null)
            tbCenterView.setVisibility(visibility);
    }

    /**
     * 设置tb右边标题可见状态
     * @param visibility 可见状态
     */
    @SuppressWarnings("unused")
    protected void setTbRightViewVisibility(int visibility) {
        if (tbRightView != null)
            tbRightView.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    public void showProgressDialog(String msg){

        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(msg);

        mProgressDialog.show();
        mProgressDialog.setCanceledOnTouchOutside(false);


    }

    public void hideProgressDialog(){

        if (mProgressDialog != null
                && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }


    }

}
