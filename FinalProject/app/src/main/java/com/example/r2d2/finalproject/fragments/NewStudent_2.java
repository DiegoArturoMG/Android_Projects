package com.example.r2d2.finalproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.PaisesEscuelasCarreras.SchoolInformation;
import com.example.r2d2.finalproject.R;

public class NewStudent_2 extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;
    private Spinner sPais;
    private Spinner sEscuela;
    private Spinner sCarrera;

    public NewStudent_2() {
        // Required empty public constructor
    }


    public static NewStudent_2 newInstance(Student student) {
        NewStudent_2 fragment = new NewStudent_2();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_ARG,student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (getArguments() != null) {
            this.student = (Student) args.getSerializable(CONTACT_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_student_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerPais = view.findViewById(R.id.sPais);
        String[] paises = SchoolInformation.paises;
        spinnerPais.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paises));

        Spinner spinnerEscuela = view.findViewById(R.id.sEscuela);
        String[] escuelas = SchoolInformation.escuelas;
        spinnerEscuela.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, escuelas));

        Spinner spinnerCarreras = view.findViewById(R.id.sCarrera);
        String[] carreras = SchoolInformation.carreras;
        spinnerCarreras.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carreras));

        sPais = view.findViewById(R.id.sPais);
        sEscuela = view.findViewById(R.id.sEscuela);
        sCarrera = view.findViewById(R.id.sCarrera);

        Button btnContinue = view.findViewById(R.id.btnContinueNewStudent3);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student.setCity(sPais.getSelectedItem().toString());
                student.setSchool(sEscuela.getSelectedItem().toString());
                student.setCareer(sCarrera.getSelectedItem().toString());

                Fragment contactDetail = NewStudent_3.newInstance(student);

                FragmentManager manager = getFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                transaction.addToBackStack("");

                transaction.replace(R.id.container, contactDetail);

                transaction.commit();
            }
        });
    }
}
