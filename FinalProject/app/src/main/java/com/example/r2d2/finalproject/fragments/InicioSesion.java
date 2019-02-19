package com.example.r2d2.finalproject.fragments;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.Entity.Student_Message;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.util.Calendar;
import java.util.Date;

public class InicioSesion extends Fragment {

    private EditText etMail;
    private EditText etPass;

    public InicioSesion() {
        // Required empty public constructor
    }

    public static InicioSesion newInstance() {
        InicioSesion fragment = new InicioSesion();
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
        return inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etMail = view.findViewById(R.id.etMail_InicioSesion);
        etPass = view.findViewById(R.id.etPassword_InicioSesion);

        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    AppDatabase db = AppDatabase.sharedInstance();
                    String email = etMail.getText().toString().replace(" ","");
                    Student consultedStudent = db.studentDao().studentById(email);

                    if(consultedStudent != null){
                        if(consultedStudent.getPassword().equals(etPass.getText().toString())){

                            Fragment contactDetail = MenuAlumno.newInstance(consultedStudent);
                            FragmentManager manager = getFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.container, contactDetail);
                            transaction.commit();
                        }else{
                            Toast.makeText(getActivity(), "The password is incorrect!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "The student does not exist!",
                                Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    Toast.makeText(getActivity(), ""+e,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        Button btnSignup = view.findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = NewStudent.newInstance();

                FragmentManager manager = getFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                transaction.addToBackStack(null);

                transaction.replace(R.id.container, contactDetail);

                transaction.commit();
            }
        });

    }
}
