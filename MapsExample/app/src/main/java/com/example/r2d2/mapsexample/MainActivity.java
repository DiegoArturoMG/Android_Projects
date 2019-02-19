package com.example.r2d2.mapsexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)this.getSupportFragmentManager().findFragmentById(R.id.mfMap);

        mapFragment.getMapAsync(this);

        Button btnNormal = findViewById(R.id.btnNormal);
        Button btnHybrid = findViewById(R.id.btnHybrid);
        Button btnSatellite = findViewById(R.id.btnSatellite);
        Button btnTerrain = findViewById(R.id.btnTerrain);
        Button btnNone = findViewById(R.id.btnNone);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        btnTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });
        btnNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NONE);
                }
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        agregarPunto(19.4326018,-99.1332049,"Zocalo CDMX");
        agregarPunto(19.604115,-99.240708,"Home");
        agregarPunto(19.712791,-99.221761,"Museo del Virreinato");
        agregarPunto(19.297418,-99.212926,"Six Flags");

    }

    private void agregarPunto(double v, double v1,String valor) {
        LatLng punto = new LatLng(v,v1);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(punto);
        markerOptions.title(valor);

        map.addMarker(markerOptions);

        this.map.moveCamera(CameraUpdateFactory.newLatLng(punto));
    }

    private void showMyUserLocation(boolean enabled) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            ActivityCompat.requestPermissions(this, permissions, 999);

        } else {

            this.map.setMyLocationEnabled(enabled);

            //TODO Iniciar LocationManager
            LocationManager locationManager =
                    (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5,
                10,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        //tvLatitud.setText("" + location.getLatitude());
                        //tvLongitud.setText("" + location.getLongitude());
                        //tvAltitud.setText("" + location.getAltitude());

                    }

                    @Override
                    public void onStatusChanged(String provider, int i, Bundle bundle) {
                        Toast.makeText(MainActivity.this,
                                "Cambió " + provider,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                        Toast.makeText(MainActivity.this,
                                "Se Activó: " + provider,
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                        Toast.makeText(MainActivity.this,
                                "Se Desactivó: " + provider,
                                Toast.LENGTH_LONG).show();

                    }
                }
            );
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 999) {

            boolean granted = true;

            for (int permission : grantResults) {
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }

            if (granted) {
                this.showMyUserLocation(true);
            }

        }
    }

}
