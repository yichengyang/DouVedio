package com.example.fiver.douvedio.application;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douvedio.application
 *  文件名:  MyApplication
 *  创建者:  YYC
 *  创建时间:  17/7/5 下午9:10
 *  描述:  1.程序的入口  2.进行一些常用组件的初始化  3.为整个应用的其他模块启用上下文环境
 */

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
    public static MyApplication getInstance(){
        return myApplication;
    }
}
