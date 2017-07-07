package com.example.fiver.douandroidsdk.okttp.listetner;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.okttp.listetner
 *  文件名:  DisposeDataHandle
 *  创建者:  YYC
 *  创建时间:  17/7/7 上午8:00
 *  描述:  TODO
 */

public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;
    public String mSource = null;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.mListener = listener;
        this.mSource = source;
    }
}
