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
        addSlide(AppIntroFragment.createInstance("第一部分",
                "启动页、引导页、闪屏页与基础框架搭建", R.drawable.pp1, R.color.teal_200));
        addSlide(AppIntroFragment.createInstance("第二部分",
                "LiveData+Retrofit网络请求与下拉刷新SmartRefreshLayout", R.drawable.pp2, R.color.purple_200));
        addSlide(AppIntroFragment.createInstance("第三部分",
                "RecyclerAdapter框架与轮播图banner", R.drawable.pp3, R.color.blue1));
        addSlide(AppIntroFragment.createInstance("第四部分",
                "新闻详情页AgentWeb与Python详情页", R.drawable.pp4, R.color.blue2));
        addSlide(AppIntroFragment.createInstance("第五部分",
                "爆炸菜单BoomMenu与统计图表MPAndroidChart", R.drawable.pp5, R.color.blue3));
        addSlide(AppIntroFragment.createInstance("第六部分",
                "视频列表与视频播放器GSYVideoPlayer", R.drawable.pp6, R.color.blue2));
        addSlide(AppIntroFragment.createInstance("第七部分",
                "我的界面与基于Bmob后端云的登录注册，找回密码", R.drawable.pp7, R.color.blue1));
        addSlide(AppIntroFragment.createInstance("第八部分",
                "百度地图API", R.drawable.pp8, R.color.purple_200));
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
