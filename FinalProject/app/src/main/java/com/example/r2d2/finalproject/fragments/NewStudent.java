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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.PaisesEscuelasCarreras.SchoolInformation;
import com.example.r2d2.finalproject.R;

public class NewStudent extends Fragment {

    private EditText etMail;
    private EditText etPassword;
    private EditText etName;
    private EditText etLastName;
    private EditText etAge;
    private Spinner sGender;
    private EditText etPhone;

    public NewStudent() {
        // Required empty public constructor
    }

    public static NewStudent newInstance() {
        NewStudent fragment = new NewStudent();
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
        return inflater.inflate(R.layout.fragment_new_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerGenero = view.findViewById(R.id.sGenero);
        String[] genero = {"Male","Female"};
        spinnerGenero.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, genero));

        etMail = view.findViewById(R.id.etMail);
        etPassword = view.findViewById(R.id.etPassword);
        etName = view.findViewById(R.id.etNombre);
        etLastName = view.findViewById(R.id.etLastName);
        etAge = view.findViewById(R.id.etEdad);
        sGender = view.findViewById(R.id.sGenero);
        etPhone = view.findViewById(R.id.etTelefono);

        Button btnContinue = view.findViewById(R.id.btnContinueNewStudent2);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = etMail.getText().toString();
                String pass = etPassword.getText().toString();
                String name = etName.getText().toString();
                String lastName = etLastName.getText().toString();
                String age = etAge.getText().toString();
                String gender = sGender.getSelectedItem().toString();
                String phone = etPhone.getText().toString();

                Student student = new Student(mail,pass,name,lastName,age,gender,phone);

                Fragment contactDetail = NewStudent_2.newInstance(student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("");
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });
    }
}
