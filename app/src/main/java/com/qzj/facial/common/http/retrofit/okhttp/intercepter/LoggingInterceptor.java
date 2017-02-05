package com.qzj.facial.common.http.retrofit.okhttp.intercepter;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp 日志拦截器
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        long t1 = System.nanoTime();
        Log.i("OkHttp",String.format("%s%nSending request %s on %s%n%s%s",
                request.method(),
                request.url(),
                chain.connection(),
                request.headers(),
                request.body()
        ));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i("OkHttp",String.format("Received response for %s in %.1fms%n%s%s",
                response.request().url(),
                (t2 - t1) / 1e6d,
                response.headers(),
                response.body()
        ));

        return response;

    }

}

