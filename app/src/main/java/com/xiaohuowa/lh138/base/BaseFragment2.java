package com.xiaohuowa.lh138.base;

import android.content.Context;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.xiaohuowa.lh138.activity.MainActivity;

/**
 * 二级Fragment才有向上导航问题
 */
public class BaseFragment2  extends Fragment implements OnFragmentKeyDownListener {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnFragmentKeyDownListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 返回false，交给系统处理
        return false;
    }
}
