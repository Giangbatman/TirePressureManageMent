package com.example.myapplication1.UI.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.myapplication1.R;

public class TireRotationFragment extends Fragment {

    private static TireRotationFragment rotationFragment = null;

    public static TireRotationFragment getRotationFragment() {
        if (rotationFragment == null) {
            rotationFragment = new TireRotationFragment();
        }
        return rotationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tire_rotation, container, false);
    }
}
