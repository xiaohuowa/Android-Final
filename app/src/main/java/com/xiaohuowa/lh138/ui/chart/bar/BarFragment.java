package com.xiaohuowa.lh138.ui.chart.bar;

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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.xiaohuowa.lh138.R;

import java.util.ArrayList;
import java.util.List;

public class BarFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bar, container, false);
        BarChart chart = root.findViewById(R.id.barChart);
        BarViewModel barViewModel = new ViewModelProvider(this).get(BarViewModel.class);
        barViewModel.getBarList().observe(getViewLifecycleOwner(),barBeans -> {
            // 两个柱状实体
            List<BarEntry> entries1 = new ArrayList<>();
            List<BarEntry> entries2 = new ArrayList<>();
            for (int i = 0; i < barBeans.size(); i++) {
                entries1.add(new BarEntry(i, barBeans.get(i).getLineBean1().getSalaries()));
                entries2.add(new BarEntry(i, barBeans.get(i).getLineBean2().getSalaries()));
            }
            BarDataSet dataSet1 = new BarDataSet(entries1, "Java工资");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dataSet1.setColor(Color.argb(.9f,107, 243, 173));
            }
            dataSet1.setValueTextColor(Color.BLUE);
            dataSet1.setValueTextSize(13f);
            BarDataSet dataSet2 = new BarDataSet(entries2, "PHP工资");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dataSet1.setColor(Color.argb(.9f,107, 243, 173));
            }
            dataSet1.setValueTextColor(Color.BLUE);
            dataSet1.setValueTextSize(13f);

            BarData barData = new BarData(dataSet1, dataSet2);
            barData.setBarWidth(0.45f); // 两个柱，中间有点间隔
            chart.setData(barData);
            chart.groupBars(-0.5f, 0.04f, 0.03f);  // 偏移为了让柱不会超出左边界限，不会只显示一半
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
                    return barBeans.get((int)value).getLineBean1().getYear();
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
            chart.animateY(3000);
        });

        return root;
    }

}