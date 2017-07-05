package com.example.fiver.douvedio.view.fragment.home;
/*
 *  项目名:  DouVedio
 *  包名:  com.example.fiver.douvedio.view.fragment
 *  文件名:  MessageFragment
 *  创建者:  YYC
 *  创建时间:  17/7/5 下午9:23
 *  描述:  TODO
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiver.douvedio.R;
import com.example.fiver.douvedio.view.fragment.BaseFragment;

public class MessageFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_layout,container,false);
        return view;
    }

}
