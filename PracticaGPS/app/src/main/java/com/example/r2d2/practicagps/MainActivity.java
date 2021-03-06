package com.example.r2d2.practicagps;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.r2d2.practicagps.fragments.Gps_fragment;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            Gps_fragment fragment =
                    Gps_fragment.newInstance();

            FragmentManager fm =
                    this.getSupportFragmentManager();

            FragmentTransaction transaction =
                    fm.beginTransaction();

            transaction.add(R.id.container, fragment);

            transaction.commit();

        }

    }
}
