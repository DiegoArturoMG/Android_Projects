package com.example.r2d2.practicagps.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r2d2.practicagps.Entity.Point;
import com.example.r2d2.practicagps.R;
import com.example.r2d2.practicagps.database.database;

public class Gps_fragment extends Fragment {

    private TextView tvLatitud;
    private TextView tvLongitud;

    public Gps_fragment() {
        // Required empty public constructor
    }

    public static Gps_fragment newInstance() {
        Gps_fragment fragment = new Gps_fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.tvLatitud = view.findViewById(R.id.tvLatitud);
        this.tvLongitud = view.findViewById(R.id.tvLongitud);

        this.configureLoactionManager();


        Button mostrarMapa = view.findViewById(R.id.btnMostrarEnMapa);

        mostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gps_fragment.this.navigateToMapa();
            }
        });

        Button mostrarLista = view.findViewById(R.id.btnMostrarEnLista);

        mostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gps_fragment.this.navigateToLista();
            }
        });
        
    }

    private void navigateToMapa() {
        Fragment second = Mapa_fragment.newInstance();
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, second);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToLista() {
        Fragment second = Lista_fragment.newInstance();
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, second);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void configureLoactionManager() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String[] permisos = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };

            ActivityCompat.requestPermissions(getActivity(), permisos, 999);

        } else {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    tvLatitud.setText("" + location.getLatitude());
                    tvLongitud.setText("" + location.getLongitude());

                    database db = database.sharedInstance();

                    Point punto = new Point();

                    punto.setLatitud(location.getLatitude());
                    punto.setLongitud(location.getLongitude());

                    db.pointDao().insert(punto);

                    Toast.makeText(getActivity(), "Punto guardado con exito",
                            Toast.LENGTH_LONG).show();

                }

                @Override
                public void onStatusChanged(String provider, int i, Bundle bundle) {
                    Toast.makeText(getContext(), "Cambio " + provider, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Toast.makeText(getContext(), "Activo " + provider, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(getContext(), "Desactivo " + provider, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(),"No tienes todos los permisos",Toast.LENGTH_LONG).show();
            }else{
                this.configureLoactionManager();
            }
        }

    }

}

