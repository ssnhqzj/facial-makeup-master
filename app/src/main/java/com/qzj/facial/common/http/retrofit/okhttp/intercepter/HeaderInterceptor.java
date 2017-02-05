package com.qzj.facial.common.http.retrofit.okhttp.intercepter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.qzj.facial.api.http.HttpUrls;
import com.qzj.facial.common.AppManager;
import com.qzj.facial.common.MyApplication;
import com.qzj.facial.common.utils.ToastUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 为每个请求添加固定的Header
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request);
    }

}
