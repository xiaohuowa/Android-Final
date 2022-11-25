package com.xiaohuowa.lh138.ui.chart.line;

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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.base.BaseFragment2;

import java.util.ArrayList;
import java.util.List;

public class LineFragment extends BaseFragment2 {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_line, container, false);
        LineChart chart = root.findViewById(R.id.lineChart);
        LineViewModel lineViewModel = new ViewModelProvider(this).get(LineViewModel.class);
        lineViewModel.getLineList().observe(getViewLifecycleOwner(), lineBeans -> {
            List<Entry> entries = new ArrayList<Entry>();
            for (int i = 0; i < lineBeans.size(); i++) {
                entries.add(new Entry(i, lineBeans.get(i).getSalaries()));
            }
            LineDataSet dataSet = new LineDataSet(entries, "工资"); // add entries to dataset
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dataSet.setColor(Color.argb(.9f,107, 243, 173));
            }
            dataSet.setValueTextColor(Color.BLUE);
            dataSet.setValueTextSize(13f);
            dataSet.setLineWidth(7f);

            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh0

            // 修改X轴
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(10f);
            xAxis.setTextColor(Color.BLACK);
            xAxis.setDrawAxisLine(true);
            xAxis.setDrawGridLines(true);  // 分割线
            xAxis.setAxisLineColor(Color.BLACK);
            xAxis.setAxisLineWidth(3f);
            xAxis.enableGridDashedLine(10f, 10f, 0f);  // 虚线
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return lineBeans.get((int)value).getYear();
                }
            });
            // 修改Y轴
            chart.getAxisRight().setEnabled(false); // 不要右轴了
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setAxisLineColor(Color.BLACK);  // 颜色
            yAxis.setAxisLineWidth(3f);  // 粗细
            yAxis.setAxisMinimum(0f); // start at zero
            yAxis.setSpaceTop(38.2f);  // 设置距离顶部的距离
            yAxis.enableGridDashedLine(10f, 10f, 0f);  // 虚线
            yAxis.setTextColor(Color.BLACK);
            LimitLine limitLine = new LimitLine(23600f, "北京市Java平均工资");
            limitLine.setLineColor(Color.rgb(205, 150, 150));
            limitLine.setLineWidth(2f);
            yAxis.addLimitLine(limitLine);  // 做一条参考线
            // 修改图例
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);  // 水平靠右
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  // 垂直靠上;
            // 修改描述
            Description description = chart.getDescription();
            // set the description text
            description.setText("Java工程师经验与工资对应情况");
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540, 100f);
            description.setTextSize(16f);
            description.setTextColor(Color.parseColor("#333333"));
            // 设置动画
            chart.animateX(3000);
        });

        return root;
    }


}