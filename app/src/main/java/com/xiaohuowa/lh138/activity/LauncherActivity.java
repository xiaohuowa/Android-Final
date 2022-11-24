package com.xiaohuowa.lh138.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.bumptech.glide.Glide;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                // 如果是第一次进入的话，进入引导页
                boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
                if (isFirst) {
                    Intent intent = new Intent(LauncherActivity.this, IntroActivity.class);
                    // 跳转引导页
                    startActivity(intent);
                    // 跳完引导页之后，设置标记为false
//                    sharedPreferences.edit().putBoolean("isFirst", false).apply();
                } else {
                    Intent intent = new Intent(LauncherActivity.this, SplashActivity.class);
                    startActivity(intent);
                }
                // 不论成功失败，都要用finish关掉
                finish();
            }
        },1000);
        // 预加载
        Glide.with(this)
                .load("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png")
//                .load("http://5b0988e595225.cdn.sohucs.com/images/20190831/05de49d16e374e9e997bc97fdf29b0cc.gif")
                .preload();
    }
}