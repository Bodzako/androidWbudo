package com.example.wbudo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;




public class GPS extends Activity implements LocationListener {

    public LocationManager locationManager;

    TextView txtCount;
    TextView txtGPS;
    TextView txtTitleGPS;
    TextView txtGPSAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        txtCount = (TextView) findViewById(R.id.textCount);
        txtGPS = (TextView) findViewById(R.id.textGPS);
        txtTitleGPS  = (TextView) findViewById(R.id.txtTitleGPS);
        txtGPSAccess = (TextView) findViewById(R.id.txtGPSAccess);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        txtCount.setText("Aktualizacja położenia co 5 sekund");
        txtGPS.setText("Proszę się przemieścić");

    }


    @Override
    public void onLocationChanged(final Location location) {

        txtGPS.setText("Położenie uległo zmianie");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtGPS.setText("Proszę się przemieścić.");
            }
        }, 4990);

    }


    @Override
    public void onProviderDisabled(String provider) {
        txtTitleGPS.setText("GPS wyłączony");
    }

    @Override
    public void onProviderEnabled(String provider) {
        txtTitleGPS.setText("GPS właczony");
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            txtGPSAccess.setText("Brak uprawnień, prosze nadać uprawnienia do lokalizacji.");
            return;

        } else {
            txtGPSAccess.setText("Nadano uprawnienia");

        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 2, this);
    }


    @Override
    public void onPause(){
        super.onPause();
        locationManager.removeUpdates(this);
    }

}