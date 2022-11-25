package com.xiaohuowa.lh138.ui.chart.line;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xiaohuowa.lh138.bean.LineBean;

import java.util.ArrayList;
import java.util.List;

public class LineViewModel extends ViewModel {

    private MutableLiveData<List<LineBean>> lineList;

    public LineViewModel() {
        String[] years = new String[]{"应届生","1-2年","2-3年",
                "3-5年","5-8年","8-10年","10年"};
        int[] salaries = {6000,13000,20000,26000,35000,50000,100000};
        lineList = new MutableLiveData<>();
        List<LineBean> value = new ArrayList<>();
        for (int i = 0; i < years.length; i++) {
            value.add(new LineBean(years[i], salaries[i]));
        }

        lineList.setValue(value);

    }

    public LiveData<List<LineBean>> getLineList() {
        return lineList;
    }
}