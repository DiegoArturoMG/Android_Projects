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
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewStudent_3 extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;

    private static int REQUEST_IMAGE_CAPTURE = 999;
    private static int CAMERA_PERMISSION = 888;

    private File photoFile;
    ImageButton imbtnCamera;

    public NewStudent_3() {
        // Required empty public constructor
    }

    public static NewStudent_3 newInstance(Student student) {
        NewStudent_3 fragment = new NewStudent_3();
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
        return inflater.inflate(R.layout.fragment_new_student_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //student.setPath(String.valueOf(R.id.imgbCamera));
        //student.setPath("camera.png");
        imbtnCamera = view.findViewById(R.id.imgbCamera);

        imbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        Button btnContinue = view.findViewById(R.id.btnSignUpFinal);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    AppDatabase db = AppDatabase.sharedInstance();

                    db.studentDao().insert(student);

                    Toast.makeText(getActivity(), "Student saved",
                            Toast.LENGTH_LONG).show();

                    Fragment contactDetail = InicioSesion.newInstance();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.container, contactDetail);
                    transaction.commit();

                }catch(Exception e){
                    Toast.makeText(getActivity(), "The email has been used",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

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

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE &&
                resultCode == Activity.RESULT_OK) {

            if (this.photoFile != null) {

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(
                        this.photoFile.getAbsolutePath(),
                        bmOptions);

                this.imbtnCamera.setImageBitmap(bitmap);

            }

        }

    }


}
