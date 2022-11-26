package com.xiaohuowa.lh138.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xiaohuowa.lh138.R;

import java.util.List;

public class VideoListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public VideoListAdapter(@Nullable List<String> data) {
        super(R.layout.item_video_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
        // 替换文本
        baseViewHolder.setText(R.id.textView, s);
    }
}
