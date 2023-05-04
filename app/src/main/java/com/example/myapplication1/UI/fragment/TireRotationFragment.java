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

import java.util.ArrayList;
import java.util.List;

public class TireRotationFragment extends Fragment {

    private TextView loptraisau;
    private TextView loptraitruoc;
    private TextView lopphaisau;
    private TextView lopphaitruoc;

    boolean isClicked = false;

    TextView clickedTV = null;
    private static TireRotationFragment rotationFragment = null;
    List<TextView> lisTV = new ArrayList<>();


    public static TireRotationFragment getRotationFragment() {
        if (rotationFragment == null) {
            rotationFragment = new TireRotationFragment();
        }
        return rotationFragment;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tire_rotation, container, false);
        lopphaitruoc = view.findViewById(R.id.lopphaitruoc);
        lopphaisau = view.findViewById(R.id.lopphaisau);
        loptraitruoc = view.findViewById(R.id.loptraitruoc);
        loptraisau = view.findViewById(R.id.loptraisau);
        if(lisTV.isEmpty()) {
            lisTV.add(lopphaisau);
            lisTV.add(lopphaitruoc);
            lisTV.add(loptraisau);
            lisTV.add(loptraitruoc);
        }
        else {
            lopphaisau.setText(lisTV.get(0).getText());
            lopphaitruoc.setText(lisTV.get(1).getText());
            loptraisau.setText(lisTV.get(2).getText());
            loptraitruoc.setText(lisTV.get(3).getText());
        }
        lopphaitruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedTV == null) clickedTV = lopphaitruoc;
                else rotateSensor(clickedTV, lopphaitruoc);
                lopphaitruoc.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                isClicked = !isClicked;
                resetColor();
            }
        });
        loptraitruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedTV == null) clickedTV = loptraitruoc;
                else rotateSensor(clickedTV, loptraitruoc);
                loptraitruoc.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                isClicked = !isClicked;
                resetColor();
            }
        });
        lopphaisau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedTV == null) clickedTV = lopphaisau;
                else rotateSensor(clickedTV, lopphaisau);
                lopphaisau.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                isClicked = !isClicked;
                resetColor();
            }
        });
        loptraisau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedTV == null) clickedTV = loptraisau;
                else rotateSensor(clickedTV, loptraisau);
                loptraisau.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                isClicked = !isClicked;
                resetColor();
            }
        });


        return view;
    }

    public void resetColor() {
        if (!isClicked) {
            clickedTV = null;
            loptraitruoc.setBackground(getResources().getDrawable(R.drawable.corner));
            lopphaitruoc.setBackground(getResources().getDrawable(R.drawable.corner));
            loptraisau.setBackground(getResources().getDrawable(R.drawable.corner));
            lopphaisau.setBackground(getResources().getDrawable(R.drawable.corner));
        }
    }

    public void rotateSensor(TextView tv1, TextView tv2) {
        String tmp = (String) tv1.getText();
        tv1.setText(tv2.getText());
        tv2.setText(tmp);
    }

    @Override
    public void onStop() {
        System.out.println("hello");
        lisTV.clear();
        lisTV.add(lopphaisau);
        lisTV.add(lopphaitruoc);
        lisTV.add(loptraisau);
        lisTV.add(loptraitruoc);
        super.onStop();
    }
}
