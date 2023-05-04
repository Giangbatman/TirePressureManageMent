package com.example.myapplication1.system;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.example.myapplication1.MainActivity;
import com.example.myapplication1.R;

import java.util.List;

public class TireSystem {

    private static TireSystem instance = null;

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor temperatureSensor;

    public static String BAR = "Bar";
    public static String PSI = "PSI";
    public static String KPA = "KPA";
    public static String KG = "KG";
    public static String C = "\u2103";
    public static String F = "\u2109";

    public  String pressureDegree = TireSystem.BAR;
    public  String temperatureDegree = TireSystem.C;

    public static boolean isAlert = false;
    public static boolean isPass = false;
    //public static String presRuler = "Bar";
    //public static String temperatureRuler = C;

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

    public static void showAlertPressureHigh() {
        if (isAlert || isPass) return;
        isAlert = true;
        isPass = true;
        new AlertDialog.Builder(MainActivity.getInstance())
                .setTitle("Cảnh báo áp suất lốp tăng quá cao!")
                .setMessage("Bỏ qua cảnh báo?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        isAlert = false;
                        isPass = true;
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isAlert = false;
                        isPass = false;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void showAlertPressureLow() {
        if (isAlert || isPass) return;
        isAlert = true;
        isPass = true;
        new AlertDialog.Builder(MainActivity.getInstance())
                .setTitle("Cảnh báo áp suất lốp tăng quá thấp!")
                .setMessage("Bỏ qua cảnh báo?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        isAlert = false;
                        isPass = true;
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isAlert = false;
                        isPass = false;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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
}
