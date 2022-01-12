package com.example.wbudo;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SensorGyroscope extends AppCompatActivity implements SensorEventListener {

    ImageView line_img;
    TextView angle;
    float lineDelta = 0;
    float lAngle = 0;
    SensorManager sensorManager;
    Sensor gSensor;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_gyroscope);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        angle = findViewById(R.id.txt_azi);
        line_img = findViewById(R.id.line);

        if (gSensor == null) {
            Toast.makeText(this, "No gyroscope", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        lineDelta = (float) Math.toDegrees(sensorEvent.values[2]);
        lAngle += lineDelta/400;
        line_img.setRotation(lAngle);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}