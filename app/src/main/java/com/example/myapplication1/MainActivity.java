package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.UI.FloatingViewService;
import com.example.myapplication1.UI.fragment.TireJointFragment;
import com.example.myapplication1.UI.fragment.TireMonitorFragment;
import com.example.myapplication1.UI.fragment.TireRotationFragment;
import com.example.myapplication1.UI.fragment.TireSettingFragment;
import com.example.myapplication1.system.TireSystem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tGiamSat;
    TextView tKhopLop;
    TextView tDaoLop;
    TextView tCaiDat;
    TextView tAmThanh;

    public static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    SensorEventListener pressureSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            System.out.println(sensorEvent.values[0]);
            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.valuesPress= values[0];
            if(values[0]<200) {
                TireSystem.showAlertPressureLow();
            }
            else if (values[0] > 1000) {
                TireSystem.showAlertPressureHigh();
            }
            else {
                TireSystem.isPass = false;
            }
            monitor.setPressure(values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    SensorEventListener temperatureSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            System.out.println(sensorEvent.values[0]);
            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.valuesTemp= values[0];
            monitor.setTemperature(values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private int rightScreenID = R.id.right_screen;


    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, FloatingViewService.class));
        instance = this;
        tGiamSat = findViewById(R.id.tvGiamSat);
        tGiamSat.setBackgroundColor(R.color.onclick);
        tKhopLop = findViewById(R.id.tvKhopLop);
        tDaoLop = findViewById(R.id.tbDaoLop);
        tCaiDat = findViewById(R.id.tbCaiDat);
        tAmThanh = findViewById(R.id.tbAmThanh);

        setActionButtonLeftBar();

        setRightSreen(TireMonitorFragment.getMonitor());

        SensorManager manager = ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
        TireSystem tireSystem = TireSystem.getInstance();
        tireSystem.setSensorManager(manager);

        List<Sensor> pressureSensorList = manager.getSensorList(Sensor.TYPE_PRESSURE);
        tireSystem.setPressureSensor(pressureSensorList.get(0));

        List<Sensor> temperatureSensorList = manager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
        tireSystem.setTemperatureSensor(temperatureSensorList.get(0));

        System.out.println("PRESSURE LIST: ");
        TireSystem.show(pressureSensorList);

        System.out.println("TEMPERATURE LIST: ");
        TireSystem.show(temperatureSensorList);
    }

    @SuppressLint("ResourceAsColor")
    public void resetColor() {
        tGiamSat.setBackgroundResource(R.color.defaultColor);
        tGiamSat.setBackgroundResource(R.color.defaultColor);
        tKhopLop.setBackgroundResource(R.color.defaultColor);
        tDaoLop.setBackgroundResource(R.color.defaultColor);
        tCaiDat.setBackgroundResource(R.color.defaultColor);
        tAmThanh.setBackgroundResource(R.color.defaultColor);
    }

    public void setActionButtonLeftBar() {
        tGiamSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked1");
                resetColor();
                tGiamSat.setBackgroundResource(R.color.onclick);
                setRightSreen(TireMonitorFragment.getMonitor());
            }
        });


        tKhopLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                System.out.println("clicked2");
                tKhopLop.setBackgroundResource(R.color.onclick);
                setRightSreen(TireJointFragment.getJointFragment());
            }
        });
        tDaoLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                System.out.println("clicked3");
                tDaoLop.setBackgroundResource(R.color.onclick);
                setRightSreen(TireRotationFragment.getRotationFragment());
            }
        });
        tCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                System.out.println("clicked4");
                tCaiDat.setBackgroundResource(R.color.onclick);
                setRightSreen(TireSettingFragment.getSettingFragment());
            }
        });
        tAmThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                System.out.println("clicked5");
                tAmThanh.setBackgroundResource(R.color.onclick);
            }
        });
    }

    public void setRightSreen(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(rightScreenID, fragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TireSystem system = TireSystem.getInstance();
        SensorManager manager = system.getSensorManager();

        Sensor pressureSensor = system.getPressureSensor();
        Sensor temperatureSensor = system.getTemperatureSensor();

        manager.registerListener(pressureSensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(temperatureSensorEventListener, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager manager = TireSystem.getInstance().getSensorManager();
        Sensor sensor = TireSystem.getInstance().getPressureSensor();
        manager.unregisterListener(pressureSensorEventListener);
    }


}