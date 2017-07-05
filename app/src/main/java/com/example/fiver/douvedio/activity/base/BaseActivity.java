package com.example.fiver.douvedio.activity.base;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douvedio.activity.base
 *  文件名:  BaseActivity
 *  创建者:  YYC
 *  创建时间:  17/7/5 下午9:35
 *  描述:  为所有的Activity提供公共的行为
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
