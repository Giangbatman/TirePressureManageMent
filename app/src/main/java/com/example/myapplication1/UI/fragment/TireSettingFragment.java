package com.example.myapplication1.UI.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.myapplication1.R;

public class TireSettingFragment extends Fragment {

    private static TireSettingFragment settingFragment = null;

    public static TireSettingFragment getSettingFragment() {
        if (settingFragment == null) {
            settingFragment = new TireSettingFragment();
        }
        return settingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tire_setting, container, false);
    }
}
