package com.xiaohuowa.lh138.ui.home.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.github.leonardoxh.livedatacalladapter.Resource;
import com.xiaohuowa.lh138.bean.PythonBean;
import com.xiaohuowa.lh138.ui.utils.NetUtils;

import java.util.List;

public class PythonViewModel extends ViewModel {
    public LiveData<List<PythonBean>> getPythonList() {
        return Transformations.map(NetUtils.get().getPythonList(), Resource::getResource);
    }
}