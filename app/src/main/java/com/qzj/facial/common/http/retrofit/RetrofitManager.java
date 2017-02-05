package com.qzj.facial.common.http.retrofit;

import com.chinamobile.streetlight.BuildConfig;
import com.qzj.facial.common.http.retrofit.adapter.rxjava.RxJavaCallAdapterFactory;
import com.qzj.facial.common.http.retrofit.okhttp.intercepter.HeaderInterceptor;
import com.qzj.facial.common.http.retrofit.okhttp.intercepter.HttpsHeaderInterceptor;
import com.qzj.facial.common.http.retrofit.okhttp.intercepter.LoggingInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Retrofit管理器
 */
public class RetrofitManager {

    private static final String BASE_URL = BuildConfig.HOST;
    public static Retrofit retrofit = null;
    public static Retrofit httpsRetrofit = null;

    /**
     * 初始化Retrofit
     */
    public static void init() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new HeaderInterceptor())
                            .addNetworkInterceptor(new LoggingInterceptor())
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .build();
                }
            }
        }
    }

    /**
     * 初始化https Retrofit
     */
    public static void initHttps() {
        if (httpsRetrofit == null) {
            synchronized (RetrofitManager.class) {
                if (httpsRetrofit == null) {

                    // Create a trust manager that does not validate certificate chains
                    final TrustManager[] trustAllCerts = new TrustManager[] {
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                    return new java.security.cert.X509Certificate[]{};
                                }
                            }
                    };

                    try {
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                        // Create an ssl socket factory with our all-trusting manager
                        final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .addInterceptor(new HttpsHeaderInterceptor())
                                .addNetworkInterceptor(new LoggingInterceptor())
                                .readTimeout(30, TimeUnit.SECONDS)
                                .sslSocketFactory(sslSocketFactory)
                                .hostnameVerifier(new HostnameVerifier() {
                                    @Override
                                    public boolean verify(String hostname, SSLSession session) {
                                        return true;
                                    }
                                })
                                .build();

                        httpsRetrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .client(okHttpClient)
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .addConverterFactory(FastJsonConverterFactory.create())
                                .build();
                    } catch (NoSuchAlgorithmException |KeyManagementException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取Api调用接口
     */
    public static <T> T getApiService(Class<T> clazz) {
        if (retrofit == null) {
            init();
        }

        return  retrofit.create(clazz);
    }

    /**
     * 获取https Api调用接口
     */
    public static <T> T getHttpsApiService(Class<T> clazz) {
        if (httpsRetrofit == null) {
            initHttps();
        }

        return  httpsRetrofit.create(clazz);
    }

}
