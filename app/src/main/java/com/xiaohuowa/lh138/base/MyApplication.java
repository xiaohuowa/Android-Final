package com.xiaohuowa.lh138.base;


import androidx.multidex.MultiDexApplication;

import cn.bmob.v3.Bmob;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //第一：默认初始化
        Bmob.initialize(this, "a3fcbc91e7ef946e138aa61c462aa193");
    }
}
