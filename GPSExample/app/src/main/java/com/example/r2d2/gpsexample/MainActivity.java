package com.example.r2d2.gpsexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvLatitud;
    private TextView tvLongitud;
    private TextView tvAltitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvLatitud = findViewById(R.id.tvLatitud);
        this.tvLongitud = findViewById(R.id.tvLongitud);
        this.tvAltitud = findViewById(R.id.tvAltitud);

        this.configureLoactionManager();

    }

    private void configureLoactionManager() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            String[] permisos = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };

            ActivityCompat.requestPermissions(this, permisos, 999);


        }else{
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    tvLatitud.setText(""+location.getLatitude());
                    tvLongitud.setText(""+location.getLongitude());
                    tvAltitud.setText(""+location.getAltitude());

                }

                @Override
                public void onStatusChanged(String provider, int i, Bundle bundle) {
                    Toast.makeText(MainActivity.this,"Cambio "+provider,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Toast.makeText(MainActivity.this,"Activo "+provider,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(MainActivity.this,"Desactivo "+provider,Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 999){

            boolean tienePermisos = true;
            for(int permiso : grantResults){
                if(permiso == PackageManager.PERMISSION_DENIED){
                    tienePermisos = false;
                    break;
                }
            }
            if(!tienePermisos){
                Toast.makeText(this,"No tienes todos los permisos",Toast.LENGTH_LONG).show();
            }else{
                this.configureLoactionManager();
            }
        }

    }
}
