package com.xiaohuowa.lh138.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.bean.User;
import com.xiaohuowa.lh138.databinding.FragmentMeBinding;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;
//import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends Fragment {


    private boolean isLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_me, container, false);
        // 头像圆形图
        CircleImageView circleImageView = root.findViewById(R.id.circleImageView);
        // 点头像的事件监听
        circleImageView.setOnClickListener(this::click);
        TextView textView = root.findViewById(R.id.textView);
        textView.setOnClickListener(this::click);
        // 判断是否登录，保存登录状态
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            textView.setText(user.getUsername());
            isLogin = true;
        } else {
            textView.setText("点击登录");
            isLogin = false;
        }

        return root;
    }

    private void click(View view) {
        if (isLogin) {
            // 如果是登录过的话，跳转个人信息
            Navigation.findNavController(view).navigate(R.id.action_navigation_me_to_infoFragment);
        } else {
            // 没登录过，跳登录
            Navigation.findNavController(view).navigate(R.id.action_navigation_me_to_loginFragment);
        }
    }

}