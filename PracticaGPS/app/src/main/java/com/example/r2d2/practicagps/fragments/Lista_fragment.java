package com.example.r2d2.practicagps.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.r2d2.practicagps.Entity.Point;
import com.example.r2d2.practicagps.R;
import com.example.r2d2.practicagps.adapters.Adaptador;
import com.example.r2d2.practicagps.database.database;

import java.util.List;

public class Lista_fragment extends Fragment {

    private ListView lvPuntos;

    public Lista_fragment() {
        // Required empty public constructor
    }

    public static Lista_fragment newInstance() {
        Lista_fragment fragment = new Lista_fragment();
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
        return inflater.inflate(R.layout.fragment_lista_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lvPuntos = view.findViewById(R.id.lvLista);
        database db = database.sharedInstance();
        List<Point> consulted = db.pointDao().getAll();
        Adaptador adapter = new Adaptador(consulted);
        lvPuntos.setAdapter(adapter);
    }
}
