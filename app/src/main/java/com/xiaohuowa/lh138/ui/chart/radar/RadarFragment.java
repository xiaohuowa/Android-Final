package com.xiaohuowa.lh138.ui.chart.radar;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.base.BaseFragment2;
import com.xiaohuowa.lh138.ui.chart.pie.PieViewModel;

import java.util.ArrayList;
import java.util.List;

public class RadarFragment extends BaseFragment2 {


    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_radar, container, false);
        RadarChart chart = root.findViewById(R.id.radarChart);
        RadarViewModel radarViewModel = new ViewModelProvider(this).get(RadarViewModel.class);
        radarViewModel.getPieList().observe(getViewLifecycleOwner(), pieBeans -> {
            List<PieEntry> entries = new ArrayList<>();
            for (int i = 0; i < pieBeans.size(); i++) {
                // 前面是占比，后面是文本
                entries.add(new PieEntry(pieBeans.get(i).getPercentage(), pieBeans.get(i).getSalaries()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "工资占比"); // add entries to dataset
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dataSet.setColors(Color.BLUE, Color.GREEN, Color.GRAY, Color.MAGENTA);
            }
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setValueTextSize(13f);
            // 添加百分比
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value + " %";
                }
            });

            PieData pieData = new PieData(dataSet);
            chart.setData(pieData);
            chart.invalidate(); // refresh0
            // 设置中心文本
            chart.setCenterText("点击显示\n相关数据");
            chart.setCenterTextColor(Color.parseColor("#333333"));
            chart.setCenterTextSize(24f);
            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    // 点击了就显示相关数据
                    chart.setCenterText(((PieEntry)e).getLabel() + "\n" + ((PieEntry)e).getValue() + "%");
                }

                @Override
                public void onNothingSelected() {
                    // 没点击就恢复默认
                    chart.setCenterText("点击显示\n相关数据");
                }
            });
            chart.setExtraTopOffset(10f);  // 设置顶部偏移

            // 修改图例
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);  // 水平靠右
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  // 垂直靠上;
            // 修改描述
            Description description = chart.getDescription();
            // set the description text
            description.setText("Java工程师经验与工资占比情况");
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540, 100f);
            description.setTextSize(16f);
            description.setTextColor(Color.parseColor("#333333"));
            // 设置动画
            chart.animateXY(3000, 3000);
        });

        return root;
    }*/


}