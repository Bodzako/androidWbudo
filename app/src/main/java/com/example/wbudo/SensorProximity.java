package com.example.wbudo;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class SensorProximity extends AppCompatActivity implements SensorEventListener {

     ImageView traingWarning;
     Vibrator v;
     ConstraintLayout layoutProximity;
     SensorManager sensorManager;
     Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximity);

        layoutProximity = (ConstraintLayout) findViewById(R.id.layoutProximity);
        v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        traingWarning = (ImageView) findViewById(R.id.traingWarning);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sensorManager.registerListener(this,proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
            traingWarning.setVisibility(View.VISIBLE);
            layoutProximity.setBackgroundColor(Color.RED);;
            v.vibrate(350);

        } else {
            traingWarning.setVisibility(View.INVISIBLE);
            layoutProximity.setBackgroundColor(Color.GREEN);

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    @Override
    protected void onResume() {
        super.onResume();

    }



    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }





}