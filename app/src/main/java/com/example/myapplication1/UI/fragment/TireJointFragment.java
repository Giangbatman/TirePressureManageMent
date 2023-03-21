package com.example.myapplication1.UI.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.R;

public class TireJointFragment extends Fragment {
    private static TireJointFragment jointFragment = null;

    public static TireJointFragment getJointFragment() {
        if (jointFragment == null) {
            jointFragment = new TireJointFragment();
        }
        return jointFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tire_joint, container, false);
    }

}
