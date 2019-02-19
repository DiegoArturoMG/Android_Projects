package com.example.arhel.android_practice1_fragments.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arhel.android_practice1_fragments.R;

public class SongDetailFragment extends Fragment {

    private String title;
    private String artist;
    private String duration;

    public static SongDetailFragment newInstance(String title, String artist,String duration) {

        Bundle args = new Bundle();
        args.putString("TITLE",title);
        args.putString("ARTIST",artist);
        args.putString("DURATION",duration);

        SongDetailFragment fragment = new SongDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SongDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_detail, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = this.getArguments();
        if(arguments != null){
            this.title= arguments.getString("TITLE");
            this.artist= arguments.getString("ARTIST");
            this.duration= arguments.getString("DURATION");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvtitle = view.findViewById(R.id.tvDTitulo);
        TextView tvartist = view.findViewById(R.id.tvDArtista);
        TextView tvduration = view.findViewById(R.id.tvDduracion);

        tvtitle.setText(this.title);
        tvartist.setText(this.artist);
        tvduration.setText(this.duration);
    }
}
