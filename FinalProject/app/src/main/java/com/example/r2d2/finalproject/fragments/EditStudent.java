package com.example.r2d2.finalproject.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.PaisesEscuelasCarreras.SchoolInformation;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditStudent extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;

    private Button btnSaveChanges;
    private EditText etPassword;
    private EditText etEdad;
    private EditText etPhone;
    private Spinner sPais;
    private Spinner sEscuela;
    private Spinner sCarrera;
    private ImageButton btnCamera;
    //private String photo;

    private static int REQUEST_IMAGE_CAPTURE = 999;
    private static int CAMERA_PERMISSION = 888;

    private File photoFile;

    public EditStudent() {
        // Required empty public constructor
    }

    public static EditStudent newInstance(Student student) {
        EditStudent fragment = new EditStudent();
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
        return inflater.inflate(R.layout.fragment_edit_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        etPassword = view.findViewById(R.id.etPasswordEdit);
        etEdad = view.findViewById(R.id.etEdadEdit);
        etPhone = view.findViewById(R.id.etTelefonoEdit);

        etPassword.setText(student.getPassword());
        etEdad.setText(student.getAge());
        etPhone.setText(student.getPhoneNumber());

        btnCamera = view.findViewById(R.id.ibPictureEdit);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        cargarSpinners(view);

        sPais = view.findViewById(R.id.sPaisEdit);
        sEscuela = view.findViewById(R.id.sEscuelaEdit);
        sCarrera = view.findViewById(R.id.sCarreraEdit);

        btnSaveChanges = view.findViewById(R.id.btnSaveChanges);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student.setPassword(etPassword.getText().toString());
                student.setAge(etEdad.getText().toString());
                student.setPhoneNumber(etPhone.getText().toString());
                student.setCity(sPais.getSelectedItem().toString());
                student.setSchool(sEscuela.getSelectedItem().toString());
                student.setCareer(sCarrera.getSelectedItem().toString());

                AppDatabase db = AppDatabase.sharedInstance();
                db.studentDao().update(student);
                Toast.makeText(getActivity(), "Updated Student", Toast.LENGTH_LONG).show();

                Fragment contactDetail = InicioSesion.newInstance();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("");
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

    }

    public void cargarSpinners(View view){
        Spinner spinnerPais = view.findViewById(R.id.sPaisEdit);
        String[] paises = SchoolInformation.paises;
        spinnerPais.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paises));

        Spinner spinnerEscuela = view.findViewById(R.id.sEscuelaEdit);
        String[] escuelas = SchoolInformation.escuelas;
        spinnerEscuela.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, escuelas));

        Spinner spinnerCarreras = view.findViewById(R.id.sCarreraEdit);
        String[] carreras = SchoolInformation.carreras;
        spinnerCarreras.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carreras));
    }

    private void takePhoto() {

        if (ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

            String[] permissions = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this.getActivity(),
                    permissions,
                    CAMERA_PERMISSION);

        } else {


            Intent takePictureIntent =
                    new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent
                    .resolveActivity(this.getContext().getPackageManager()) != null) {

                // Create the File where the photo should go
                try {

                    this.photoFile = createImageFile();

                    if (this.photoFile != null) {

                        Uri photoURI = FileProvider.getUriForFile(this.getContext(),
                                "com.example.r2d2.finalproject.fileprovider",
                                this.photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                        student.setPath(this.photoFile.getAbsolutePath());
                        Log.d("TAG",""+student.getPath().toString());
                    }

                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e("TAG", "Error al intentar captura la imágen");
                }
            }


        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getContext()
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */);

        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.takePhoto();
            } else {
                Toast.makeText(this.getContext(),
                        "No tienes permiso para sacar fotos",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

}
