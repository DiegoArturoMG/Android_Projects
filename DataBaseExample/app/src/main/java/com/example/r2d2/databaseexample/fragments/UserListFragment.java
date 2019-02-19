package com.example.r2d2.databaseexample.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.adapters.ContactListAdapters;
import com.example.r2d2.databaseexample.database.AppDatabase;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.Arrays;
import java.util.List;

public class UserListFragment extends Fragment {

    private ListView lvContactos;

    public UserListFragment() {}

    public static UserListFragment newInstance() {
        Bundle args = new Bundle();
        UserListFragment fragment = new UserListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        lvContactos = view.findViewById(R.id.lvContactos);

        AppDatabase db = AppDatabase.sharedInstance();
        List<UserEntity> consulted = db.userDao().getAll();
        ContactListAdapters adapter = new ContactListAdapters(consulted);
        lvContactos.setAdapter(adapter);

        ImageButton btnNavega = view.findViewById(R.id.ibAdd);

        btnNavega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserListFragment.this.navigateToCreateNewUser();

            }
        });

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                UserEntity user = (UserEntity) lvContactos.getAdapter().getItem(position);

                Fragment contactDetail = UserDetailFragment.newInstance(user);

                FragmentManager manager = getFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                transaction.addToBackStack("");

                transaction.replace(R.id.container, contactDetail);

                transaction.commit();

            }
        });

    }

    private void navigateToCreateNewUser(){
        Fragment second = NewUserFragment.newInstance();

        FragmentManager fragmentManager =
                this.getFragmentManager();

        FragmentTransaction transaction =
                fragmentManager.beginTransaction();

        transaction.replace(R.id.container, second);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
