package com.xiaohuowa.lh138.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.bean.NewsBean;
import com.xiaohuowa.lh138.utils.NetUtils;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {

    public HomeAdapter(List<NewsBean> data) {
        super(data);
        // 绑定 layout 对应的 type
        addItemType(1, R.layout.item_home1);
        addItemType(2, R.layout.item_home2);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsBean item) {
        // 根据返回的 type 分别设置数据
//        switch (helper.getItemViewType()) {
        switch (item.getType()) {
            case 1:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg1())
                        .into((ImageView) helper.getView(R.id.imageView));  // id不对的话会报错.
                break;
            case 2:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg1())
                        .into((ImageView) helper.getView(R.id.imageView));
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg2())
                        .into((ImageView) helper.getView(R.id.imageView2));
                Glide.with(getContext()).load(NetUtils.BASE_URL + item.getImg3())
                        .into((ImageView) helper.getView(R.id.imageView3));
                break;
            default:
                break;
        }
    }
}