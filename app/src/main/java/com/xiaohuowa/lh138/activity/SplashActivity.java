package com.xiaohuowa.lh138.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaohuowa.lh138.R;

public class SplashActivity extends AppCompatActivity {

    private boolean isSkip;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsyimg.3dmgame.com%2Fuploadimg%2Fupload%2Fimage%2F20200722%2F20200722141720_84005.gif&refer=http%3A%2F%2Fsyimg.3dmgame.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672897013&t=e85995b0f5fdc206a8a879b224522c72")
//                .load("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png")
//                .load("http://5b0988e595225.cdn.sohucs.com/images/20190831/05de49d16e374e9e997bc97fdf29b0cc.gif")
                .into(imageView);
        new Handler().postDelayed(() -> {
            if (!isSkip && !isBack) {
                // 如果没有跳过，就从闪屏调到main
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

    public void skip(View view) {
        isSkip = true;
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * 如果按了返回键，就不继续跳转了
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
    }
}