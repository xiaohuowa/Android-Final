package com.xiaohuowa.lh138.ui.utils;

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    public static final String BASE_URL = "https://gitee.com/xiaohuowa/android-final-static/raw/master/";
//    public static final String BASE_URL = "http://192.168.31.201:8080/topline/";


    public static GetRequest get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())  // 转换成JSON
                .build();
        return retrofit.create(GetRequest.class);
    }

}
