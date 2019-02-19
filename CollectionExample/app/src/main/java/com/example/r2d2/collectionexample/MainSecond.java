package com.example.r2d2.collectionexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r2d2.collectionexample.adapters.SongListViewAdapter;
import com.example.r2d2.collectionexample.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class MainSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        Bundle extras = this.getIntent().getExtras();
        String theTitle = extras.getString("theTitle","");
        String theArtist = extras.getString("theArtist","");
        String theDuration = extras.getString("theDuration","");

        //Para mostrar en la interfaz
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvArtist = findViewById(R.id.tvArtist);
        TextView tvDuration = findViewById(R.id.tvDuration);

        tvTitle.setText(theTitle);
        //tvInteger.setText(""+theInteger);
        tvArtist.setText(String.format("%s",theArtist));
        //tvFloat.setText(""+theFloat);
        tvDuration.setText(String.format("%s",theDuration));

    }

}
