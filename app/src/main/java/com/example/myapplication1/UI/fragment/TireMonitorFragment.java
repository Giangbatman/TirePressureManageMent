package com.example.myapplication1.UI.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication1.R;


public class TireMonitorFragment extends Fragment {

    private static TireMonitorFragment monitor = null;
    private static String C = "\u2103";
    private static String F = "\u2109";

    private TextView apsuatlopsaubentrai;
    private TextView apsuatlopsaubenphai;
    private TextView apsuatloptruocbentrai;
    private TextView apsuatloptruocbenphai;

    private TextView nhietdolopsaubentrai;
    private TextView nhietdolopsaubenphai;
    private TextView nhietdoloptruocbentrai;
    private TextView nhietdoloptruocbenphai;

    private String presRuler = "Bar";
    private String temperatureRuler = C;

    public static TireMonitorFragment getMonitor() {
        if (monitor == null) {
            monitor = new TireMonitorFragment();
//            monitor.reset();
        }
        System.out.println("---------------1");
        return monitor;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tire_monitor, container, false);
        apsuatloptruocbentrai = view.findViewById(R.id.apsuatloptruocbentrai);
        apsuatloptruocbenphai = view.findViewById(R.id.apsuatloptruocbenphai);
        apsuatlopsaubentrai = view.findViewById(R.id.apsuatlopsaubentrai);
        apsuatlopsaubenphai = view.findViewById(R.id.apsuatlopsaubenphai);

        nhietdoloptruocbentrai = view.findViewById(R.id.nhietdoloptruocbentrai);
        nhietdoloptruocbenphai = view.findViewById(R.id.nhietdoloptruocbenphai);
        nhietdolopsaubentrai = view.findViewById(R.id.nhietdolopsaubentrai);
        nhietdolopsaubenphai = view.findViewById(R.id.nhietdolopsaubenphai);
        System.out.println("-------------2");

        return view;
    }

    public void reset() {
        apsuatlopsaubentrai.setText("0.0 Bar");
        apsuatlopsaubenphai.setText("0.0 Bar");
        apsuatloptruocbenphai.setText("0.0 Bar");
        apsuatloptruocbentrai.setText("0.0 Bar");

        nhietdolopsaubenphai.setText("-49 \u2109C");
        nhietdolopsaubentrai.setText("-49 \u2109C");
        nhietdoloptruocbenphai.setText("-49 \u2109C");
        nhietdoloptruocbentrai.setText("-49 \u2109C");
    }

    @SuppressLint("SetTextI18n")
    public void setPressure(String press) {
        apsuatlopsaubentrai.setText(press + presRuler);
        apsuatlopsaubenphai.setText(press + presRuler);
        apsuatloptruocbenphai.setText(press + presRuler);
        apsuatloptruocbentrai.setText(press + presRuler);
    }

    public void setTemperature(String temperature) {
        nhietdolopsaubenphai.setText(temperature + temperatureRuler);
        nhietdolopsaubentrai.setText(temperature + temperatureRuler);
        nhietdoloptruocbenphai.setText(temperature + temperatureRuler);
        nhietdoloptruocbentrai.setText(temperature + temperatureRuler);
    }
}
