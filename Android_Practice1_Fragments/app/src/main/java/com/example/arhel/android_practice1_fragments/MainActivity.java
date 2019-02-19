package com.example.arhel.android_practice1_fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arhel.android_practice1_fragments.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            MainFragment fragment = MainFragment.newInstance();

            FragmentManager fm = this.getSupportFragmentManager();

            FragmentTransaction transaction = fm.beginTransaction();

            transaction.add(R.id.container,fragment);

            transaction.commit();

        }

    }
}
