package com.xiaohuowa.lh138.utils;

import androidx.lifecycle.LiveData;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.xiaohuowa.lh138.bean.NewsBean;
import com.xiaohuowa.lh138.bean.PythonBean;
import com.xiaohuowa.lh138.bean.VideoBean;

import java.util.List;

import retrofit2.http.GET;

public interface GetRequest {

    @GET("home_ad_list_data.json")
    LiveData<Resource<List<NewsBean>>> getAdList();
    @GET("home_news_list_data.json")
    LiveData<Resource<List<NewsBean>>> getNewsList();

    @GET("python_list_data.json")
    LiveData<Resource<List<PythonBean>>> getPythonList();
    @GET("video_list_data.json")
    LiveData<Resource<List<VideoBean>>> getVideoList();
}
