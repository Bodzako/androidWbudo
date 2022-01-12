package com.example.wbudo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorPressure extends AppCompatActivity implements SensorEventListener {


    SensorManager manager;
    TextView tekst1;
    TextView tekst2;
    float wartosc=1.0f;
    float start_licz=1.0f;
    boolean licz=false, lapy=false;
    Button btnPressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_pressure);


        tekst1=(TextView)findViewById(R.id.textView3);
        tekst2=(TextView)findViewById(R.id.textView_cis);
        btnPressure = (Button) findViewById(R.id.button_cis1);

        tekst1.setText("");

        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_PRESSURE),0,null);


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO Auto-generated method stub
    }
    public void start(View view) {

        if(view.getId()==R.id.button_cis1)
        {
            start_licz=wartosc;
            licz=true;
            btnPressure.setVisibility(view.GONE);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        tekst1.setText("Czujnik cisnienia: "+event.values[0]+" hPa");
        wartosc=event.values[0];
        if(wartosc>start_licz+0.12 && licz==true) {
            tekst2.setText("Telefon jest niżej");

        }
        else if(wartosc<start_licz-0.12 && licz==true)
            tekst2.setText("Telefon jest wyżej");
        else
            tekst2.setText("wysokość telefonu nie zmieniła się");


    }

    @Override
    public void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }


}