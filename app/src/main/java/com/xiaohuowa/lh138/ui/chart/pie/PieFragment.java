package com.xiaohuowa.lh138.ui.chart.pie;

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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.base.BaseFragment2;
import com.xiaohuowa.lh138.ui.chart.line.LineViewModel;

import java.util.ArrayList;
import java.util.List;

public class PieFragment extends BaseFragment2 {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pie, container, false);
        PieChart chart = root.findViewById(R.id.pieChart);
        PieViewModel pieViewModel = new ViewModelProvider(this).get(PieViewModel.class);
        pieViewModel.getPieList().observe(getViewLifecycleOwner(), pieBeans -> {
            List<PieEntry> entries = new ArrayList<>();
            for (int i = 0; i < pieBeans.size(); i++) {
                // ?????????????????????????????????
                entries.add(new PieEntry(pieBeans.get(i).getPercentage(), pieBeans.get(i).getSalaries()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "????????????"); // add entries to dataset
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dataSet.setColors(Color.BLUE, Color.GREEN, Color.GRAY, Color.MAGENTA);
            }
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setValueTextSize(13f);
            // ???????????????
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value + " %";
                }
            });

            PieData pieData = new PieData(dataSet);
            chart.setData(pieData);
            chart.invalidate(); // refresh0
            // ??????????????????
            chart.setCenterText("????????????\n????????????");
            chart.setCenterTextColor(Color.parseColor("#333333"));
            chart.setCenterTextSize(24f);
            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    // ??????????????????????????????
                    chart.setCenterText(((PieEntry)e).getLabel() + "\n" + ((PieEntry)e).getValue() + "%");
                }

                @Override
                public void onNothingSelected() {
                    // ????????????????????????
                    chart.setCenterText("????????????\n????????????");
                }
            });
            chart.setExtraTopOffset(10f);  // ??????????????????

            // ????????????
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);  // ????????????
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  // ????????????;
            // ????????????
            Description description = chart.getDescription();
            // set the description text
            description.setText("Java????????????????????????????????????");
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540, 100f);
            description.setTextSize(16f);
            description.setTextColor(Color.parseColor("#333333"));
            // ????????????
            chart.animateXY(3000, 3000);
        });

        return root;
    }


}