package com.xiaohuowa.lh138.ui.video.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.adapter.VideoListAdapter;

import java.util.Arrays;
import java.util.List;

public class VideoListFragment extends Fragment {

    private final VideoDetailFragment videoDetailFragment;
    private List<String> list;
    private String url0 = "http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4";
    private String url1 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public VideoListFragment(String[] list, VideoDetailFragment videoDetailFragment) {
        this.list = Arrays.asList(list);
        this.videoDetailFragment = videoDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 水平分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
        VideoListAdapter videoListAdapter = new VideoListAdapter(list);
        recyclerView.setAdapter(videoListAdapter);
        // 点击切换视频
        videoListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position%2==0) {
                videoDetailFragment.playNewUrl(url0);
            } else {
                videoDetailFragment.playNewUrl(url1);
            }
        });

        return root;
    }
}