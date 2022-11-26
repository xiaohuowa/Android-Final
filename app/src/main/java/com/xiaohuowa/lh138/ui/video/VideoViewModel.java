package com.xiaohuowa.lh138.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.xiaohuowa.lh138.bean.VideoBean;
import com.xiaohuowa.lh138.ui.utils.NetUtils;

import java.util.List;

public class VideoViewModel extends ViewModel {

    public LiveData<List<VideoBean>> getVideoList() {
        return Transformations.map(NetUtils.get().getVideoList(), Resource::getResource);
    }
}