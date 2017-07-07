package com.example.fiver.douandroidsdk.okttp.listetner;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.okttp.listetner
 *  文件名:  DisposeDataListener
 *  创建者:  YYC
 *  创建时间:  17/7/7 上午7:50
 *  描述:  自定义监听
 */

public interface DisposeDataListener {
    //请求成功回调事件处理
    public void onSuccess(Object responseObj);
    //请求失败回调事件处理
    public void onFailure(Object reasonObj);
}
