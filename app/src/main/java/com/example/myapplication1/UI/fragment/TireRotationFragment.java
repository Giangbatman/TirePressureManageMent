package com.example.myapplication1.UI.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication1.MainActivity;
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
                lopphaitruoc.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                if (clickedTV == null) clickedTV = lopphaitruoc;
                else rotateSensor(clickedTV, lopphaitruoc);
                isClicked = !isClicked;
//                resetColor();
            }
        });
        loptraitruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loptraitruoc.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                if (clickedTV == null) clickedTV = loptraitruoc;
                else rotateSensor(clickedTV, loptraitruoc);
                isClicked = !isClicked;
//                resetColor();
            }
        });
        lopphaisau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopphaisau.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                if (clickedTV == null) clickedTV = lopphaisau;
                else rotateSensor(clickedTV, lopphaisau);
                isClicked = !isClicked;
//                resetColor();
            }
        });
        loptraisau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loptraisau.setBackground(getResources().getDrawable(R.drawable.corner_setting_onclick));
                if (clickedTV == null) clickedTV = loptraisau;
                else rotateSensor(clickedTV, loptraisau);
                isClicked = !isClicked;
//                resetColor();
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
        if(tv1 == tv2) 
        {
            resetColor();
            return;
        }
        new AlertDialog.Builder(MainActivity.getInstance())
                .setTitle("Đảo lốp")
                .setMessage("Xác nhận đảo cảm biến lốp " + tv1.getText() + " với lốp " + tv2.getText() + "?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        Sensor sensor1 = getSensorByName((String) tv1.getText());
                        Sensor sensor2 = getSensorByName((String) tv2.getText());
                        String key1 = getKeySensor(sensor1);
                        String key2 = getKeySensor(sensor2);
                        TireSystem sys = TireSystem.getInstance();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            sys.mapSensor.replace(key1, sensor2);
                            sys.mapSensor.replace(key2, sensor1);
                        }


                        String tmp = (String) tv1.getText();
                        tv1.setText(tv2.getText());
                        tv2.setText(tmp);
                        resetColor();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetColor();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public Sensor getSensorByName(String name) {
        switch (name) {
            case "Cảm biến 1": return TireSystem.getInstance().getPressureSensor1();
            case "Cảm biến 2": return TireSystem.getInstance().getPressureSensor2();
            case "Cảm biến 3": return TireSystem.getInstance().getPressureSensor3();
            case "Cảm biến 4": return TireSystem.getInstance().getPressureSensor4();
            default:
                return null;
        }
    }

    public String getKeySensor(Sensor sensor) {
        TireSystem sys = TireSystem.getInstance();
        for (String key : TireSystem.getInstance().mapSensor.keySet()) {
            if (sys.mapSensor.get(key).equals(sensor)) return key;
        }
        return null;
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
