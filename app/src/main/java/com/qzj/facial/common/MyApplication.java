package com.qzj.facial.common;

import android.app.Application;
import android.content.Context;

import com.qzj.facial.common.db.SqliteStorage;
import com.qzj.facial.common.utils.PreferenceUtils;
import com.qzj.facial.common.utils.ToastUtil;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * Application
 */
public class MyApplication extends Application {

    public static Context context = null;

    // 是否清除密码，用于修改密码后清空密码框
    public  boolean isClearPwd;

    // 无光感设备街道Id列表
    public List<Integer> terminalStreetIdList;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initComponent();
        initData();
    }

    private void initContext() {
        context = getApplicationContext();
    }

    /**
     * 初始化组件
     */
    private void initComponent() {
        // 初始化SharedPreferences
        PreferenceUtils.init(this,"com_qzj_facial");

        // 初始化异常接管类
        CrashHandler.getInstance().init(getApplicationContext());

        // bugly初始化
        //CrashReport.initCrashReport(this, "900028885", false);

        // 初始化Toast
        ToastUtil.init(this);

        Hawk.init(MyApplication.context).setStorage(new SqliteStorage(MyApplication.context)).build();

        // LeakCanary初始化
//        LeakCanary.install(this);

        // BlockCanary初始化
        //BlockCanary.install(this, new BlockCanaryContext()).start();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }
}
