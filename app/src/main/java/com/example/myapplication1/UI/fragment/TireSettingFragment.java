package com.example.myapplication1.UI.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication1.R;
import com.example.myapplication1.system.TireSystem;

public class TireSettingFragment extends Fragment  {

    private TextView bar;
    private TextView psi;
    private TextView kpa;
    private TextView kg;
    private TextView c;
    private TextView f;
    private TextView default_setting;
    private TextView bao_dong_tren;
    private TextView bao_dong_duoi;
    private SeekBar seekBar1;
    private SeekBar seekBar2;


    private static TireSettingFragment settingFragment = null;

    public static TireSettingFragment getSettingFragment() {
        if (settingFragment == null) {
            settingFragment = new TireSettingFragment();
        }
        return settingFragment;
    }

    @SuppressLint({"MissingInflatedId", "UseCompatLoadingForDrawables", "SetTextI18n"})
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tire_setting, container, false);
        bar = view.findViewById(R.id.bar);
        psi = view.findViewById(R.id.psi);
        kpa = view.findViewById(R.id.kpa);
        kg = view.findViewById(R.id.kg);
        c = view.findViewById(R.id.c);
        f = view.findViewById(R.id.f);
        default_setting = view.findViewById(R.id.default_setting);
        bao_dong_tren = view.findViewById(R.id.bao_dong_tren);
        bao_dong_duoi = view.findViewById(R.id.bao_dong_duoi);
        seekBar1 = view.findViewById(R.id.seekBar1);
        seekBar2 = view.findViewById(R.id.seekBar2);

        seekBar1.setProgress(TireSystem.apSuatTren);
        seekBar2.setProgress(TireSystem.apSuatDuoi);
        bao_dong_tren.setText(getString(R.string.bao_dong_tren) + TireSystem.apSuatTren);
        bao_dong_duoi.setText(getString(R.string.bao_dong_duoi) + TireSystem.apSuatDuoi);

        resetColor();

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TireSystem.getInstance().pressureDegree = TireSystem.BAR;
                resetColor();
            }
        });
        psi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().pressureDegree = TireSystem.PSI;
                resetColor();
                TireMonitorFragment.getMonitor().presRuler ="PSI";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    TireSystem.getInstance().playWarningSound(getContext());
                }
            }
        });
        kpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().pressureDegree = TireSystem.KPA;
                resetColor();
                TireMonitorFragment.getMonitor().presRuler="KPA";
            }
        });
        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().pressureDegree = TireSystem.KG;
                resetColor();
                TireMonitorFragment.getMonitor().presRuler="KG";
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().temperatureDegree = TireSystem.C;
                resetColor();
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().temperatureDegree = TireSystem.F;
                resetColor();
                TireMonitorFragment.getMonitor().temperatureRuler="\u2109 F";

            }
        });

        default_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TireSystem.getInstance().pressureDegree = TireSystem.BAR;
                TireSystem.getInstance().temperatureDegree = TireSystem.C;
                resetColor();
                TireMonitorFragment.getMonitor().presRuler="Bar";
                TireMonitorFragment.getMonitor().temperatureRuler="C";

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChangedValue = i;
            }


            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            public void onStopTrackingTouch(SeekBar seekBar) {
                bao_dong_tren.setText(getString(R.string.bao_dong_tren) + progressChangedValue);
                TireSystem.apSuatTren = progressChangedValue;
//                System.out.println(progressChangedValue);
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChangedValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                bao_dong_duoi.setText(getString(R.string.bao_dong_duoi) + progressChangedValue);
                TireSystem.apSuatDuoi = progressChangedValue;
            }
        });

//        return inflater.inflate(R.layout.tire_rotation, container, false);
        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void resetColor() {
        TireMonitorFragment monitorFragment = TireMonitorFragment.getMonitor();

        bar.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        psi.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        kpa.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        kg.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        c.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        f.setBackground(getResources().getDrawable(R.drawable.corner_setting));
        if (TireSystem.getInstance().pressureDegree.equals(TireSystem.BAR)) {
            bar.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick))
            ;
        }

        if (TireSystem.getInstance().pressureDegree.equals(TireSystem.PSI)) {
            psi.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
        }
        if (TireSystem.getInstance().pressureDegree.equals(TireSystem.KPA)) {
            kpa.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
        }
        if (TireSystem.getInstance().pressureDegree.equals(TireSystem.KG)) {
            kg.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
        }

        if (TireSystem.getInstance().temperatureDegree.equals(TireSystem.C)) {
            c.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
        }
        else if (TireSystem.getInstance().temperatureDegree.equals(TireSystem.F)) {
            f.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
        }

//        monitorFragment.setPressure(monitorFragment.valuesPress);
        monitorFragment.reset();
        System.out.println(monitorFragment.valuesTemp);
        monitorFragment.setTemperature(monitorFragment.valuesTemp);
    }
}
