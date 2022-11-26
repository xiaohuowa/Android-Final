package com.xiaohuowa.lh138.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.base.BaseFragment2;
import com.xiaohuowa.lh138.bean.User;

import cn.bmob.v3.BmobUser;

public class InfoFragment extends BaseFragment2 {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = root.findViewById(R.id.textView);
        TextView textView2 = root.findViewById(R.id.textView2);
        TextView textView3 = root.findViewById(R.id.textView3);
        TextView textView4 = root.findViewById(R.id.textView4);
        TextView textView5 = root.findViewById(R.id.textView5);
        if (BmobUser.isLogin()) {
            // 登录过，获取当前账号
            User user = BmobUser.getCurrentUser(User.class);
            // 获取数据
            textView.setText(user.getUsername());
            textView.setText(user.getNickName());
            textView.setText(user.isSex() ? "男" : "女");
            textView.setText(user.getEmail());
            textView.setText(user.getInfo());
        }
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::logOut);
        return root;
    }

    private void logOut(View view) {
        BmobUser.logOut();
        // 退出了返回上级
        Navigation.findNavController(view).navigateUp();
        Snackbar.make(view, "退出登录", Snackbar.LENGTH_LONG).show();
    }
}