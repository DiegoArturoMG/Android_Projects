package com.example.r2d2.finalproject.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.R;

public class MenuAlumno extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;

    private ImageView ivPictureMenu;
    private TextView tvMailMenu;
    private TextView tvPassMenu;
    private TextView tvNameMenu;
    private TextView tvLastNameMenu;
    private TextView tvCityMenu;
    private TextView tvSchoolMenu;
    private TextView tvCareerMenu;


    public MenuAlumno() {
        // Required empty public constructor
    }

    public static MenuAlumno newInstance(Student student) {
        MenuAlumno fragment = new MenuAlumno();
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
        return inflater.inflate(R.layout.fragment_menu_alumno, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d("TAG","path: "+student.getPath());

        ivPictureMenu = view.findViewById(R.id.ivPictureMenu);
        tvMailMenu = view.findViewById(R.id.tvMailMenu);
        tvPassMenu = view.findViewById(R.id.tvPassMenu);
        tvNameMenu = view.findViewById(R.id.tvNameMenu);
        tvLastNameMenu = view.findViewById(R.id.tvLastNameMenu);
        tvCityMenu = view.findViewById(R.id.tvCityMenu);
        tvSchoolMenu = view.findViewById(R.id.tvSchoolMenu);
        tvCareerMenu = view.findViewById(R.id.tvCareerMenu);

        tvMailMenu.setText(student.getMail());
        tvPassMenu.setText(student.getPassword());
        tvNameMenu.setText(student.getFirstName());
        tvLastNameMenu.setText(student.getLastName());
        tvCityMenu.setText(student.getCity());
        tvSchoolMenu.setText(student.getSchool());
        tvCareerMenu.setText(student.getCareer());

        loadImage();

        Button btnNewMessage = view.findViewById(R.id.btnNewMessage);
        btnNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = NewMessage.newInstance(student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

        Button btnViewSendMessages = view.findViewById(R.id.btnViewMessagesSend);
        btnViewSendMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = ViewSendMessages.newInstance(student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

        Button btnViewReceiveMessages = view.findViewById(R.id.btnViewMessagesReceive);
        btnViewReceiveMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = ViewReceiveMessage.newInstance(student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

        Button btnLogOut = view.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = InicioSesion.newInstance();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

        ImageButton ibtnEdit = view.findViewById(R.id.ibntEdit);
        ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment contactDetail = EditStudent.newInstance(student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack("");
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

    }

    private void loadImage() {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(
                student.getPath(),
                bmOptions);

        this.ivPictureMenu.setImageBitmap(bitmap);

    }
}
