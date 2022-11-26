package com.xiaohuowa.lh138.base;


import androidx.multidex.MultiDexApplication;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import cn.bmob.v3.Bmob;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // Bmob默认初始化
        Bmob.initialize(this, "a3fcbc91e7ef946e138aa61c462aa193");
        // 百度地图初始化
        SDKInitializer.setAgreePrivacy(getApplicationContext(), true);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        LocationClient.setAgreePrivacy(true);
    }
}
