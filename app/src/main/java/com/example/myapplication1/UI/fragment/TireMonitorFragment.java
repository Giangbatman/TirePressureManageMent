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
import com.example.myapplication1.system.TireSystem;


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
    public double valuesPress= 0;
    public double valuesTemp= 40;

    //public static String pressRuler = TireSystem.presRuler ;
    //public static String temperatureRulerMonitor = TireSystem.temperatureRuler;
    public String presRuler="Bar";
    public String temperatureRuler= "\u2103C";
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

        setPressure(valuesPress);
        setTemperature(valuesTemp);
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
    public void setPressure(double press) {
        press = transferPressValues(TireSystem.getInstance().pressureDegree, press);
        apsuatlopsaubentrai.setText(String.valueOf(press) + TireSystem.getInstance().pressureDegree);
        apsuatlopsaubenphai.setText(String.valueOf(press) + TireSystem.getInstance().pressureDegree);
        apsuatloptruocbenphai.setText(String.valueOf(press) + TireSystem.getInstance().pressureDegree);
        apsuatloptruocbentrai.setText(String.valueOf(press) + TireSystem.getInstance().pressureDegree);
    }

    public void setTemperature(double temperature) {
        temperature = transferTempValues(TireSystem.getInstance().temperatureDegree, temperature);
        nhietdolopsaubenphai.setText(String.valueOf(temperature) + TireSystem.getInstance().temperatureDegree);
        nhietdolopsaubentrai.setText(String.valueOf(temperature) + TireSystem.getInstance().temperatureDegree);
        nhietdoloptruocbenphai.setText(String.valueOf(temperature) + TireSystem.getInstance().temperatureDegree);
        nhietdoloptruocbentrai.setText(String.valueOf(temperature) + TireSystem.getInstance().temperatureDegree);
    }
    public double transferPressValues(String Type, double valuesPress)
    {
        if(Type.equals(TireSystem.PSI))
            valuesPress = valuesPress*14.5;
        if(Type.equals(TireSystem.KG))
            valuesPress = valuesPress*1.02;
        if(Type.equals(TireSystem.KPA))
            valuesPress = valuesPress*100.0;
        valuesPress = Math.round(valuesPress*100.0)/100.0;
        return valuesPress;
    }
    public double transferTempValues(String Type, double valuesTemp)
    {
        if(Type.equals(TireSystem.F))
            valuesTemp = valuesTemp*9.0/5 + 32.0;
        return Math.round(valuesTemp*100.0)/100.0;
    }
}
