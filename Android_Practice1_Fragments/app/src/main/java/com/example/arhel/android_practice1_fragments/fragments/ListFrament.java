package com.example.arhel.android_practice1_fragments.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arhel.android_practice1_fragments.R;
import com.example.arhel.android_practice1_fragments.adapter.ListViewAdapter;
import com.example.arhel.android_practice1_fragments.models.SongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrament extends Fragment {

    private String title;
    private String artist;
    private String duration;


    public static ListFrament newInstance() {

        Bundle args = new Bundle();

        ListFrament fragment = new ListFrament();
        fragment.setArguments(args);
        return fragment;
    }

    public ListFrament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_frament, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final List<SongModel> songs = this.generateDummyList(50);
        ListViewAdapter adapter = new ListViewAdapter(songs);

        ListView lvsong = view.findViewById(R.id.lvCanciones);
        lvsong.setAdapter(adapter);

        lvsong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ListFrament.this.title = songs.get(i).getTitle();
                ListFrament.this.artist = songs.get(i).getArtist();
                ListFrament.this.duration = songs.get(i).getDuration();

                ListFrament.this.navigateToSongDetail();

            }
        });

    }

    private void navigateToSongDetail(){

        String texttitulo = this.title;
        String textartista = this.artist;
        String textduracion = this.duration;

        Fragment songdetail = SongDetailFragment.newInstance(texttitulo,textartista,textduracion);
        FragmentManager fragmentManager = this.getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container,songdetail);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    private List<SongModel> generateDummyList (int count){
        List<SongModel> list = new ArrayList<>(count);
        for (int x = 1; x<= count; x++){
            SongModel song = new SongModel("Title"+x,"Artist"+x, String.format("%d:%d",x,(x*x)));
            list.add(song);
        }
        return  list;
    }

}
