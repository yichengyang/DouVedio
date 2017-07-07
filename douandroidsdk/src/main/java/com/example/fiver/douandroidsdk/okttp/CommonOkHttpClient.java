package com.example.fiver.douandroidsdk.okttp;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.okttp
 *  文件名:  CommonOkHttpClient
 *  创建者:  YYC
 *  创建时间:  17/7/6 下午7:37
 *  描述:  请求的发送，请求参数的配置，https支持
 */

import com.example.fiver.douandroidsdk.okttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CommonOkHttpClient {
    //超时参数
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    //为mOkHttpClient配置参数
    static{
        //创建client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //为构建者提供参数
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        //请求重定向
        okHttpBuilder.followRedirects(true);

        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpBuilder.build();

    }
    public static Call sendRequest(Request request, Callback commCallback){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallback);
        return call;
    }


}
