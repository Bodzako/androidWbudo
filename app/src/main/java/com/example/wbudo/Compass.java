package com.example.wbudo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class Compass extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private float[] cz_Gravity = new float[3];
    private float[] cz_Geomagnetic = new float[3];
    private float kierunek = 0f;
    private float kierunek_temp = 0f;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);


        imageView = (ImageView)findViewById(R.id.kompas);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final float a = 0.97f;
        synchronized(this){
            if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            {
                cz_Gravity[0] = a*cz_Gravity[0]+(1-a)*sensorEvent.values[0];
                cz_Gravity[1] = a*cz_Gravity[1]+(1-a)*sensorEvent.values[1];
                cz_Gravity[2] = a*cz_Gravity[2]+(1-a)*sensorEvent.values[2];
            }
            if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
            {
                cz_Geomagnetic[0] = a*cz_Geomagnetic[0]+(1-a)*sensorEvent.values[0];
                cz_Geomagnetic[1] = a*cz_Geomagnetic[1]+(1-a)*sensorEvent.values[1];
                cz_Geomagnetic[2] = a*cz_Geomagnetic[2]+(1-a)*sensorEvent.values[2];
            }
            float R[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R,null,cz_Gravity,cz_Geomagnetic);
            if(success)
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R,orientation);
                kierunek = (float)Math.toDegrees(orientation[0]);
                kierunek = (kierunek+360)%360;
                Animation anime = new RotateAnimation(-kierunek_temp,-kierunek, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                kierunek_temp = kierunek;
                anime.setDuration(500);
                imageView.startAnimation(anime);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}