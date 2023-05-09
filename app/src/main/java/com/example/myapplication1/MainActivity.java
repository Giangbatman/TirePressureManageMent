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
import android.view.View;
import android.widget.TextView;

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

    public static MainActivity getInstance() {
        return instance;
    }
    SensorEventListener pressureSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float[] values = sensorEvent.values;
            TireSystem sys = TireSystem.getInstance();
            sys.setSensorVal1(values[0]);
            System.out.println("Sensor1: " + sys.getSensorVal1());

            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.valuesPress= values[0];


            String lop = "";
            for (String key : sys.mapSensor.keySet()) {
                if (sys.mapSensor.get(key).equals(sys.getPressureSensor1())){
                    lop = key;
                    break;
                }
            }
            monitor.setPressure(sys.getSensorVal1(), lop);
//            monitor.setPressure(values[0]);
//            TireSystem.currentValue = values[0];
            sys.checkAlertPressure(values[0], sys.getPressureSensor1());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


    //sensor Apsuat 2
    SensorEventListener humiditySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            TireSystem sys = TireSystem.getInstance();
            sys.setSensorVal2(values[0]);
            System.out.println("Sensor2: " + sys.getSensorVal2());


            String lop = "";
            for (String key : sys.mapSensor.keySet()) {
                if (sys.mapSensor.get(key).equals(sys.getPressureSensor2())){
                    lop = key;
                    break;
                }
            }
            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.setPressure(sys.getSensorVal2(), lop);
            sys.checkAlertPressure(values[0], sys.getPressureSensor2());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    //Sensor apsuat 3
    SensorEventListener lightSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            TireSystem sys = TireSystem.getInstance();
            sys.setSensorVal3(values[0]);
            System.out.println("Sensor3: " + sys.getSensorVal3());


            String lop = "";
            for (String key : sys.mapSensor.keySet()) {
                if (sys.mapSensor.get(key).equals(sys.getPressureSensor3())){
                    lop = key;
                    break;
                }
            }
            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.setPressure(sys.getSensorVal3(), lop);
            sys.checkAlertPressure(values[0], sys.getPressureSensor3());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    //Sensor apsuat 4
    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            double val = values[0] * 11;
            TireSystem sys = TireSystem.getInstance();
            sys.setSensorVal4(val);
            System.out.println("Sensor4: " + sys.getSensorVal4());


            String lop = "";
            for (String key : sys.mapSensor.keySet()) {
                if (sys.mapSensor.get(key).equals(sys.getPressureSensor4())){
                    lop = key;
                    break;
                }
            }
            TireMonitorFragment monitor = TireMonitorFragment.getMonitor();
            monitor.setPressure(sys.getSensorVal4(), lop);
            sys.checkAlertPressure(val, sys.getPressureSensor1());
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
        tGiamSat.setBackgroundResource(R.color.onclick);
        setRightSreen(TireMonitorFragment.getMonitor());

        SensorManager manager = ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
        TireSystem sys = TireSystem.getInstance();
        sys.setSensorManager(manager);

        List<Sensor> temperatureSensorList = manager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sys.setTemperatureSensor(temperatureSensorList.get(0));

        List<Sensor> pressureSensorList = manager.getSensorList(Sensor.TYPE_PRESSURE);
        sys.setPressureSensor(pressureSensorList.get(0));
        sys.setPressureSensor1(pressureSensorList.get(0));

        List<Sensor> humiditySensorList = manager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
        sys.setPressureSensor2(humiditySensorList.get(0));

        List<Sensor> lightSensorList = manager.getSensorList(Sensor.TYPE_LIGHT);
        sys.setPressureSensor3(lightSensorList.get(0));

        List<Sensor> proximitySensorList = manager.getSensorList(Sensor.TYPE_PROXIMITY);
        sys.setPressureSensor4(proximitySensorList.get(0));

        sys.mapSensor.put(TireSystem.TRAITRUOC, sys.getPressureSensor1());
        sys.mapSensor.put(TireSystem.PHAITRUOC, sys.getPressureSensor2());
        sys.mapSensor.put(TireSystem.TRAISAU, sys.getPressureSensor3());
        sys.mapSensor.put(TireSystem.PHAISAU, sys.getPressureSensor4());

        manager.registerListener(temperatureSensorEventListener, sys.getTemperatureSensor(), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(pressureSensorEventListener, sys.getPressureSensor1(), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(humiditySensorEventListener, sys.getPressureSensor2(), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(lightSensorEventListener, sys.getPressureSensor3(), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(proximitySensorEventListener, sys.getPressureSensor4(), SensorManager.SENSOR_DELAY_NORMAL);

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
//                resetColor();
//                System.out.println("clicked5");
//                tAmThanh.setBackgroundResource(R.color.onclick);
                TireSystem sys = TireSystem.getInstance();
                if (sys.isMute) {
                    sys.isMute = false;
                    tAmThanh.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_volume_up, 0, 0, 0);
                }
                else {
                    sys.isMute = true;
                    tAmThanh.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_volume_off, 0, 0, 0);
                    sys.stopWarningSound();
                }
//                sys.isMute = !sys.isMute;
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
//        TireSystem system = TireSystem.getInstance();
//        SensorManager manager = system.getSensorManager();

//        Sensor pressureSensor1 = system.getPressureSensor1();
//        Sensor pressureSensor2 = system.getPressureSensor1();
//        Sensor pressureSensor3 = system.getPressureSensor1();
//        Sensor pressureSensor4 = system.getPressureSensor1();
//        Sensor temperatureSensor = system.getTemperatureSensor();

//        manager.registerListener(pressureSensorEventListener, pressureSensor1, SensorManager.SENSOR_DELAY_NORMAL);
//        manager.registerListener(temperatureSensorEventListener, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        manager.registerListener(humiditySensorEventListener, pressureSensor2, SensorManager.SENSOR_DELAY_NORMAL);
//        manager.registerListener(lightSensorEventListener, pressureSensor3, SensorManager.SENSOR_DELAY_NORMAL);
//        manager.registerListener(proximitySensorEventListener, pressureSensor4, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager manager = TireSystem.getInstance().getSensorManager();
        Sensor sensor = TireSystem.getInstance().getPressureSensor();
        manager.unregisterListener(pressureSensorEventListener);
    }


}