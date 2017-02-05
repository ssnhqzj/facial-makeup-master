package com.qzj.facial.api.http;

import com.qzj.facial.common.http.retrofit.RetrofitManager;

/**
 * 用户模块网络请求Api
 */
public class HttpApi {

    private static HttpApi api = null;
    private HttpApiService apiService = null;

    private HttpApi() {
        apiService = RetrofitManager.getApiService(HttpApiService.class);
    }

    public static HttpApi instance(){
        if (api == null){
            synchronized (HttpApi.class){
                if (api == null){
                    api = new HttpApi();
                }
            }
        }

        return api;
    }

    /**
     * 登录
     */
    /*public Observable<User> login(String id, String pwd) {
        Map<String,String> param = new HashMap<>();
        param.put("account",id);
        param.put("password",pwd);
        return apiService.login(param);
    }*/

}
