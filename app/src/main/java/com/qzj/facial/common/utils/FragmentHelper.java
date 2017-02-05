package com.qzj.facial.common.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: qzj
 * Date: 2016/8/31
 */
public class FragmentHelper {

    private AppCompatActivity activity;
    private FragmentManager fm;

    // fragment容器id
    private int containerId;
    private ArrayMap<String, Fragment> fragments;
    private String currentShowKey;

    private OnFragmentHelperListener helperListener;

    public FragmentHelper(AppCompatActivity activity, int containerId) {
        this.activity = activity;
        this.containerId = containerId;

        fm = activity.getSupportFragmentManager();
        fragments = new ArrayMap<>();
    }

    private void addFragment(String key, Fragment fragment) {
        if (fragment != null && fragments.get(key) == null) {
            fragments.put(key, fragment);
        }
    }

    public void showFragmnet(String key) {
        Fragment to = fragments.get(key);
        Fragment from = fragments.get(currentShowKey);

        // to如果为空，先添加
        if (to == null) {
            if (helperListener != null) {
                to = helperListener.onCreateFragment(key);
                addFragment(key, to);
            }else{
                return;
            }
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);

        // 当前有无显示的fragment
        if (currentShowKey != null && !"".equals(currentShowKey)) {
            if (to.isAdded()){
                ft.hide(from).show(to).commit();
            }else{
                ft.hide(from).add(containerId, to, key).commit();
            }
        }else{
            if (to.isAdded()) {
                ft.show(to).commit();
            }else{
                ft.add(containerId, to, key).commit();
            }
        }

        currentShowKey = key;
    }

    public void setHelperListener(OnFragmentHelperListener helperListener) {
        this.helperListener = helperListener;
    }

    public interface OnFragmentHelperListener {
        Fragment onCreateFragment(String key);
    }

}
