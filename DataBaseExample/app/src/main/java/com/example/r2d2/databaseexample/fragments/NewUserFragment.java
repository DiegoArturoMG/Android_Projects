package com.example.r2d2.databaseexample.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.database.AppDatabase;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.List;

public class NewUserFragment extends Fragment {

    private EditText etName;
    private EditText etLastName;

    public NewUserFragment() {
        // Required empty public constructor
    }

    public static NewUserFragment newInstance() {
        NewUserFragment fragment = new NewUserFragment();
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
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button btnCrear =  view.findViewById(R.id.btnCrear);

        etName = view.findViewById(R.id.etName);
        etLastName = view.findViewById(R.id.etLastName);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String lastName = etLastName.getText().toString();

                if(name.length() != 0 && lastName.length() != 0){
                    AppDatabase db = AppDatabase.sharedInstance();

                    UserEntity user = new UserEntity();

                    user.setFirstName(etName.getText().toString());
                    user.setLastName(etLastName.getText().toString());

                    db.userDao().insert(user);

                    Toast.makeText(getActivity(), "Contacto creado con exito",
                            Toast.LENGTH_LONG).show();

                    List<UserEntity> consulted = db.userDao().getAll();

                    for (UserEntity u : consulted) {
                        Log.d("TAG","-----------------------");

                        Log.d("TAG", u.toString());

                    }

                    FragmentManager fm = getFragmentManager();

                    fm.popBackStack();
                }else{
                    Toast.makeText(getActivity(), "Complete los campos",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
