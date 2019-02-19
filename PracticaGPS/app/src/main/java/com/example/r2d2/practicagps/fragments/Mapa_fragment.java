package com.example.r2d2.practicagps.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r2d2.practicagps.Entity.Point;
import com.example.r2d2.practicagps.R;
import com.example.r2d2.practicagps.adapters.Adaptador;
import com.example.r2d2.practicagps.database.database;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Mapa_fragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;

    public Mapa_fragment() {
        // Required empty public constructor
    }

    public static Mapa_fragment newInstance() {
        Mapa_fragment fragment = new Mapa_fragment();
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
        return inflater.inflate(R.layout.fragment_mapa_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.mfMap);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        this.showMyUserLocation(true);

        database db = database.sharedInstance();
        List<Point> consulted = db.pointDao().getAll();

        for(Point p : consulted){
            LatLng punto = new LatLng(p.getLatitud(), p.getLongitud());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(punto);
            markerOptions.title("punto");
            this.map.addMarker(markerOptions);
            this.map.moveCamera(CameraUpdateFactory.newLatLng(punto));
        }

    }

    private void showMyUserLocation(boolean enabled) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            ActivityCompat.requestPermissions(getActivity(), permissions, 999);

        } else {

            this.map.setMyLocationEnabled(enabled);

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
