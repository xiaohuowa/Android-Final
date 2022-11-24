package com.xiaohuowa.lh138.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.xiaohuowa.lh138.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.createInstance("第一日",
                "启动页、引导页、闪屏页与基础框架搭建", R.drawable.p1, R.color.teal_200));
        addSlide(AppIntroFragment.createInstance("第二日",
                "LiveData+Retrofit网络请求与下拉刷新SmartRefreshLayout", R.drawable.p2, R.color.purple_200));
        addSlide(AppIntroFragment.createInstance("第三日",
                "RecyclerAdapter框架与轮播图banner", R.drawable.p3, R.color.blue1));
        addSlide(AppIntroFragment.createInstance("第四日",
                "新闻详情页AgentWeb与Python详情页", R.drawable.p4, R.color.blue2));
        addSlide(AppIntroFragment.createInstance("第五日",
                "爆炸菜单BoomMenu与统计图表MPAndroidChart", R.drawable.p5, R.color.blue3));
        addSlide(AppIntroFragment.createInstance("第六日",
                "视频列表与视频播放器GSYVideoPlayer", R.drawable.p6, R.color.blue2));
        addSlide(AppIntroFragment.createInstance("第七日",
                "我的界面与基于Bmob后端云的登录注册，找回密码", R.drawable.p7, R.color.blue1));
        addSlide(AppIntroFragment.createInstance("第八日",
                "百度地图API", R.drawable.p8, R.color.purple_200));
        setSkipText("跳过");
        setDoneText("完成");
        // 全屏模式
        setImmersiveMode();
    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // 跳过之后跳转到首页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // 结束之后跳转到首页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
