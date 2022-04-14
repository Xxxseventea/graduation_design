package view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthylife.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.RecordBean;
import db.SaveData;
import utils.ChartUtils;
import view.activity.SearchActivity;

/**
 *         final PieChart mChart = (PieChart) findViewById(R.id.chart1);
 *         mChart.setCenterTextColor(Color.BLACK);
 *         HashMap dataMap = new HashMap();
 *         dataMap.put("Ⅰ类水", "8");
 *         dataMap.put("Ⅱ类水", "12");
 *         dataMap.put("Ⅲ类水", "31");
 *         dataMap.put("Ⅳ类水", "24");
 *         dataMap.put("Ⅴ类水", "10");
 *         dataMap.put("劣Ⅴ类水", "15");
 *         PieChartUtil.getPitChart().setPieChart(mChart, dataMap, "水质", true);

 */

public class TrendFragment extends Fragment {
    LineChart lineChart ;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trend,container,false);
        lineChart = view.findViewById(R.id.lineChart);
        button = view.findViewById(R.id.btn_trend);

        ChartUtils chartUtils = new ChartUtils(lineChart,getContext());


        ArrayList<RecordBean> arrayList = SaveData.get(getContext());
        ArrayList<Float> arrayList1 = new ArrayList<>();
        for (int i = 0; i < arrayList.size();i++){
            arrayList1.add(Float.valueOf(arrayList.get(i).getTizhong()));
        }
        chartUtils.initLineChart(arrayList1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



}
