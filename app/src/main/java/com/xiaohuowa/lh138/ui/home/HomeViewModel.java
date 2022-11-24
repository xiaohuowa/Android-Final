package com.xiaohuowa.lh138.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.xiaohuowa.lh138.bean.NewsBean;
import com.xiaohuowa.lh138.utils.NetUtils;

import java.util.List;

public class HomeViewModel extends ViewModel {

    public LiveData<List<NewsBean>> getNewsList() {
        return Transformations.map(NetUtils.get().getNewsList(), Resource::getResource);
    }

    public LiveData<List<NewsBean>> getAdList() {
        return Transformations.map(NetUtils.get().getAdList() , Resource::getResource);
    }
}