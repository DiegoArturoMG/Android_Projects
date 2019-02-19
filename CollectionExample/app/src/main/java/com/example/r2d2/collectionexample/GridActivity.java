package com.example.r2d2.collectionexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.r2d2.collectionexample.adapters.SongGridViewAdapter;
import com.example.r2d2.collectionexample.adapters.SongListViewAdapter;
import com.example.r2d2.collectionexample.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GridView gridView = findViewById(R.id.gvCanciones);

        SongGridViewAdapter adapter = new SongGridViewAdapter(this.generateDummyList(50));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridView grid = (GridView) parent;
                ListAdapter adapter = grid.getAdapter();

                SongModel item = (SongModel) adapter.getItem(position);

                //Toast.makeText(GridActivity.this,"Cancion: "+item,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(GridActivity.this,MainSecond.class); //Operacion de resolucion de alcance
                intent.putExtra("theTitle",String.format("%s",item.getTitle()));
                intent.putExtra("theArtist",String.format("%s",item.getArtist()));
                intent.putExtra("theDuration",String.format("%s",item.getDuration()));

                startActivity(intent); //Debe ir hasta el final para que pueda enviar todos los datos
            }
        });

    }

    private List<SongModel> generateDummyList(int count) {
        List<SongModel> list = new ArrayList<>(count);

        for(int x=1; x<=count; x++){
            SongModel song = new SongModel("Title: "+x,"Artist: "+x,String.format("%d:%d",x,(x*x)));
            list.add(song);
        }

        return list;
    }
}
