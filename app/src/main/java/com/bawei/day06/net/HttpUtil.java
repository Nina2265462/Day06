package com.bawei.day06.net;

import com.bawei.day06.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:cln
 *@Date: 2019/6/17
 *@Description:功能
 * */
public class HttpUtil {

    private final Retrofit retrofit;
    private static volatile HttpUtil httpUtil;

    /**
     * 私有构造方法
     */

    public HttpUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 双层检索机制
     *
     * @return
     */
    public static HttpUtil getInstance() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class) {
                if (httpUtil == null) {
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }

    /**
     * 创建service服务
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }
}
