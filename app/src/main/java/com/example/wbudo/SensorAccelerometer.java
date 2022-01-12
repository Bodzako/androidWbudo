package com.example.wbudo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorAccelerometer extends AppCompatActivity implements SensorEventListener{

    TextView xAxis, yAxis, zAxis;
    float toGs = (float) (1/ 9.80665);
    double xAx, yAx, zAx;
    String x, y, z;
    SensorManager sensorManager2;
    Sensor aSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_accelerometer);

        sensorManager2 = (SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = sensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        xAxis = findViewById(R.id.xaxis);
        yAxis = findViewById(R.id.yaxis);
        zAxis = findViewById(R.id.zaxis);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xAx = (double) Math.round(sensorEvent.values[0] * toGs * 1000d) / 1000d;
        yAx = (double) Math.round(sensorEvent.values[1] * toGs * 1000d) / 1000d;
        zAx = (double) Math.round(sensorEvent.values[2] * toGs * 1000d) / 1000d;
        x = "Oś x: " + xAx + " g";
        y = "Oś y: " + yAx + " g";
        z = "Oś z: " + zAx + " g";
        xAxis.setText(x);
        yAxis.setText(y);
        zAxis.setText(z);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager2.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager2.unregisterListener(this);

    }


}