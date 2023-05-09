package com.example.myapplication1.system;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.TextView;

import com.example.myapplication1.MainActivity;
import com.example.myapplication1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TireSystem {

    private static TireSystem instance = null;
    MediaPlayer mp = null;
    public boolean isMute = true;

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor temperatureSensor;

    private Sensor pressureSensor1;
    private Sensor pressureSensor2;
    private Sensor pressureSensor3;
    private Sensor pressureSensor4;

    private double sensorVal1 = 0.0;
    private double sensorVal2 = 0.0;
    private double sensorVal3 = 0.0;
    private double sensorVal4 = 0.0;

    public Map<String, Sensor> mapSensor = new HashMap<>();

    public static final String TRAITRUOC = "trai truoc";
    public static final String TRAISAU = "trai sau";
    public static final String PHAITRUOC = "phai truoc";
    public static final String PHAISAU = "phai sau";

    public static String BAR = "Bar";
    public static String PSI = "PSI";
    public static String KPA = "KPA";
    public static String KG = "KG";
    public static String C = "\u2103";
    public static String F = "\u2109";

    public  String pressureDegree = TireSystem.BAR;
    public  String temperatureDegree = TireSystem.C;

    public static int apSuatTren = 100;
    public static int apSuatDuoi = 0;

    public static boolean isAlert = false;
    public static boolean isPass = false;
    public static double currentValue = 0;
    //public static String presRuler = "Bar";
    //public static String temperatureRuler = C;

    static List<Double> listSensorValues = new ArrayList<>();

    public static TireSystem getInstance() {
        if (instance == null) {
            instance = new TireSystem();
        }
        return instance;
    }

    public static void show(List<Sensor> list) {
        for (Sensor sensor : list) {
            System.out.println("sensor 1: ");
            System.out.println(sensor.getPower());
        }
    }

    public void checkAlertPressure(double pressure, Sensor sensor) {
        String title = "Cảnh báo áp suất lốp ";


        boolean isShow = true;
        if (listSensorValues.contains(pressure)) {
            isShow = false;
        }
//        else {
//
//        }
        isPass = false;
        if (isShow) {
            if(pressure<TireSystem.apSuatDuoi) {
                title += "quá thấp!";
                if (isAlert || isPass)
                    TireSystem.showAlertPressureLow(title, "Bỏ qua cảnh báo?");
            }
            else if (pressure > TireSystem.apSuatTren) {
                title += "quá cao!";
                if (currentValue != pressure)
                    currentValue = pressure;
                TireSystem.showAlertPressureHigh(title, "Bỏ qua cảnh báo?");
            }
        }

    }

    public static void showAlertPressureHigh(String title, String message) {
        if (isAlert || isPass) return;
        isAlert = true;
//        isPass = true;
        new AlertDialog.Builder(MainActivity.getInstance())
                .setTitle(title)
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        isAlert = false;
                        isPass = true;
                        instance.stopWarningSound();
                        listSensorValues = TireSystem.getInstance().getListSensorValues();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isAlert = false;
                        isPass = false;
                        instance.stopWarningSound();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        if (!instance.isMute)
            instance.playWarningSound(MainActivity.getInstance());
    }

    public static void showAlertPressureLow(String title, String mess) {
        if (isAlert || isPass) return;
        isAlert = true;
        isPass = true;
        new AlertDialog.Builder(MainActivity.getInstance())
                .setTitle(title)
                .setMessage(mess)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        isAlert = false;
                        isPass = true;
                        instance.stopWarningSound();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isAlert = false;
                        isPass = false;
                        instance.stopWarningSound();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        if (instance.isMute)
            instance.playWarningSound(MainActivity.getInstance());
    }

    public List<Double> getListSensorValues() {
        return Arrays.asList(sensorVal1, sensorVal2, sensorVal3, sensorVal4);
    }

    public void playWarningSound(Context context){
        if (mp == null) mp = MediaPlayer.create(context, R.raw.alert);
        mp.setVolume(1.0f, 1.0f);
        mp.setLooping(true);
        if (!isMute)
            mp.start();
    }

    public void stopWarningSound() {
        if (mp != null) {
            mp.pause();
        }
    }

    public double getSensorVal1() {
        return sensorVal1;
    }

    public void setSensorVal1(double sensorVal1) {
        this.sensorVal1 = sensorVal1;
    }

    public double getSensorVal2() {
        return sensorVal2;
    }

    public void setSensorVal2(double sensorVal2) {
        this.sensorVal2 = sensorVal2;
    }

    public double getSensorVal3() {
        return sensorVal3;
    }

    public void setSensorVal3(double sensorVal3) {
        this.sensorVal3 = sensorVal3;
    }

    public double getSensorVal4() {
        return sensorVal4;
    }

    public void setSensorVal4(double sensorVal4) {
        this.sensorVal4 = sensorVal4;
    }

    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public Sensor getPressureSensor() {
        return pressureSensor;
    }

    public void setPressureSensor(Sensor pressureSensor) {
        this.pressureSensor = pressureSensor;
    }

    public Sensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(Sensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public Sensor getPressureSensor1() {
        return pressureSensor1;
    }

    public void setPressureSensor1(Sensor pressureSensor1) {
        this.pressureSensor1 = pressureSensor1;

    }

    public Sensor getPressureSensor2() {
        return pressureSensor2;
    }

    public void setPressureSensor2(Sensor pressureSensor2) {
        this.pressureSensor2 = pressureSensor2;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mapSensor.replace(PHAITRUOC, pressureSensor2);
        }
    }

    public Sensor getPressureSensor3() {
        return pressureSensor3;
    }

    public void setPressureSensor3(Sensor pressureSensor3) {
        this.pressureSensor3 = pressureSensor3;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mapSensor.replace(TRAISAU, pressureSensor3);
        }
    }

    public Sensor getPressureSensor4() {
        return pressureSensor4;
    }

    public void setPressureSensor4(Sensor pressureSensor4) {
        this.pressureSensor4 = pressureSensor4;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mapSensor.replace(PHAISAU, pressureSensor4);
        }
    }
}
