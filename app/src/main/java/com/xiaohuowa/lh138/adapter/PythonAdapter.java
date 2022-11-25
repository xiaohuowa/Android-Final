package com.xiaohuowa.lh138.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.bean.PythonBean;

import java.util.List;

public class PythonAdapter extends BaseQuickAdapter<PythonBean, BaseViewHolder> {

    public PythonAdapter(@Nullable List<PythonBean> data) {
        super(R.layout.item_python, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PythonBean pythonBean) {
        // 数据绑定
        baseViewHolder.setText(R.id.textView, pythonBean.getAddress());
        baseViewHolder.setText(R.id.textView2, pythonBean.getContent());
        baseViewHolder.setText(R.id.textView3, pythonBean.getOpen_class());
    }
}
