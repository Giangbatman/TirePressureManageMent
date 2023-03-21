package com.example.myapplication1.system;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

public class TireSystem {

    private static TireSystem instance = null;

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor temperatureSensor;

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
