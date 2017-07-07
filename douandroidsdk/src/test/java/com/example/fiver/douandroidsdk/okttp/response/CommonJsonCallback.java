package com.example.fiver.douandroidsdk.okttp.response;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douandroidsdk.okttp.response
 *  文件名:  CommonJsonCallback
 *  创建者:  YYC
 *  创建时间:  17/7/7 上午8:02
 *  描述:  处理Json的回调响应
 */

import android.os.Handler;
import android.os.Looper;

import com.example.fiver.douandroidsdk.okttp.exception.OkHttpException;
import com.example.fiver.douandroidsdk.okttp.listetner.DisposeDataHandle;
import com.example.fiver.douandroidsdk.okttp.listetner.DisposeDataListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {
    //服务器返回字段的对应关系
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    //自定义异常类型
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    //将数据转发到UI线程
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    //处理服务器返回的数据
    private void handleResponse(Object responseObj) {
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if (result.has(RESULT_CODE)) {
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        Object obj = new Gson().fromJson(result.toString(), mClass);
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
