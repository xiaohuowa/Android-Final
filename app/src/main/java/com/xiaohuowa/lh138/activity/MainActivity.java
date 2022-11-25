package com.xiaohuowa.lh138.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.base.OnFragmentKeyDownListener;
import com.xiaohuowa.lh138.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    /**
     * 做个列表，来记录二级Fragment的进出顺序
     */
    private List<OnFragmentKeyDownListener> onFragmentKeyDownListenerList = new ArrayList<>();
    private long exitTime;

    public void setOnFragmentKeyDownListener(OnFragmentKeyDownListener onFragmentKeyDownListener) {
        onFragmentKeyDownListenerList.add(onFragmentKeyDownListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_chart, R.id.navigation_video, R.id.navigation_me)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(navView, navController);
    }

    /**
     * 返回键支持
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (onFragmentKeyDownListenerList.size() != 0) {
            onFragmentKeyDownListenerList.remove(onFragmentKeyDownListenerList.size() - 1);
        }
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果按下了返回键
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if (onFragmentKeyDownListenerList.size() != 0) {
                if (onFragmentKeyDownListenerList.get(onFragmentKeyDownListenerList.size()-1)
                        .onKeyDown(keyCode, event)) {
                    // 如果返回真，进来了，说明不需要处理
                    return true;
                } else {
                    onSupportNavigateUp();
                }
            } else if(System.currentTimeMillis() - exitTime > 2000){
                // 没有二级Fragment了，说明在首页，提示按两次退出
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // 连续按了两次了，实现退出
                // finish();  // 真退出
                // 假退出，回来的时候加载更快
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}