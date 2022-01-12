package com.example.wbudo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SensorLight extends AppCompatActivity implements SensorEventListener {

    SensorManager manager;
    TextView tekst1;
    TextView tekst2;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_light);


        tekst1=(TextView)findViewById(R.id.textView1);
        tekst2=(TextView)findViewById(R.id.textView2);
        tekst1.setText("");
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_LIGHT),0,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flashOn() {
        CameraManager kamera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = kamera.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                kamera.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashOff() {
        CameraManager kamera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = kamera.getCameraIdList()[0];
            kamera.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO Auto-generated method stub
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSensorChanged(SensorEvent event) {
        tekst2.setText("Czujnik swiatla: "+event.values[0]);
        float zmienna=event.values[0];

        if(event.values[0]<=2.0f)
        {
            flashOn();
        }
        else
        {
            flashOff();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        flashOff();
        manager.unregisterListener(this);
    }

}



