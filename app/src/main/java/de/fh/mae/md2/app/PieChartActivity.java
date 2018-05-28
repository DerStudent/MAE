
package de.fh.mae.md2.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        PieChart chart = (PieChart) findViewById(R.id.chart1);

        float[] werte = new float[5];
        werte[0] = 20.0f;
        werte[1] = 45.0f;
        werte[2] = 2.0f;
        werte[3] = 15.0f;
        werte[4] = 30.0f;

        List<PieEntry> list = new ArrayList<>();

        for (float wert : werte) {
            list.add(new PieEntry(wert));
        }

        PieDataSet dataset = new PieDataSet(list, "PiChart");
        PieData lineData = new PieData(dataset);
        chart.setData(lineData);
        chart.invalidate(); // refresh

    }
}
