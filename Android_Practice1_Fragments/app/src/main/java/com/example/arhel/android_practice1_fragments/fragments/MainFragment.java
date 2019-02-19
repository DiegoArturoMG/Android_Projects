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
import android.widget.Button;

import com.example.arhel.android_practice1_fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnList = view.findViewById(R.id.btnList);
        Button btnGrid = view.findViewById(R.id.btnGrid);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment.this.navigateToListView();
            }
        });

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment.this.navigateToGridView();
            }
        });

    }

    public void navigateToListView(){
        Fragment list = ListFrament.newInstance();
        FragmentManager fm = this.getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.container,list);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    public void navigateToGridView(){
        Fragment grid = GridFragment.newInstance();
        FragmentManager fm = this.getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container,grid);
        ft.addToBackStack(null);
        ft.commit();

    }


}
