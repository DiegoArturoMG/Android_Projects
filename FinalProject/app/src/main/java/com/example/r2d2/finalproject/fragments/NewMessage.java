package com.example.r2d2.finalproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.Entity.Student_Message;
import com.example.r2d2.finalproject.PaisesEscuelasCarreras.SchoolInformation;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.util.Calendar;
import java.util.Date;

public class NewMessage extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;

    private Spinner sPais;
    private Spinner sEscuela;
    private Spinner sCarrera;

    private EditText etMessage;
    private Button btnSendMessage;

    public NewMessage() {
        // Required empty public constructor
    }

    public static NewMessage newInstance(Student student) {
        NewMessage fragment = new NewMessage();
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
        return inflater.inflate(R.layout.fragment_new_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        cargarSpinners(view);

        sPais = view.findViewById(R.id.sPaisNewMessage);
        sEscuela = view.findViewById(R.id.sEscuelaNewMessage);
        sCarrera = view.findViewById(R.id.sCarreraNewMessage);

        etMessage = view.findViewById(R.id.etMessageNewMessage);

        btnSendMessage = view.findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = AppDatabase.sharedInstance();
                Student[] remitentes = db.studentDao().studentByCitySchoolCareer(sPais.getSelectedItem().toString(),
                        sEscuela.getSelectedItem().toString(),sCarrera.getSelectedItem().toString());

                for(Student remitente : remitentes){

                    Date currentTime = Calendar.getInstance().getTime();

                    Message mensaje = new Message();

                    mensaje.setId(currentTime.toString()+"_"+student.getMail()+"_"+remitente.getMail());
                    mensaje.setMessage(etMessage.getText().toString());
                    mensaje.setEmisor(student.getMail());
                    mensaje.setRemitente(remitente.getMail());

                    db.messageDao().insert(mensaje);
                    Log.d("TAG","------Msg-------------"+mensaje.toString()+"----------------");

                    Student_Message student_message = new Student_Message();
                    student_message.setId(currentTime.toString()+"_"+student.getMail()+"_"+remitente.getMail());
                    student_message.setSid(student.getMail());
                    student_message.setMid(currentTime.toString()+"_"+student.getMail()+"_"+remitente.getMail());

                    db.studentmessageDao().insert(student_message);
                    Log.d("TAG","------student_message-------------"+student_message.toString()+"----------------");
                }

                Toast.makeText(getActivity(), "You have sent the message to the whole world!", Toast.LENGTH_LONG).show();


                FragmentManager fm = getFragmentManager();
                fm.popBackStack();

            }
        });

    }

    private void cargarSpinners(View view) {
        Spinner spinnerPais = view.findViewById(R.id.sPaisNewMessage);
        String[] paises = SchoolInformation.paises;
        spinnerPais.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paises));

        Spinner spinnerEscuela = view.findViewById(R.id.sEscuelaNewMessage);
        String[] escuelas = SchoolInformation.escuelas;
        spinnerEscuela.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, escuelas));

        Spinner spinnerCarreras = view.findViewById(R.id.sCarreraNewMessage);
        String[] carreras = SchoolInformation.carreras;
        spinnerCarreras.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carreras));
    }

    /*
    Lo que hago aqui es buscar a los alumnos que necesito y despues el mensaje que se les va a enviar,
    se agrega a una clase mensaje, se guarda en la base de datos y despues guardo los datos de mi llave
    primaria de alumno y mi id de mensaje en la tabla intermedia, que en este caso es mi clase Student_Message,
    el problema esta en la linea 121. No se si sea correcta meter datos a esa tabla intermedia, o se instancia
    esa tabla automaticamente al momento de instanciar las otras 2 tablas.
    */
    private void getStudentsSchool(){

            AppDatabase db = AppDatabase.sharedInstance();
            Student[] remitentes = db.studentDao().studentByCitySchoolCareer(sPais.getSelectedItem().toString(),
                    sEscuela.getSelectedItem().toString(),sCarrera.getSelectedItem().toString());

            //for(Student remitente : remitentes){

                //db = AppDatabase.sharedInstance();
                //mensaje = new Message(etMessage.getText().toString(),student.getMail(),remitentes[0].getMail());
                //db.messageDao().insert(mensaje);
                //Log.d("TAG","------Msg_1-------------"+mensaje.getId()+"----------------");


                //Student_Message student_message = new Student_Message(student.getMail(), mensaje.getMid());
                //db.studentmessageDao().insert(student_message);
            //}

            //Toast.makeText(getActivity(), "You have sent the message to the whole world!", Toast.LENGTH_LONG).show();

    }

}
