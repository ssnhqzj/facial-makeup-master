package com.qzj.facial.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.streetlight.R;
import com.qzj.facial.common.toolbar.OnTbTabCheckedListener;
import com.qzj.facial.common.toolbar.TabToolbar;
import com.qzj.facial.common.toolbar.Toolbar;
import com.qzj.facial.common.utils.TextViewDrawableUtils;

/**
 * base fragment
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    protected Activity activity;

    protected TextView tbCenterView;
    protected TextView tbRightView;
    protected TextView tbLeftView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化ToolBar
     */
    protected void initToolBar(View view, Toolbar tb) {
        if (tb == null || view == null) return;
        RelativeLayout toolbar = (RelativeLayout) view.findViewById(R.id.toolbar);
        tbLeftView = (TextView) view.findViewById(R.id.toolbar_left);
        tbCenterView = (TextView) view.findViewById(R.id.toolbar_center);
        tbRightView = (TextView) view.findViewById(R.id.toolbar_right);

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


    @Override
    public void onClick(View v) {

    }
}
