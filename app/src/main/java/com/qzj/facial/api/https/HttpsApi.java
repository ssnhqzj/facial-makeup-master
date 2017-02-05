package com.qzj.facial.api.https;

import com.qzj.facial.common.http.retrofit.RetrofitManager;

/**
 * https网络请求Api
 */
public class HttpsApi {

    private static HttpsApi api = null;
    private HttpsApiService apiService = null;

    private HttpsApi() {
        apiService = RetrofitManager.getHttpsApiService(HttpsApiService.class);
    }

    public static HttpsApi instance(){
        if (api == null){
            synchronized (HttpsApi.class){
                if (api == null){
                    api = new HttpsApi();
                }
            }
        }

        return api;
    }

    /**
     * 设置照明模式--手动策略
     */
    /*public Observable<Result> setManualModePolicy(String url, ApiManualSetting params) {
        return apiService.setManualModePolicy(url,params);
    }*/

}
