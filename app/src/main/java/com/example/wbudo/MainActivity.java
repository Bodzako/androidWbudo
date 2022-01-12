package com.example.wbudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGPS;
    Button btnProximity;
    Button btnGyroscpoe;
    Button btnLight;
    Button btnAccelelometer;
    Button btnMagnetometer;
    Button btnPressureG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGPS = (Button) findViewById(R.id.btnGPS);
        btnProximity = (Button) findViewById(R.id.btnProximity);
        btnLight = (Button) findViewById(R.id.btnLight);
        btnPressureG = (Button) findViewById(R.id.btnGauge);
        btnAccelelometer= (Button) findViewById(R.id.btnAccelerometer);
        btnGyroscpoe= (Button) findViewById(R.id.btnGyroscpoe);
        btnMagnetometer= (Button) findViewById(R.id.btnMagnetometer);


        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, GPS.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(i1);
            }
        });



        btnProximity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, SensorProximity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i2);
            }


        });

        btnLight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this, SensorLight.class);
                i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i3);
            }

        });



        btnPressureG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(MainActivity.this, SensorPressure.class);
                i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i4);
            }

        });

        btnAccelelometer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(MainActivity.this, SensorAccelerometer.class);
                i5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i5);
            }

        });

        btnGyroscpoe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(MainActivity.this, SensorGyroscope.class);
                i6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i6);
            }

        });

        btnMagnetometer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i7 = new Intent(MainActivity.this, Compass.class);
                i7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i7);
            }

        });



    }
}