package com.example.fiver.douandroidsdk.okttp.request;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.okttp.request
 *  文件名:  CommonRequest
 *  创建者:  YYC
 *  创建时间:  17/7/6 下午7:43
 *  描述:  接受请求参数，生成Request对象
 */

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

public class CommonRequest {
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder mFromBodyBuild  = new FormBody.Builder();
        if (params!=null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()){
                //将请求参数遍历添加到我们的请求构件类中去
                mFromBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody mFormBody = mFromBodyBuild.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params!=null){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()){
                //将请求参数遍历添加到我们的请求构件类中去
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();
    }
}
