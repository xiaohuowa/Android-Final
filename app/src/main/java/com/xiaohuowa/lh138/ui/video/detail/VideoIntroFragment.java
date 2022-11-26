package com.xiaohuowa.lh138.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaohuowa.lh138.R;

public class VideoIntroFragment extends Fragment {


    private final String intro;

    public VideoIntroFragment(String intro) {
        this.intro = intro;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_intro, container, false);
        TextView textView = root.findViewById(R.id.textView);
        // 显示视频简介
        textView.setText(intro);
        return root;
    }
}